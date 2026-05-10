package errors;

// =============================================================================
// SemanticError.java  —  Error produced during semantic analysis
//
// Pipeline Position:  AST  →  [SEMANTIC ANALYZER]  ← errors produced here
//
// Examples:
//   - Using a variable before declaring it
//   - Passing wrong number of arguments to a function
//   - Type mismatch in an operation (e.g. "hello" * 3)
// =============================================================================
public class SemanticError extends MiniLangError {

    public SemanticError(int line, int column, String message) {
        super("Semantic", line, column, message);
    }
}
