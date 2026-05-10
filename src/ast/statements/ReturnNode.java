package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// ReturnNode.java  —  Represents:  return expr;  or  return;
//
// WHY implement return via an exception?
//   A return statement can appear deep inside nested if/while/for blocks.
//   The cleanest way to unwind the call stack instantly is to throw a
//   ReturnException that carries the value, and catch it at the
//   function call site. This is the standard interpreter implementation strategy.
// =============================================================================
public class ReturnNode extends ASTNode {

    // The value expression — null means "return void"
    private final ASTNode value;

    public ReturnNode(int line, int column, ASTNode value) {
        super(line, column);
        this.value = value;
    }

    public ASTNode getValue() { return value; } // null = no return value

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitReturn(this);
    }
}
