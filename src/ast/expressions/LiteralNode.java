package ast.expressions;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// LiteralNode.java  —  Represents a concrete value written in source code
//
// Examples:  42   |   3.14   |   true   |   "hello"
//
// The value is stored as a Java Object:
//   - Integer  for int literals    (42)
//   - Double   for float literals  (3.14)
//   - Boolean  for bool literals   (true, false)
//   - String   for string literals ("hello")
//
// WHY Object and not separate classes?
//   These four types are the leaf nodes of every expression tree.
//   Having one class with an Object field is simpler than four nearly
//   identical classes. The type is checked at runtime when needed.
// =============================================================================
public class LiteralNode extends ASTNode {

    // The actual Java value (Integer, Double, Boolean, or String)
    private final Object value;

    // Convenience constructors for each supported type

    public LiteralNode(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    public LiteralNode(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    public LiteralNode(int line, int column, boolean value) {
        super(line, column);
        this.value = value;
    }

    public LiteralNode(int line, int column, String value) {
        super(line, column);
        this.value = value;
    }

    public Object getValue() { return value; }

    // Type helpers so the interpreter doesn't need instanceof everywhere
    public boolean isInt()     { return value instanceof Integer; }
    public boolean isFloat()   { return value instanceof Double; }
    public boolean isBool()    { return value instanceof Boolean; }
    public boolean isString()  { return value instanceof String; }
    public boolean isNumeric() { return isInt() || isFloat(); }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitLiteral(this);
    }
}
