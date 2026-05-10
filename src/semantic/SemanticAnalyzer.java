package semantic;

import ast.*;
import ast.expressions.*;
import ast.statements.*;
import errors.ErrorCollector;
import errors.SemanticError;

import java.util.List;

// =============================================================================
// SemanticAnalyzer.java  —  Walks the AST to validate meaning
//
// Pipeline Position:  AST  →  [SemanticAnalyzer]  →  Validated AST (or errors)
//
// What it checks:
//   1. Variables are defined before use
//   2. Functions are defined before call (or at program level)
//   3. Function call arity (correct number of arguments)
//   4. Return statements are inside functions
//   5. Break statements are inside loops/switch
//   6. Type inference — records the type of each variable
//
// WHY do this BEFORE interpretation?
//   The interpreter assumes the code is correct. If we run an undefined
//   variable through the interpreter, it crashes. The semantic analyzer
//   catches these issues at "compile time" and gives helpful error messages.
// =============================================================================
public class SemanticAnalyzer implements ASTVisitor<MiniLangType> {

    private final SymbolTable    symbolTable;
    private final ErrorCollector errors;

    // Track whether we're inside a function (for return validation)
    private boolean insideFunction = false;

    // Track whether we're inside a loop/switch (for break validation)
    private int loopDepth = 0;

    public SemanticAnalyzer(ErrorCollector errors) {
        this.symbolTable = new SymbolTable();
        this.errors      = errors;
        registerBuiltIns();
    }

    // Pre-register built-in functions so the analyzer doesn't flag them.
    // We use -1 for paramCount to indicate "variable arity" (skip arity check).
    private void registerBuiltIns() {
        String[] builtIns = { "length", "substring", "toInt", "toFloat", "toString", "push", "pop" };
        for (String name : builtIns) {
            // paramNames=null signals "built-in, skip arity check"
            symbolTable.define(new Symbol(name, MiniLangType.UNKNOWN, java.util.List.of("__builtin__")));
        }
    }

    // ── Entry point ───────────────────────────────────────────
    public void analyze(ProgramNode program) {
        visitProgram(program);
    }

    // ─────────────────────────────────────────────────────────────
    // PROGRAM
    // ─────────────────────────────────────────────────────────────

    @Override
    public MiniLangType visitProgram(ProgramNode node) {
        // First pass: register all function declarations so functions
        // can be called before they are defined (like in real languages).
        for (ASTNode stmt : node.getStatements()) {
            if (stmt instanceof FunctionDeclNode) {
                FunctionDeclNode fn = (FunctionDeclNode) stmt;
                symbolTable.define(new Symbol(fn.getName(), MiniLangType.UNKNOWN, fn.getParams()));
            }
        }
        // Second pass: analyze everything
        for (ASTNode stmt : node.getStatements()) {
            stmt.accept(this);
        }
        return MiniLangType.VOID;
    }

    // ─────────────────────────────────────────────────────────────
    // STATEMENTS
    // ─────────────────────────────────────────────────────────────

