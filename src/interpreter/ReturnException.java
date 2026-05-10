package interpreter;

// =============================================================================
// ReturnException.java  —  Control flow mechanism for return statements
//
// WHY an exception?
//   When "return x;" executes deep inside nested if/while blocks, we need
//   to INSTANTLY unwind the entire call stack back to the function call site.
//   Java exceptions do exactly this — they propagate up automatically.
//
//   This is a standard interpreter implementation technique.
//   It is NOT an error — it's deliberate control flow, like a goto.
//
// NOTE: We extend RuntimeException (not Exception) to avoid
//       Java's checked-exception requirement everywhere.
// =============================================================================
public class ReturnException extends RuntimeException {

    private final MiniLangValue value;

    public ReturnException(MiniLangValue value) {
        // Don't bother filling in a stack trace — this is not a real error
        super(null, null, true, false);
        this.value = value;
    }

    public MiniLangValue getValue() {
        return value;
    }
}
