package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// WhileNode.java  —  Represents:  while (condition) { }
// =============================================================================
public class WhileNode extends ASTNode {

    private final ASTNode condition;  // Loop guard expression
    private final ASTNode body;       // Block to repeat

    public WhileNode(int line, int column, ASTNode condition, ASTNode body) {
        super(line, column);
        this.condition = condition;
        this.body      = body;
    }

    public ASTNode getCondition() { return condition; }
    public ASTNode getBody()      { return body; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitWhile(this);
    }
}
