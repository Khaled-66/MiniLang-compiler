package ast;

// =============================================================================
// ASTNode.java  —  Abstract base class for ALL AST nodes
//
// Pipeline Position:  Parse Tree  →  [AST BUILDER]  →  AST (made of these)
//
// WHY an abstract base class?
//   Every node in the tree — whether it's an assignment, a function call,
//   or a number literal — needs to know WHERE in the source file it came from.
//   By putting line/column in the base class, every node automatically has it.
//
// Concept: Abstract Syntax Tree (AST)
//   "Abstract" = strips away syntax noise (semicolons, parentheses, keywords)
//   "Syntax"   = still represents the STRUCTURE of the code
//   "Tree"     = hierarchical — nodes contain child nodes
// =============================================================================
public abstract class ASTNode {

    // Source location — set during ASTBuilder phase
    // Line is 1-indexed (line 1 is the first line of the file)
    // Column is 0-indexed (as ANTLR provides it)
    private int line;
    private int column;

    // ── Constructor ───────────────────────────────────────────
    protected ASTNode(int line, int column) {
        this.line   = line;
        this.column = column;
    }

    // Default constructor for nodes built without position info
    protected ASTNode() {
        this(-1, -1);
    }

    // ── Getters ───────────────────────────────────────────────
    public int getLine()   { return line; }
    public int getColumn() { return column; }

    // ── Position setter (used by ASTBuilder after construction) ──
    public void setPosition(int line, int column) {
        this.line   = line;
        this.column = column;
    }

    // ── Abstract visitor hook ─────────────────────────────────
    // Every concrete node must implement this so the Interpreter
    // (which uses the Visitor pattern) can call it.
    //
    // Concept: Visitor Pattern
    //   Each node says "accept a visitor and let it do its thing on me."
    //   The visitor (Interpreter) decides WHAT to do for each node type.
    //   This separates the tree STRUCTURE from the execution BEHAVIOR.
    public abstract <T> T accept(ASTVisitor<T> visitor);
}
