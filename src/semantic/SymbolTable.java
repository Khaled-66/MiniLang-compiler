package semantic;

import java.util.HashMap;
import java.util.Map;

// =============================================================================
// SymbolTable.java  —  Scoped lookup table for all named entities
//
// Pipeline Position:  Created and used by SemanticAnalyzer
//
// CONCEPT: Lexical Scoping with a Scope Stack
//   Imagine scopes as a stack of dictionaries:
//     - Global scope (bottom)
//       └── Function scope
//           └── If-block scope (top — current)
//
//   When we look up "x", we start at the TOP (current scope) and walk
//   DOWN (toward global) until we find it, or report "undefined."
//
//   When we enter a { block }, we PUSH a new empty scope.
//   When we leave a { block }, we POP the top scope (destroying local variables).
//
// WHY a linked list of maps instead of one flat map?
//   One flat map can't support shadowing — the ability for a variable in
//   an inner scope to have the SAME NAME as one in an outer scope.
// =============================================================================
public class SymbolTable {

    // ── Scope Node (inner class) ───────────────────────────────
    // Each scope is a node in a linked list pointing to its parent.
    private static class Scope {
        final Map<String, Symbol> symbols = new HashMap<>();
        final Scope parent; // null = global scope (no parent)

        Scope(Scope parent) {
            this.parent = parent;
        }
    }

    // The current scope (top of the stack)
    private Scope current;

    // ── Lifecycle ─────────────────────────────────────────────

    public SymbolTable() {
        // Initialize with the global scope
        this.current = new Scope(null);
    }

    // Called when entering a { block }
    public void pushScope() {
        current = new Scope(current);
    }

    // Called when leaving a { block }
    public void popScope() {
        if (current.parent == null) {
            throw new IllegalStateException("Cannot pop the global scope.");
        }
        current = current.parent;
    }

    // ── Define ─────────────────────────────────────────────────
    // Add a new symbol to the CURRENT scope only
    public void define(Symbol symbol) {
        current.symbols.put(symbol.getName(), symbol);
    }

    // ── Lookup ─────────────────────────────────────────────────
    // Walk up the scope chain; returns null if not found anywhere
    public Symbol lookup(String name) {
        Scope scope = current;
        while (scope != null) {
            if (scope.symbols.containsKey(name)) {
                return scope.symbols.get(name);
            }
            scope = scope.parent;
        }
        return null; // Not found
    }

    // ── Check if defined in CURRENT scope only (for re-declaration detection) ─
    public boolean isDefinedInCurrentScope(String name) {
        return current.symbols.containsKey(name);
    }
}
