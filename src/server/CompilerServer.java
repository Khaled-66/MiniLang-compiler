// CompilerServer lives in the default package to access ANTLR generated classes.

import ast.ProgramNode;
import errors.ErrorCollector;
import errors.LexerError;
import errors.ParseError;
import interpreter.Interpreter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import semantic.SemanticAnalyzer;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

// =============================================================================
// CompilerServer.java  —  Lightweight HTTP server bridging GUI to compiler
//
// Pipeline Position:  GUI (browser)  →  HTTP POST  →  [CompilerServer]
//                     →  full pipeline  →  JSON response  →  GUI
//
// WHY an HTTP server instead of a subprocess?
//   Starting a new JVM process for every "Run" click in the GUI takes 1–2s.
//   An HTTP server starts ONCE and handles all subsequent requests in <50ms.
//
// Endpoints:
//   POST /run    — runs source code through the full pipeline, returns JSON
//   GET  /       — serves the GUI HTML page
//   GET  /static — serves static assets (CSS, JS)
// =============================================================================
public class CompilerServer {

    public static void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Route: serve GUI files
        server.createContext("/", CompilerServer::handleStatic);

        // Route: run source code
        server.createContext("/run", CompilerServer::handleRun);

        server.setExecutor(null); // Default executor
        server.start();
        System.out.println("MiniLang IDE running at http://localhost:" + port);
    }

    // ── /run — Full compiler pipeline ─────────────────────────
    private static void handleRun(HttpExchange exchange) throws IOException {
        // Add CORS headers so the browser page can call us
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        // Handle preflight OPTIONS request from browser
        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        // Read the raw source code from the request body
        String source;
        try (InputStream is = exchange.getRequestBody()) {
            source = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }

        // Run through the pipeline
        String jsonResponse = runPipeline(source);

        // Send back JSON
        byte[] bytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    // ── /static + / — Serve GUI files ────────────────────────
    private static void handleStatic(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();

        // Map "/" to the GUI index.html
        if (path.equals("/") || path.equals("/index.html")) {
            path = "/gui/index.html";
        } else {
            path = "/gui" + path;
        }

        // Load the file from the classpath (so the JAR is self-contained)
        InputStream resource = CompilerServer.class.getResourceAsStream(path);
        if (resource == null) {
            // Try loading from filesystem (development mode)
            File file = new File("gui" + exchange.getRequestURI().getPath());
            if (!file.exists()) {
                String msg = "404 Not Found";
                exchange.sendResponseHeaders(404, msg.length());
                exchange.getResponseBody().write(msg.getBytes());
                exchange.getResponseBody().close();
                return;
            }
            resource = new FileInputStream(file);
        }

        String contentType = guessContentType(path);
        byte[] bytes = resource.readAllBytes();
        exchange.getResponseHeaders().add("Content-Type", contentType);
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    // ── Full compiler pipeline (returns JSON result) ──────────
    public static String runPipeline(String source) {
        ErrorCollector errors = new ErrorCollector();

        // ── Phase 1 & 2: Lex + Parse ────────────────────────
        CharStream         input  = CharStreams.fromString(source);
        MiniLangLexer      lexer  = new MiniLangLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new CollectingErrorListener(errors, "Lexer"));

        CommonTokenStream  tokens = new CommonTokenStream(lexer);
        MiniLangParser     parser = new MiniLangParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new CollectingErrorListener(errors, "Parser"));

        MiniLangParser.ProgramContext parseTree = parser.program();

        if (errors.hasErrors()) {
            return buildJson("", errors.getSummary(), "null");
        }

        // ── Phase 3: Build AST ───────────────────────────────
        ASTBuilder    builder = new ASTBuilder(errors);
        ProgramNode   ast     = (ProgramNode) builder.visit(parseTree);

        // ── Phase 4: Semantic Analysis ───────────────────────
        SemanticAnalyzer semantic = new SemanticAnalyzer(errors);
        semantic.analyze(ast);

        if (errors.hasErrors()) {
            return buildJson("", errors.getSummary(), ASTSerializer.toJson(ast));
        }

        // ── Phase 5: Interpret ───────────────────────────────
        Interpreter interpreter = new Interpreter(errors);
        String output = interpreter.run(ast);

        if (errors.hasErrors()) {
            return buildJson(output, errors.getSummary(), ASTSerializer.toJson(ast));
        }

        return buildJson(output, "", ASTSerializer.toJson(ast));
    }

    // ── JSON builder (manual — no external library needed) ────
    // Returns: { "output": "...", "errors": "...", "ast": {...} }
    private static String buildJson(String output, String errorsStr, String astJson) {
        return "{"
                + "\"output\":" + jsonString(output) + ","
                + "\"errors\":" + jsonString(errorsStr) + ","
                + "\"ast\":"    + astJson
                + "}";
    }

    private static String jsonString(String s) {
        if (s == null) return "null";
        return "\"" + s
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")
                + "\"";
    }

    private static String guessContentType(String path) {
        if (path.endsWith(".html")) return "text/html; charset=utf-8";
        if (path.endsWith(".css"))  return "text/css; charset=utf-8";
        if (path.endsWith(".js"))   return "application/javascript; charset=utf-8";
        return "text/plain";
    }

    // ── Inner: ANTLR error listener that feeds ErrorCollector ─
    private static class CollectingErrorListener extends BaseErrorListener {
        private final ErrorCollector errors;
        private final String         phase;

        CollectingErrorListener(ErrorCollector errors, String phase) {
            this.errors = errors;
            this.phase  = phase;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            if (phase.equals("Lexer")) {
                errors.add(new LexerError(line, charPositionInLine, msg));
            } else {
                errors.add(new ParseError(line, charPositionInLine, msg));
            }
        }
    }
}
