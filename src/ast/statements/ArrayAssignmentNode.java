package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// ArrayAssignmentNode.java  —  Represents:  arr[i] = expr;
//
// Example source:  items[0] = 42;
//
// AST shape:
//   ArrayAssignmentNode (varName="items")
//   ├── index:  LiteralNode (0)
//   └── value:  LiteralNode (42)
// =============================================================================
public class ArrayAssignmentNode extends ASTNode {

    private final String  varName;  // Name of the array variable
    private final ASTNode index;    // Expression for the index
    private final ASTNode value;    // Expression to store

    public ArrayAssignmentNode(int line, int column, String varName, ASTNode index, ASTNode value) {
        super(line, column);
        this.varName = varName;
        this.index   = index;
        this.value   = value;
    }

    public String  getVarName() { return varName; }
    public ASTNode getIndex()   { return index; }
    public ASTNode getValue()   { return value; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitArrayAssignment(this);
    }
}
