package errors;

// =============================================================================
// RuntimeError.java  —  Error produced during interpretation/execution
//
// Pipeline Position:  [INTERPRETER]  ← errors produced here
//
// Examples:
//   - Division by zero at runtime
//   - Array index out of bounds
//   - Stack overflow from infinite recursion
// =============================================================================
public class RuntimeError extends MiniLangError {

    public RuntimeError(int line, int column, String message) {
        super("Runtime", line, column, message);
    }

    // ── Convenience constructor when we don't have exact location ─────────
    // Used for runtime errors that are hard to trace back to a line
    public RuntimeError(String message) {
        super("Runtime", -1, -1, message);
    }
}
