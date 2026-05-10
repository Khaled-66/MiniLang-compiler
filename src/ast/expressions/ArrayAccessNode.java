package ast.expressions;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// ArrayAccessNode.java  —  Represents:  arr[i]
//
// The interpreter evaluates the index expression, validates bounds,
// then reads the element from the array's Java ArrayList.
// =============================================================================
public class ArrayAccessNode extends ASTNode {

    private final String  varName; // The array variable name
    private final ASTNode index;   // The index expression

    public ArrayAccessNode(int line, int column, String varName, ASTNode index) {
        super(line, column);
        this.varName = varName;
        this.index   = index;
    }

    public String  getVarName() { return varName; }
    public ASTNode getIndex()   { return index; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitArrayAccess(this);
    }
}
