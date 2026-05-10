// Generated from MiniLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniLangParser}.
 */
public interface MiniLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MiniLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MiniLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MiniLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MiniLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#assignmentStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStmt(MiniLangParser.AssignmentStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#assignmentStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStmt(MiniLangParser.AssignmentStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#arrayAssignStmt}.
	 * @param ctx the parse tree
	 */
	void enterArrayAssignStmt(MiniLangParser.ArrayAssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#arrayAssignStmt}.
	 * @param ctx the parse tree
	 */
	void exitArrayAssignStmt(MiniLangParser.ArrayAssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(MiniLangParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(MiniLangParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#inputStmt}.
	 * @param ctx the parse tree
	 */
	void enterInputStmt(MiniLangParser.InputStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#inputStmt}.
	 * @param ctx the parse tree
	 */
	void exitInputStmt(MiniLangParser.InputStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MiniLangParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MiniLangParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MiniLangParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MiniLangParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MiniLangParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MiniLangParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(MiniLangParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(MiniLangParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(MiniLangParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(MiniLangParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStmt(MiniLangParser.SwitchStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStmt(MiniLangParser.SwitchStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#caseClause}.
	 * @param ctx the parse tree
	 */
	void enterCaseClause(MiniLangParser.CaseClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#caseClause}.
	 * @param ctx the parse tree
	 */
	void exitCaseClause(MiniLangParser.CaseClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#defaultClause}.
	 * @param ctx the parse tree
	 */
	void enterDefaultClause(MiniLangParser.DefaultClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#defaultClause}.
	 * @param ctx the parse tree
	 */
	void exitDefaultClause(MiniLangParser.DefaultClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(MiniLangParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(MiniLangParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(MiniLangParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(MiniLangParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MiniLangParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MiniLangParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MiniLangParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MiniLangParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MiniLangParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MiniLangParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MiniLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MiniLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(MiniLangParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(MiniLangParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(MiniLangParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(MiniLangParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(MiniLangParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(MiniLangParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(MiniLangParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(MiniLangParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(MiniLangParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(MiniLangParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(MiniLangParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(MiniLangParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(MiniLangParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(MiniLangParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(MiniLangParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(MiniLangParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryMinusExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpr(MiniLangParser.UnaryMinusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryMinusExpr}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpr(MiniLangParser.UnaryMinusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterParenAtom(MiniLangParser.ParenAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitParenAtom(MiniLangParser.ParenAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallAtom(MiniLangParser.FunctionCallAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallAtom(MiniLangParser.FunctionCallAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccessAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessAtom(MiniLangParser.ArrayAccessAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessAtom(MiniLangParser.ArrayAccessAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLiteralAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteralAtom(MiniLangParser.ArrayLiteralAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLiteralAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteralAtom(MiniLangParser.ArrayLiteralAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterLiteralAtom(MiniLangParser.LiteralAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitLiteralAtom(MiniLangParser.LiteralAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterVariableAtom(MiniLangParser.VariableAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableAtom}
	 * labeled alternative in {@link MiniLangParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitVariableAtom(MiniLangParser.VariableAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(MiniLangParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(MiniLangParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link MiniLangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(MiniLangParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link MiniLangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(MiniLangParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link MiniLangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(MiniLangParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link MiniLangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(MiniLangParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolLiteral}
	 * labeled alternative in {@link MiniLangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(MiniLangParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolLiteral}
	 * labeled alternative in {@link MiniLangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(MiniLangParser.BoolLiteralContext ctx);
}