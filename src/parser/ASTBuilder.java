// ASTBuilder lives in the DEFAULT package (no package declaration)
// because ANTLR generated files (MiniLangParser, MiniLangLexer, etc.)
// are also in the default package. Java does not allow importing from
// the default package into a named package — so we match them.

import ast.*;
import ast.expressions.*;
import ast.statements.*;
import errors.ErrorCollector;
import errors.ParseError;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

// =============================================================================
// ASTBuilder.java  —  Converts the ANTLR Parse Tree into our custom AST
//
// Pipeline Position:  Parse Tree  →  [ASTBuilder]  →  AST
//
// HOW it works:
//   - Extends MiniLangBaseVisitor<ASTNode>
//   - Visits each node in the ANTLR parse tree (top-down)
//   - Returns a corresponding custom ASTNode for each
//   - The result of visiting the root (program) is a ProgramNode
//
// WHY convert to a custom AST instead of using the parse tree directly?
//   1. Parse trees contain grammar noise (semicolons, parentheses, keywords)
//      that the interpreter doesn't need.
//   2. Our custom nodes have typed fields (getCondition(), getBody())
//      instead of generic getChild(0), getChild(1).
//   3. Semantic analysis and optimization are much cleaner on a typed AST.
// =============================================================================
public class ASTBuilder extends MiniLangBaseVisitor<ASTNode> {

    private final ErrorCollector errors;

    public ASTBuilder(ErrorCollector errors) {
        this.errors = errors;
    }

    // ── Helper: get line/column from any parser rule context ──
    private int line(ParserRuleContext ctx)   { return ctx.getStart().getLine(); }
    private int column(ParserRuleContext ctx) { return ctx.getStart().getCharPositionInLine(); }

