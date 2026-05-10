package ast.expressions;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// VariableNode.java  —  Represents reading a variable's value
//
// Example:  x + 1  →  the "x" part is a VariableNode
//
// At runtime, the interpreter looks up the variable name in the current
// Environment scope chain to find its value.
// =============================================================================
public class VariableNode extends ASTNode {

    private final String name;

    public VariableNode(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitVariable(this);
    }
}
