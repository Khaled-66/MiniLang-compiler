package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

import java.util.List;

// =============================================================================
// BlockNode.java  —  Represents:  { statement1; statement2; ... }
//
// WHY does a block need its own node?
//   A block is not just a list of statements — it introduces a NEW SCOPE.
//   When the interpreter visits a BlockNode, it:
//     1. Creates a new child environment (new scope)
//     2. Executes all statements in that scope
//     3. Destroys the scope when the block ends
//   Variables declared inside a block are NOT visible outside it.
// =============================================================================
public class BlockNode extends ASTNode {

    private final List<ASTNode> statements;

    public BlockNode(int line, int column, List<ASTNode> statements) {
        super(line, column);
        this.statements = statements;
    }

    public List<ASTNode> getStatements() { return statements; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitBlock(this);
    }
}
