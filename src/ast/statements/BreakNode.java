package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// BreakNode.java  —  Represents:  break;
//
// Like ReturnNode, break works via a BreakException that propagates up
// until a while/for/switch handler catches it.
// =============================================================================
public class BreakNode extends ASTNode {

    public BreakNode(int line, int column) {
        super(line, column);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitBreak(this);
    }
}
