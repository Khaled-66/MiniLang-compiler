package interpreter;

import java.util.HashMap;
import java.util.Map;

// =============================================================================
// Environment.java  —  Runtime scoped variable storage
//
// Pipeline Position:  Used by the Interpreter during execution
//
// IMPORTANT DISTINCTION vs. SymbolTable:
//   - SymbolTable  = used at COMPILE TIME (semantic analysis) — stores types
//   - Environment  = used at RUNTIME (interpretation) — stores actual VALUES
//
// The structure is identical — a linked list of maps (scope chain).
// Each function call and each { block } gets its own Environment scope.
//
// Concept: Lexical Scoping
//   "Lexical" because the scope is determined by where the code is WRITTEN,
//   not where it's CALLED from. This is the scoping model used by Java,
//   JavaScript, Python, and most modern languages.
// =============================================================================
public class Environment {

    // The variables defined in THIS scope only
    private final Map<String, MiniLangValue> values = new HashMap<>();

    // The parent scope (null = global scope)
    private final Environment parent;

    // ── Constructors ──────────────────────────────────────────
    public Environment() {
        this.parent = null; // Global scope
    }

    public Environment(Environment parent) {
        this.parent = parent; // Child scope
    }

    // ── Set (assign a variable) ───────────────────────────────
    // First looks for the variable in the scope chain (re-assignment).
    // If not found anywhere, defines it in the CURRENT scope (first assignment).
    public void set(String name, MiniLangValue value) {
        // Walk up the chain to find where this variable was first defined
        Environment scope = findScope(name);
        if (scope != null) {
            scope.values.put(name, value); // Re-assign in its original scope
        } else {
            values.put(name, value); // First assignment — define locally
        }
    }

    // ── Define (force-create in current scope) ─────────────────
    // Used for function parameters — we always want them local.
    public void define(String name, MiniLangValue value) {
        values.put(name, value);
    }

    // ── Get (read a variable) ─────────────────────────────────
    // Returns null if not found anywhere in the scope chain.
    public MiniLangValue get(String name) {
        if (values.containsKey(name)) {
            return values.get(name);
        }
        if (parent != null) {
            return parent.get(name);
        }
        return null; // Not found
    }

    // ── Helper: find which scope a variable lives in ──────────
    private Environment findScope(String name) {
        if (values.containsKey(name)) return this;
        if (parent != null) return parent.findScope(name);
        return null;
    }

    // ── Child scope factory ───────────────────────────────────
    public Environment createChild() {
        return new Environment(this);
    }
}
