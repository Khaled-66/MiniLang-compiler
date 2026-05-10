package interpreter;

import ast.statements.FunctionDeclNode;

// =============================================================================
// MiniLangFunction.java  —  Runtime representation of a declared function
//
// Pipeline Position:  Created when the Interpreter visits a FunctionDeclNode,
//                     stored in the Environment, retrieved on function calls.
//
// WHY store the closure environment?
//   A "closure" is a function that remembers the scope where it was DEFINED,
//   not where it's CALLED from. This enables functions to access variables
//   from the outer scope they were created in.
//
//   Example:
//     x = 10;
//     function getX() { return x; }
//     getX();  // Returns 10 — it closes over x
// =============================================================================
public class MiniLangFunction {

    private final FunctionDeclNode declaration;   // The AST node (has params + body)
    private final Environment      closureEnv;    // The scope at definition time

    public MiniLangFunction(FunctionDeclNode declaration, Environment closureEnv) {
        this.declaration = declaration;
        this.closureEnv  = closureEnv;
    }

    public FunctionDeclNode getDeclaration() { return declaration; }
    public Environment      getClosureEnv()  { return closureEnv; }

    public String       getName()   { return declaration.getName(); }
    public int          getArity()  { return declaration.getParams().size(); }
}
