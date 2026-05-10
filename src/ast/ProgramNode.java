package ast;

import java.util.List;

// =============================================================================
// ProgramNode.java  —  The root node of every AST
//
// Pipeline Position:  This is the TOP of the tree. The ASTBuilder creates
//                     exactly ONE ProgramNode per source file, and it contains
//                     all top-level statements as children.
//
// Example:
//   x = 5;
//   print x;
//
// AST:
//   ProgramNode
//   ├── AssignmentNode (x = 5)
//   └── PrintNode (x)
// =============================================================================
public class ProgramNode extends ASTNode {

    // The ordered list of top-level statements in the program
    private final List<ASTNode> statements;

    public ProgramNode(List<ASTNode> statements) {
        super(1, 0); // Program always starts at line 1
        this.statements = statements;
    }

    public List<ASTNode> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitProgram(this);
    }
}
