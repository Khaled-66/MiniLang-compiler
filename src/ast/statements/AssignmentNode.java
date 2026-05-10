package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;
import ast.expressions.BinaryOpNode;

// =============================================================================
// AssignmentNode.java  —  Represents:  x = expr;
//
// Example source:  x = 3 + 4;
//
// AST shape:
//   AssignmentNode (varName="x")
//   └── BinaryOpNode (+)
//       ├── LiteralNode (3)
//       └── LiteralNode (4)
// =============================================================================
public class AssignmentNode extends ASTNode {

    // The name of the variable being assigned to (left-hand side)
    private final String varName;

    // The expression whose value will be stored (right-hand side)
    private final ASTNode value;

    public AssignmentNode(int line, int column, String varName, ASTNode value) {
        super(line, column);
        this.varName = varName;
        this.value   = value;
    }

    public String getVarName() { return varName; }
    public ASTNode getValue()  { return value; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitAssignment(this);
    }
}
