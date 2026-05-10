package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;
import ast.expressions.LiteralNode;

import java.util.List;

// =============================================================================
// SwitchNode.java  —  Represents:  switch (expr) { case v: ... default: ... }
//
// Inner class SwitchCase bundles each case's literal value + statements.
// =============================================================================
public class SwitchNode extends ASTNode {

    // A single case clause: case 1: stmt1; stmt2;
    public static class SwitchCase {
        private final LiteralNode value;       // The value to match (must be a literal)
        private final List<ASTNode> body;      // Statements in this case

        public SwitchCase(LiteralNode value, List<ASTNode> body) {
            this.value = value;
            this.body  = body;
        }

        public LiteralNode    getValue() { return value; }
        public List<ASTNode>  getBody()  { return body; }
    }

    private final ASTNode          subject;       // The expression being switched on
    private final List<SwitchCase> cases;         // Ordered list of case clauses
    private final List<ASTNode>    defaultBody;   // Statements in default (null = no default)

    public SwitchNode(int line, int column,
                      ASTNode subject,
                      List<SwitchCase> cases,
                      List<ASTNode> defaultBody) {
        super(line, column);
        this.subject     = subject;
        this.cases       = cases;
        this.defaultBody = defaultBody;
    }

    public ASTNode          getSubject()     { return subject; }
    public List<SwitchCase> getCases()       { return cases; }
    public List<ASTNode>    getDefaultBody() { return defaultBody; } // null = no default

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitSwitch(this);
    }
}