    @Override
    public MiniLangType visitAssignment(AssignmentNode node) {
        MiniLangType valueType = node.getValue().accept(this);
        // Define or re-assign the variable with inferred type
        symbolTable.define(new Symbol(node.getVarName(), valueType));
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitArrayAssignment(ArrayAssignmentNode node) {
        Symbol array = symbolTable.lookup(node.getVarName());
        if (array == null) {
            errors.add(new SemanticError(node.getLine(), node.getColumn(),
                    "Undefined variable '" + node.getVarName() + "'"));
        }
        node.getIndex().accept(this);
        node.getValue().accept(this);
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitPrint(PrintNode node) {
        node.getExpression().accept(this);
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitInput(InputNode node) {
        // Input always produces a STRING (interpreter may coerce later)
        symbolTable.define(new Symbol(node.getVarName(), MiniLangType.STRING));
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitIf(IfNode node) {
        node.getCondition().accept(this);
        symbolTable.pushScope();
        node.getThenBlock().accept(this);
        symbolTable.popScope();
        if (node.getElseBlock() != null) {
            symbolTable.pushScope();
            node.getElseBlock().accept(this);
            symbolTable.popScope();
        }
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitWhile(WhileNode node) {
        node.getCondition().accept(this);
        loopDepth++;
        symbolTable.pushScope();
        node.getBody().accept(this);
        symbolTable.popScope();
        loopDepth--;
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitFor(ForNode node) {
        // The init variable belongs to the for scope
        symbolTable.pushScope();
        symbolTable.define(new Symbol(node.getInitVar(), MiniLangType.UNKNOWN));
        node.getInitValue().accept(this);
        node.getCondition().accept(this);
        node.getUpdateValue().accept(this);
        loopDepth++;
        node.getBody().accept(this);
        loopDepth--;
        symbolTable.popScope();
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitSwitch(SwitchNode node) {
        node.getSubject().accept(this);
        loopDepth++; // switch uses loopDepth for break validation
        for (SwitchNode.SwitchCase sc : node.getCases()) {
            symbolTable.pushScope();
            for (ASTNode stmt : sc.getBody()) stmt.accept(this);
            symbolTable.popScope();
        }
        if (node.getDefaultBody() != null) {
            symbolTable.pushScope();
            for (ASTNode stmt : node.getDefaultBody()) stmt.accept(this);
            symbolTable.popScope();
        }
        loopDepth--;
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitFunctionDecl(FunctionDeclNode node) {
        // Re-define with the actual param names now that we're analyzing the body
        symbolTable.define(new Symbol(node.getName(), MiniLangType.UNKNOWN, node.getParams()));

        boolean prevInside = insideFunction;
        insideFunction = true;
        symbolTable.pushScope();
        // Register parameters as local variables in the function scope
        for (String param : node.getParams()) {
            symbolTable.define(new Symbol(param, MiniLangType.UNKNOWN));
        }
        node.getBody().accept(this);
        symbolTable.popScope();
        insideFunction = prevInside;
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitReturn(ReturnNode node) {
        if (!insideFunction) {
            errors.add(new SemanticError(node.getLine(), node.getColumn(),
                    "'return' used outside of a function"));
        }
        if (node.getValue() != null) {
            node.getValue().accept(this);
        }
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitBreak(BreakNode node) {
        if (loopDepth == 0) {
            errors.add(new SemanticError(node.getLine(), node.getColumn(),
                    "'break' used outside of a loop or switch"));
        }
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitBlock(BlockNode node) {
        // Note: block scope is pushed by the PARENT (if/while/for/function)
        // because some parents need to add symbols BEFORE visiting the block.
        for (ASTNode stmt : node.getStatements()) {
            stmt.accept(this);
        }
        return MiniLangType.VOID;
    }

    @Override
    public MiniLangType visitExprStatement(ExprStatementNode node) {
        node.getExpression().accept(this);
        return MiniLangType.VOID;
    }

    // ─────────────────────────────────────────────────────────────
    // EXPRESSIONS
    // ─────────────────────────────────────────────────────────────

    @Override
    public MiniLangType visitBinaryOp(BinaryOpNode node) {
        MiniLangType left  = node.getLeft().accept(this);
        MiniLangType right = node.getRight().accept(this);
        String op = node.getOperator();

        // Logical operators require boolean-compatible operands
        if (op.equals("&&") || op.equals("||")) {
            return MiniLangType.BOOL;
        }

        // Comparison operators always produce a boolean
        if (op.equals("==") || op.equals("!=") ||
            op.equals(">")  || op.equals("<")  ||
            op.equals(">=") || op.equals("<=")) {
            return MiniLangType.BOOL;
        }

        // String concatenation with +
        if (op.equals("+") && (left == MiniLangType.STRING || right == MiniLangType.STRING)) {
            return MiniLangType.STRING;
        }

        // Numeric arithmetic — promote to FLOAT if either side is FLOAT
        if (left == MiniLangType.FLOAT || right == MiniLangType.FLOAT) {
            return MiniLangType.FLOAT;
        }

        return MiniLangType.INT;
    }

    @Override
    public MiniLangType visitUnaryOp(UnaryOpNode node) {
        MiniLangType operandType = node.getOperand().accept(this);
        if (node.getOperator().equals("!")) {
            return MiniLangType.BOOL;
        }
        return operandType; // "-" preserves numeric type
    }

    @Override
    public MiniLangType visitLiteral(LiteralNode node) {
        if (node.isInt())    return MiniLangType.INT;
        if (node.isFloat())  return MiniLangType.FLOAT;
        if (node.isBool())   return MiniLangType.BOOL;
        if (node.isString()) return MiniLangType.STRING;
        return MiniLangType.UNKNOWN;
    }

    @Override
    public MiniLangType visitVariable(VariableNode node) {
        Symbol symbol = symbolTable.lookup(node.getName());
        if (symbol == null) {
            errors.add(new SemanticError(node.getLine(), node.getColumn(),
                    "Undefined variable '" + node.getName() + "'"));
            return MiniLangType.UNKNOWN; // Continue analysis despite the error
        }
        return symbol.getType();
    }

    @Override
    public MiniLangType visitFunctionCall(FunctionCallNode node) {
        Symbol fn = symbolTable.lookup(node.getName());
        if (fn == null) {
            errors.add(new SemanticError(node.getLine(), node.getColumn(),
                    "Undefined function '" + node.getName() + "'"));
            return MiniLangType.UNKNOWN;
        }
        if (!fn.isFunction()) {
            errors.add(new SemanticError(node.getLine(), node.getColumn(),
                    "'" + node.getName() + "' is a variable, not a function"));
            return MiniLangType.UNKNOWN;
        }
        // Skip arity check for built-ins (marked by single "__builtin__" param)
        boolean isBuiltIn = fn.getParamCount() == 1
                && fn.getParamNames().get(0).equals("__builtin__");
        if (!isBuiltIn) {
            int expected = fn.getParamCount();
            int actual   = node.getArguments().size();
            if (expected != actual) {
                errors.add(new SemanticError(node.getLine(), node.getColumn(),
                        "Function '" + node.getName() + "' expects " + expected
                                + " argument(s) but got " + actual));
            }
        }
        // Analyze each argument expression
        for (ASTNode arg : node.getArguments()) {
            arg.accept(this);
        }
        return fn.getType();
    }

    @Override
    public MiniLangType visitArrayLiteral(ArrayLiteralNode node) {
        for (ASTNode elem : node.getElements()) {
            elem.accept(this);
        }
        return MiniLangType.ARRAY;
    }

    @Override
    public MiniLangType visitArrayAccess(ArrayAccessNode node) {
        Symbol array = symbolTable.lookup(node.getVarName());
        if (array == null) {
            errors.add(new SemanticError(node.getLine(), node.getColumn(),
                    "Undefined variable '" + node.getVarName() + "'"));
        }
        node.getIndex().accept(this);
        return MiniLangType.UNKNOWN; // Element type not tracked statically
    }
}