    // ─────────────────────────────────────────────────────────────
    // PROGRAM
    // ─────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitProgram(MiniLangParser.ProgramContext ctx) {
        List<ASTNode> statements = new ArrayList<>();
        for (MiniLangParser.StatementContext stmt : ctx.statement()) {
            ASTNode node = visit(stmt);
            if (node != null) statements.add(node);
        }
        return new ProgramNode(statements);
    }

    // ─────────────────────────────────────────────────────────────
    // STATEMENTS
    // ─────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitAssignmentStmt(MiniLangParser.AssignmentStmtContext ctx) {
        String  varName = ctx.ID().getText();
        ASTNode value   = visit(ctx.expr());
        return new AssignmentNode(line(ctx), column(ctx), varName, value);
    }

    @Override
    public ASTNode visitArrayAssignStmt(MiniLangParser.ArrayAssignStmtContext ctx) {
        String  varName = ctx.ID().getText();
        ASTNode index   = visit(ctx.expr(0));
        ASTNode value   = visit(ctx.expr(1));
        return new ArrayAssignmentNode(line(ctx), column(ctx), varName, index, value);
    }

    @Override
    public ASTNode visitPrintStmt(MiniLangParser.PrintStmtContext ctx) {
        ASTNode expression = visit(ctx.expr());
        return new PrintNode(line(ctx), column(ctx), expression);
    }

    @Override
    public ASTNode visitInputStmt(MiniLangParser.InputStmtContext ctx) {
        String varName = ctx.ID().getText();
        String prompt  = ctx.STRING().getText(); // still has surrounding quotes; InputNode strips them
        return new InputNode(line(ctx), column(ctx), varName, prompt);
    }

    @Override
    public ASTNode visitIfStmt(MiniLangParser.IfStmtContext ctx) {
        ASTNode condition = visit(ctx.expr());
        ASTNode thenBlock = visit(ctx.block(0));
        // (ELSE block)? — block(1) exists only when there's an else
        ASTNode elseBlock = ctx.block().size() > 1 ? visit(ctx.block(1)) : null;
        return new IfNode(line(ctx), column(ctx), condition, thenBlock, elseBlock);
    }

    @Override
    public ASTNode visitWhileStmt(MiniLangParser.WhileStmtContext ctx) {
        ASTNode condition = visit(ctx.expr());
        ASTNode body      = visit(ctx.block());
        return new WhileNode(line(ctx), column(ctx), condition, body);
    }

    @Override
    public ASTNode visitForStmt(MiniLangParser.ForStmtContext ctx) {
        // forInit:   ID '=' expr ';'
        MiniLangParser.ForInitContext init = ctx.forInit();
        String  initVar   = init.ID().getText();
        ASTNode initValue = visit(init.expr());

        // middle expr = the condition
        ASTNode condition = visit(ctx.expr());

        // forUpdate:  ID '=' expr
        MiniLangParser.ForUpdateContext update = ctx.forUpdate();
        String  updateVar   = update.ID().getText();
        ASTNode updateValue = visit(update.expr());

        ASTNode body = visit(ctx.block());

        return new ForNode(line(ctx), column(ctx),
                initVar, initValue,
                condition,
                updateVar, updateValue,
                body);
    }

    @Override
    public ASTNode visitSwitchStmt(MiniLangParser.SwitchStmtContext ctx) {
        ASTNode subject = visit(ctx.expr());

        List<SwitchNode.SwitchCase> cases = new ArrayList<>();
        for (MiniLangParser.CaseClauseContext c : ctx.caseClause()) {
            // Visit the literal node
            LiteralNode caseValue = (LiteralNode) visit(c.literal());
            List<ASTNode> body = new ArrayList<>();
            for (MiniLangParser.StatementContext s : c.statement()) {
                body.add(visit(s));
            }
            cases.add(new SwitchNode.SwitchCase(caseValue, body));
        }

        List<ASTNode> defaultBody = null;
        if (ctx.defaultClause() != null) {
            defaultBody = new ArrayList<>();
            for (MiniLangParser.StatementContext s : ctx.defaultClause().statement()) {
                defaultBody.add(visit(s));
            }
        }

        return new SwitchNode(line(ctx), column(ctx), subject, cases, defaultBody);
    }

    @Override
    public ASTNode visitFunctionDecl(MiniLangParser.FunctionDeclContext ctx) {
        String name = ctx.ID().getText();

        List<String> params = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (var p : ctx.paramList().ID()) {
                params.add(p.getText());
            }
        }

        ASTNode body = visit(ctx.block());
        return new FunctionDeclNode(line(ctx), column(ctx), name, params, body);
    }

    @Override
    public ASTNode visitReturnStmt(MiniLangParser.ReturnStmtContext ctx) {
        ASTNode value = ctx.expr() != null ? visit(ctx.expr()) : null;
        return new ReturnNode(line(ctx), column(ctx), value);
    }

    @Override
    public ASTNode visitBreakStmt(MiniLangParser.BreakStmtContext ctx) {
        return new BreakNode(line(ctx), column(ctx));
    }

    @Override
    public ASTNode visitExprStmt(MiniLangParser.ExprStmtContext ctx) {
        ASTNode expr = visit(ctx.expr());
        return new ExprStatementNode(line(ctx), column(ctx), expr);
    }

    @Override
    public ASTNode visitBlock(MiniLangParser.BlockContext ctx) {
        List<ASTNode> stmts = new ArrayList<>();
        for (MiniLangParser.StatementContext s : ctx.statement()) {
            stmts.add(visit(s));
        }
        return new BlockNode(line(ctx), column(ctx), stmts);
    }

    // ─────────────────────────────────────────────────────────────
    // EXPRESSIONS
    // ─────────────────────────────────────────────────────────────

    @Override
    public ASTNode visitOrExpr(MiniLangParser.OrExprContext ctx) {
        return new BinaryOpNode(line(ctx), column(ctx), "||", visit(ctx.expr(0)), visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitAndExpr(MiniLangParser.AndExprContext ctx) {
        return new BinaryOpNode(line(ctx), column(ctx), "&&", visit(ctx.expr(0)), visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitEqualityExpr(MiniLangParser.EqualityExprContext ctx) {
        return new BinaryOpNode(line(ctx), column(ctx), ctx.op.getText(), visit(ctx.expr(0)), visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitRelationalExpr(MiniLangParser.RelationalExprContext ctx) {
        return new BinaryOpNode(line(ctx), column(ctx), ctx.op.getText(), visit(ctx.expr(0)), visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitAddSubExpr(MiniLangParser.AddSubExprContext ctx) {
        return new BinaryOpNode(line(ctx), column(ctx), ctx.op.getText(), visit(ctx.expr(0)), visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitMulDivExpr(MiniLangParser.MulDivExprContext ctx) {
        return new BinaryOpNode(line(ctx), column(ctx), ctx.op.getText(), visit(ctx.expr(0)), visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitNotExpr(MiniLangParser.NotExprContext ctx) {
        return new UnaryOpNode(line(ctx), column(ctx), "!", visit(ctx.expr()));
    }

    @Override
    public ASTNode visitUnaryMinusExpr(MiniLangParser.UnaryMinusExprContext ctx) {
        // Visits atom() — not expr() — so -5+3 binds as (-5)+3
        return new UnaryOpNode(line(ctx), column(ctx), "-", visit(ctx.atom()));
    }

    // ── AtomExpr: the 'atom' alternative inside expr — just unwrap
    @Override
    public ASTNode visitAtomExpr(MiniLangParser.AtomExprContext ctx) {
        return visit(ctx.atom());
    }


    @Override
    public ASTNode visitParenAtom(MiniLangParser.ParenAtomContext ctx) {
        return visit(ctx.expr()); // parentheses are transparent
    }

    @Override
    public ASTNode visitFunctionCallAtom(MiniLangParser.FunctionCallAtomContext ctx) {
        String name = ctx.ID().getText();
        List<ASTNode> args = new ArrayList<>();
        if (ctx.argList() != null) {
            for (MiniLangParser.ExprContext e : ctx.argList().expr()) {
                args.add(visit(e));
            }
        }
        return new FunctionCallNode(line(ctx), column(ctx), name, args);
    }

    @Override
    public ASTNode visitArrayAccessAtom(MiniLangParser.ArrayAccessAtomContext ctx) {
        return new ArrayAccessNode(line(ctx), column(ctx), ctx.ID().getText(), visit(ctx.expr()));
    }

    @Override
    public ASTNode visitArrayLiteralAtom(MiniLangParser.ArrayLiteralAtomContext ctx) {
        List<ASTNode> elements = new ArrayList<>();
        if (ctx.argList() != null) {
            for (MiniLangParser.ExprContext e : ctx.argList().expr()) {
                elements.add(visit(e));
            }
        }
        return new ArrayLiteralNode(line(ctx), column(ctx), elements);
    }

    @Override
    public ASTNode visitLiteralAtom(MiniLangParser.LiteralAtomContext ctx) {
        return visit(ctx.literal());
    }

    @Override
    public ASTNode visitVariableAtom(MiniLangParser.VariableAtomContext ctx) {
        return new VariableNode(line(ctx), column(ctx), ctx.ID().getText());
    }

    // ─────────────────────────────────────────────────────────────
    // LITERALS
    // ─────────────────────────────────────────────────────────────



    @Override
    public ASTNode visitNumberLiteral(MiniLangParser.NumberLiteralContext ctx) {
        String text = ctx.NUMBER().getText();
        int l = ctx.getStart().getLine();
        int c = ctx.getStart().getCharPositionInLine();
        if (text.contains(".")) {
            return new LiteralNode(l, c, Double.parseDouble(text));
        } else {
            return new LiteralNode(l, c, Integer.parseInt(text));
        }
    }

    @Override
    public ASTNode visitStringLiteral(MiniLangParser.StringLiteralContext ctx) {
        String raw = ctx.STRING().getText();
        // Strip surrounding double quotes
        String value = raw.substring(1, raw.length() - 1);
        int l = ctx.getStart().getLine();
        int c = ctx.getStart().getCharPositionInLine();
        return new LiteralNode(l, c, value);
    }

    @Override
    public ASTNode visitBoolLiteral(MiniLangParser.BoolLiteralContext ctx) {
        boolean value = ctx.BOOL().getText().equals("true");
        int l = ctx.getStart().getLine();
        int c = ctx.getStart().getCharPositionInLine();
        return new LiteralNode(l, c, value);
    }
}
