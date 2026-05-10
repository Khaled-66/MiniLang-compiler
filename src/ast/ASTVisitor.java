package ast;

import ast.expressions.*;
import ast.statements.*;

// =============================================================================
// ASTVisitor.java  —  Generic visitor interface for walking the AST
//
// Pipeline Position:  Used by BOTH the Semantic Analyzer and the Interpreter
//
// WHY generic <T>?
//   - The SemanticAnalyzer visits nodes and returns MiniLangType (type info)
//   - The Interpreter visits nodes and returns MiniLangValue (runtime values)
//   - The Optimizer visits nodes and returns ASTNode (transformed nodes)
//   One interface handles all three by being generic.
//
// Every time you add a new AST node class, add a corresponding visit method here.
// =============================================================================
public interface ASTVisitor<T> {

    // ── Program ────────────────────────────────────────────────
    T visitProgram(ProgramNode node);

    // ── Statements ────────────────────────────────────────────
    T visitAssignment(AssignmentNode node);
    T visitArrayAssignment(ArrayAssignmentNode node);
    T visitPrint(PrintNode node);
    T visitInput(InputNode node);
    T visitIf(IfNode node);
    T visitWhile(WhileNode node);
    T visitFor(ForNode node);
    T visitSwitch(SwitchNode node);
    T visitFunctionDecl(FunctionDeclNode node);
    T visitReturn(ReturnNode node);
    T visitBreak(BreakNode node);
    T visitBlock(BlockNode node);
    T visitExprStatement(ExprStatementNode node);

    // ── Expressions ───────────────────────────────────────────
    T visitBinaryOp(BinaryOpNode node);
    T visitUnaryOp(UnaryOpNode node);
    T visitLiteral(LiteralNode node);
    T visitVariable(VariableNode node);
    T visitFunctionCall(FunctionCallNode node);
    T visitArrayLiteral(ArrayLiteralNode node);
    T visitArrayAccess(ArrayAccessNode node);
}
