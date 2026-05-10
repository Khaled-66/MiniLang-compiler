package interpreter;

// =============================================================================
// BreakException.java  —  Control flow mechanism for break statements
//
// Same principle as ReturnException — instantly exits the innermost
// while/for/switch when "break;" is encountered.
// =============================================================================
public class BreakException extends RuntimeException {

    public BreakException() {
        super(null, null, true, false);
    }
}
