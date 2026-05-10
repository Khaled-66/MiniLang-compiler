package ast.expressions;

import ast.ASTNode;
import ast.ASTVisitor;

import java.util.List;

// =============================================================================
// FunctionCallNode.java  —  Represents:  name(arg1, arg2, ...)
//
// Example:  add(3, x + 1)
//
// The interpreter:
//   1. Evaluates each argument expression
//   2. Looks up the function in the environment by name
//   3. Creates a new child scope
//   4. Binds each param name to its evaluated argument
//   5. Executes the function body
//   6. Returns the value from a ReturnException (or null for void)
// =============================================================================
public class FunctionCallNode extends ASTNode {

    private final String        name;       // Function name
    private final List<ASTNode> arguments;  // Evaluated left-to-right

    public FunctionCallNode(int line, int column, String name, List<ASTNode> arguments) {
        super(line, column);
        this.name      = name;
        this.arguments = arguments;
    }

    public String        getName()      { return name; }
    public List<ASTNode> getArguments() { return arguments; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitFunctionCall(this);
    }
}
