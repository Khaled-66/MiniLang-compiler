package errors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// =============================================================================
// ErrorCollector.java  —  Aggregates all errors across all compiler phases
//
// WHY collect instead of throw immediately?
//   Naive compilers stop at the first error. Real compilers (like javac) collect
//   ALL errors and report them together. This is called "error recovery."
//   The programmer can then fix 10 errors in one go instead of running the
//   compiler 10 times.
//
// Usage pattern:
//   ErrorCollector errors = new ErrorCollector();
//   errors.add(new SemanticError(5, 3, "undefined variable 'x'"));
//   if (errors.hasErrors()) { ... report all ... }
// =============================================================================
public class ErrorCollector {

    // The single list that accumulates errors from all phases
    private final List<MiniLangError> errors = new ArrayList<>();

    // ── Add a single error ─────────────────────────────────────
    public void add(MiniLangError error) {
        errors.add(error);
    }

    // ── Bulk-add a list of errors (e.g. from a sub-phase) ─────
    public void addAll(List<MiniLangError> moreErrors) {
        errors.addAll(moreErrors);
    }

    // ── Query ──────────────────────────────────────────────────
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public int count() {
        return errors.size();
    }

    // Returns an unmodifiable view so callers can't accidentally mutate the list
    public List<MiniLangError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    // ── Formatted summary (used by CLI + server) ───────────────
    // Returns all errors as a single multi-line string
    public String getSummary() {
        if (!hasErrors()) return "No errors.";
        StringBuilder sb = new StringBuilder();
        for (MiniLangError e : errors) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    // ── Reset (for REPL/GUI re-runs without creating a new collector) ─────
    public void clear() {
        errors.clear();
    }
}
