package errors;

// =============================================================================
// MiniLangError.java  —  Base class for all compiler errors
//
// Pipeline Position:  Used by ALL phases (Lexer, Parser, Semantic, Runtime)
//
// WHY a base class?
//   Every error needs the same three pieces of information:
//     1. WHERE it happened (line + column)
//     2. WHAT went wrong (message)
//     3. WHICH phase found it (phase name)
//   A base class enforces this contract so every error is consistent.
// =============================================================================
public abstract class MiniLangError {

    // The name of the compiler phase that found this error
    // e.g. "Lexer", "Parser", "Semantic", "Runtime"
    private final String phase;

    // Line number in the source file (1-indexed, matches what the user sees)
    private final int line;

    // Column (character position on the line, 0-indexed from ANTLR)
    private final int column;

    // Human-readable description of what went wrong
    private final String message;

    // ── Constructor ───────────────────────────────────────────
    protected MiniLangError(String phase, int line, int column, String message) {
        this.phase   = phase;
        this.line    = line;
        this.column  = column;
        this.message = message;
    }

    // ── Getters ───────────────────────────────────────────────
    public String getPhase()   { return phase; }
    public int    getLine()    { return line; }
    public int    getColumn()  { return column; }
    public String getMessage() { return message; }

    // ── Display ───────────────────────────────────────────────
    // Formats the error as:  [Semantic] line 5:3 — Type mismatch: ...
    @Override
    public String toString() {
        return String.format("[%s] line %d:%d - %s", phase, line, column, message);
    }
}
