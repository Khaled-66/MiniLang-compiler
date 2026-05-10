package semantic;

import java.util.List;

// =============================================================================
// Symbol.java  —  A single entry in the Symbol Table
//
// Pipeline Position:  Created and read by SemanticAnalyzer
//
// A symbol represents anything that has a NAME in the source code:
//   - A variable   (name="x", type=INT, isFunction=false)
//   - A function   (name="add", type=INT, isFunction=true, paramTypes=[INT, INT])
//
// The paramTypes list is only meaningful for function symbols.
// =============================================================================
public class Symbol {

    private final String          name;
    private final MiniLangType    type;        // Return type (or variable type)
    private final boolean         isFunction;
    private final List<String>    paramNames;  // Function parameter names (null for variables)

    // ── Constructor for VARIABLES ─────────────────────────────
    public Symbol(String name, MiniLangType type) {
        this.name       = name;
        this.type       = type;
        this.isFunction = false;
        this.paramNames = null;
    }

    // ── Constructor for FUNCTIONS ─────────────────────────────
    public Symbol(String name, MiniLangType returnType, List<String> paramNames) {
        this.name       = name;
        this.type       = returnType;
        this.isFunction = true;
        this.paramNames = paramNames;
    }

    // ── Getters ───────────────────────────────────────────────
    public String          getName()       { return name; }
    public MiniLangType    getType()       { return type; }
    public boolean         isFunction()    { return isFunction; }
    public List<String>    getParamNames() { return paramNames; }

    public int getParamCount() {
        return paramNames == null ? 0 : paramNames.size();
    }
}
