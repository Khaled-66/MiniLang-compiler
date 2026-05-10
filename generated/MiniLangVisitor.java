// Generated from MiniLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MiniLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MiniLangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#assignmentStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStmt(MiniLangParser.AssignmentStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#arrayAssignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAssignStmt(MiniLangParser.ArrayAssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#printStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(MiniLangParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#inputStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputStmt(MiniLangParser.InputStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MiniLangParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(MiniLangParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MiniLangParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(MiniLangParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(MiniLangParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#switchStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStmt(MiniLangParser.SwitchStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#caseClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseClause(MiniLangParser.CaseClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#defaultClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultClause(MiniLangParser.DefaultClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(MiniLangParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(MiniLangParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(MiniLangParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#breakStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(MiniLangParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#exprStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(MiniLangParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MiniLangParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(MiniLangParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(MiniLangParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(MiniLangParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(MiniLangParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(MiniLangParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExpr(MiniLangParser.AtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(MiniLangParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(MiniLangParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryMinusExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMinusExpr(MiniLangParser.UnaryMinusExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenAtom(MiniLangParser.ParenAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCallAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallAtom(MiniLangParser.FunctionCallAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccessAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessAtom(MiniLangParser.ArrayAccessAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayLiteralAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteralAtom(MiniLangParser.ArrayLiteralAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LiteralAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralAtom(MiniLangParser.LiteralAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VariableAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAtom(MiniLangParser.VariableAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(MiniLangParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link MiniLangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(MiniLangParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link MiniLangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(MiniLangParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolLiteral}
	 * labeled alternative in {@link MiniLangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(MiniLangParser.BoolLiteralContext ctx);
}