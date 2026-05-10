import errors.ErrorCollector;
import org.antlr.v4.runtime.*;
import ast.ProgramNode;
import interpreter.Interpreter;
import optimizer.Optimizer;
import semantic.SemanticAnalyzer;

// =============================================================================
// Main.java  —  Entry point for the MiniLang compiler/interpreter
//
// Two modes:
//   1. GUI mode (no args):          java Main
//      Starts the HTTP server and opens the browser IDE on port 8080.
//
//   2. CLI mode (with file arg):    java Main myprogram.ml
//      Runs the compiler pipeline directly in the terminal and prints output.
//
// Pipeline flow (both modes):
//   Source → Lexer → Parser → AST Builder → Semantic Analyzer
//   → Optimizer (constant folding) → Interpreter → Output
// =============================================================================
public class Main {

    public static void main(String[] args) throws Exception {

        // ── Mode 1: GUI (no arguments) ──────────────────────────────────
        if (args.length == 0) {
            System.out.println("Starting MiniLang IDE...");
            CompilerServer.start(3000);
            // Open the browser automatically on Windows
            Runtime.getRuntime().exec("cmd /c start http://localhost:3000");
            return;
        }

        // ── Mode 2: CLI (file argument) ─────────────────────────────────
        String filePath = args[0];
        ErrorCollector errors = new ErrorCollector();

        // ── Step 1 & 2: Lex + Parse ────────────────────────────────────
        CharStream input;
        try {
            input = CharStreams.fromFileName(filePath);
        } catch (Exception e) {
            System.err.println("Error: Cannot read file '" + filePath + "'");
            System.exit(1);
            return;
        }

        MiniLangLexer  lexer  = new MiniLangLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new CliErrorListener(errors, "Lexer"));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniLangParser    parser = new MiniLangParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new CliErrorListener(errors, "Parser"));

        MiniLangParser.ProgramContext parseTree = parser.program();

        if (errors.hasErrors()) {
            System.err.println(errors.getSummary());
            System.exit(1);
            return;
        }

        // ── Step 3: Build AST ──────────────────────────────────────────
        ASTBuilder  builder = new ASTBuilder(errors);
        ProgramNode ast     = (ProgramNode) builder.visit(parseTree);

        // ── Step 4: Semantic Analysis ──────────────────────────────────
        SemanticAnalyzer semantic = new SemanticAnalyzer(errors);
        semantic.analyze(ast);

        if (errors.hasErrors()) {
            System.err.println(errors.getSummary());
            System.exit(1);
            return;
        }

        // ── Step 5: Optimize (constant folding) ─── (bonus) ───────────
        Optimizer optimizer = new Optimizer();
        ast = optimizer.optimize(ast);

        // ── Step 6: Interpret ──────────────────────────────────────────
        Interpreter interpreter = new Interpreter(errors);
        String output = interpreter.run(ast);

        if (!output.isEmpty()) {
            System.out.println(output);
        }

        if (errors.hasErrors()) {
            System.err.println(errors.getSummary());
            System.exit(1);
        }
    }

    // ── ANTLR error listener for CLI mode ───────────────────────────────
    // Collects errors into the ErrorCollector instead of printing immediately
    private static class CliErrorListener extends BaseErrorListener {
        private final ErrorCollector errors;
        private final String         phase;

        CliErrorListener(ErrorCollector errors, String phase) {
            this.errors = errors;
            this.phase  = phase;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            if (phase.equals("Lexer")) {
                errors.add(new errors.LexerError(line, charPositionInLine, msg));
            } else {
                errors.add(new errors.ParseError(line, charPositionInLine, msg));
            }
        }
    }
}
