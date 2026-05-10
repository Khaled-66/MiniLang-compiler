package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// IfNode.java  —  Represents:  if (condition) { } else { }
//
// The elseBlock field is null when there is no else branch.
// The interpreter checks: if elseBlock != null → execute it on false condition.
//
// AST shape for:  if (x > 5) { print x; } else { print 0; }
//
//   IfNode
//   ├── condition:  BinaryOpNode (>)
//   │   ├── VariableNode (x)
//   │   └── LiteralNode (5)
//   ├── thenBlock:  BlockNode
//   │   └── PrintNode (x)
//   └── elseBlock:  BlockNode
//       └── PrintNode (0)
// =============================================================================
public class IfNode extends ASTNode {

    private final ASTNode condition;  // The boolean expression
    private final ASTNode thenBlock;  // Executed when condition is true
    private final ASTNode elseBlock;  // Executed when condition is false (may be null)

    public IfNode(int line, int column, ASTNode condition, ASTNode thenBlock, ASTNode elseBlock) {
        super(line, column);
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    public ASTNode getCondition()  { return condition; }
    public ASTNode getThenBlock()  { return thenBlock; }
    public ASTNode getElseBlock()  { return elseBlock; } // null = no else

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitIf(this);
    }
}
