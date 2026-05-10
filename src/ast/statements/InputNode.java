package ast.statements;

import ast.ASTNode;
import ast.ASTVisitor;

// =============================================================================
// InputNode.java  —  Represents:  x = input("prompt");
//
// Reads a line from stdin and assigns it to a variable.
// The interpreter will parse the string to int/float if possible,
// otherwise store it as a string.
// =============================================================================
public class InputNode extends ASTNode {

    private final String varName; // Variable to store the result in
    private final String prompt;  // The string shown to the user (e.g. "Enter a number: ")

    public InputNode(int line, int column, String varName, String prompt) {
        super(line, column);
        this.varName = varName;
        // Strip surrounding quotes from the grammar's STRING token
        this.prompt  = prompt.substring(1, prompt.length() - 1);
    }

    public String getVarName() { return varName; }
    public String getPrompt()  { return prompt; }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitInput(this);
    }
}
