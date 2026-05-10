package ast.expressions;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// UnaryOpNode.java  —  Represents:  -expr  or  !expr
//
// Examples:  -5   |   !isReady
//
// Operators stored:  "-" (negation)  |  "!" (logical not)
// =============================================================================
public class UnaryOpNode extends ASTNode {

    private final String  operator; // "-" or "!"
    private final ASTNode operand;

    public UnaryOpNode(int line, int column, String operator, ASTNode operand) {
        super(line, column);
        this.operator = operator;
        this.operand  = operand;
    }

    public String  getOperator() { return operator; }
    public ASTNode getOperand()  { return operand; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitUnaryOp(this);
    }
}
