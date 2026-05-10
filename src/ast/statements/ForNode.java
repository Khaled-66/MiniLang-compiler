package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// ForNode.java  —  Represents:  for (i = 0; i < 10; i = i + 1) { }
//
// Fields:
//   initVar   — the loop variable name (e.g. "i")
//   initValue — the starting value expression (e.g. 0)
//   condition — the loop guard (e.g. i < 10)
//   updateVar — the variable being updated (usually same as initVar)
//   updateVal — the update expression (e.g. i + 1)
//   body      — the block to execute each iteration
//
// WHY store init/update as separate fields rather than nested statements?
//   The for-loop header has a very rigid structure: one assignment, one
//   condition, one update. Storing them explicitly makes the interpreter
//   simpler and prevents misuse (no nested ifs in the header).
// =============================================================================
public class ForNode extends ASTNode {

    private final String  initVar;
    private final ASTNode initValue;
    private final ASTNode condition;
    private final String  updateVar;
    private final ASTNode updateValue;
    private final ASTNode body;

    public ForNode(int line, int column,
                   String initVar, ASTNode initValue,
                   ASTNode condition,
                   String updateVar, ASTNode updateValue,
                   ASTNode body) {
        super(line, column);
        this.initVar     = initVar;
        this.initValue   = initValue;
        this.condition   = condition;
        this.updateVar   = updateVar;
        this.updateValue = updateValue;
        this.body        = body;
    }

    public String  getInitVar()     { return initVar; }
    public ASTNode getInitValue()   { return initValue; }
    public ASTNode getCondition()   { return condition; }
    public String  getUpdateVar()   { return updateVar; }
    public ASTNode getUpdateValue() { return updateValue; }
    public ASTNode getBody()        { return body; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitFor(this);
    }
}
