package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// ExprStatementNode.java  —  Wraps an expression used as a statement
//
// Example:  myFunc();   ← a function call with no assignment
//
// WHY needed?
//   The grammar distinguishes statements from expressions.
//   A bare function call (used for its side effects, not its return value)
//   needs to be a valid statement. This wrapper node bridges that gap.
// =============================================================================
public class ExprStatementNode extends ASTNode {

    private final ASTNode expression;

    public ExprStatementNode(int line, int column, ASTNode expression) {
        super(line, column);
        this.expression = expression;
    }

    public ASTNode getExpression() { return expression; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitExprStatement(this);
    }
}
