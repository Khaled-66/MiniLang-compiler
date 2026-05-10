package ast.expressions;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// BinaryOpNode.java  —  Represents any two-operand expression
//
// Examples:  x + 1   |   a > b   |   x && y   |   x == "hello"
//
// WHY one class for all binary ops?
//   All binary operations share the same structure: left OP right.
//   Instead of 12 separate classes (AddNode, SubNode, GtNode, etc.),
//   we use one class and store the operator as a string.
//   The interpreter switches on the operator string.
// =============================================================================
public class BinaryOpNode extends ASTNode {

    // The operator as a string: "+", "-", "*", "/", ">", "<", ">=", "<=", "==", "!=", "&&", "||"
    private final String operator;
    private final ASTNode left;
    private final ASTNode right;

    public BinaryOpNode(int line, int column, String operator, ASTNode left, ASTNode right) {
        super(line, column);
        this.operator = operator;
        this.left     = left;
        this.right    = right;
    }

    public String  getOperator() { return operator; }
    public ASTNode getLeft()     { return left; }
    public ASTNode getRight()    { return right; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitBinaryOp(this);
    }
}
