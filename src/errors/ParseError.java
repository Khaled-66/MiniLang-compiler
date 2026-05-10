package errors;

// =============================================================================
// ParseError.java  —  Error produced during syntax analysis
//
// Pipeline Position:  Tokens  →  [PARSER]  ← errors produced here
//
// Example:  Missing closing parenthesis
//   Output: [Parser] line 4:10 — missing ')' after expression
// =============================================================================
public class ParseError extends MiniLangError {

    public ParseError(int line, int column, String message) {
        super("Parser", line, column, message);
    }
}
