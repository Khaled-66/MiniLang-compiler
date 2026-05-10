package ast.expressions;

import ast.ASTNode;
import ast.ASTVisitor;

import java.util.List;

// =============================================================================
// ArrayLiteralNode.java  —  Represents:  [1, 2, 3]  or  []
//
// At runtime, the interpreter evaluates each element expression in order
// and stores them in a Java ArrayList.
// =============================================================================
public class ArrayLiteralNode extends ASTNode {

    private final List<ASTNode> elements; // The expressions inside the brackets

    public ArrayLiteralNode(int line, int column, List<ASTNode> elements) {
        super(line, column);
        this.elements = elements;
    }

    public List<ASTNode> getElements() { return elements; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitArrayLiteral(this);
    }
}
