package errors;

// =============================================================================
// LexerError.java  —  Error produced during tokenization
//
// Pipeline Position:  Source Code  →  [LEXER]  ← errors produced here
//
// Example:  Encountering '@' which is not a valid MiniLang character
//   Output: [Lexer] line 3:7 — Unexpected character: '@'
// =============================================================================
public class LexerError extends MiniLangError {

    public LexerError(int line, int column, String message) {
        super("Lexer", line, column, message);
    }
}
