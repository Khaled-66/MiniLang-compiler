package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

import java.util.List;

// =============================================================================
// FunctionDeclNode.java  —  Represents:  function name(a, b) { ... }
//
// Stores the function's name, parameter names, and body block.
// At runtime, this node is NOT executed immediately — it registers the
// function in the current environment so it can be called later.
// =============================================================================
public class FunctionDeclNode extends ASTNode {

    private final String       name;    // Function name (e.g. "add")
    private final List<String> params;  // Parameter names (e.g. ["a", "b"])
    private final ASTNode      body;    // The block of statements to execute

    public FunctionDeclNode(int line, int column,
                             String name,
                             List<String> params,
                             ASTNode body) {
        super(line, column);
        this.name   = name;
        this.params = params;
        this.body   = body;
    }

    public String       getName()   { return name; }
    public List<String> getParams() { return params; }
    public ASTNode      getBody()   { return body; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitFunctionDecl(this);
    }
}
