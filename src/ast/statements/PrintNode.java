package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// PrintNode.java  —  Represents:  print expr;
//
// Example source:  print x + 1;
//
// AST shape:
//   PrintNode
//   └── BinaryOpNode (+)
//       ├── VariableNode (x)
//       └── LiteralNode (1)
// =============================================================================
public class PrintNode extends ASTNode {

    // The expression whose value will be printed to stdout
    private final ASTNode expression;

    public PrintNode(int line, int column, ASTNode expression) {
        super(line, column);
        this.expression = expression;
    }

    public ASTNode getExpression() { return expression; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitPrint(this);
    }
}
