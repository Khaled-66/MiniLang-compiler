package interpreter;

import ast.*;
import ast.expressions.*;
import ast.statements.*;
import errors.ErrorCollector;
import errors.RuntimeError;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// =============================================================================
// Interpreter.java  —  AST-walking execution engine
//
// Pipeline Position:  Validated AST  →  [Interpreter]  →  Program Output
//
// HOW it works:
//   Implements ASTVisitor<MiniLangValue>.
//   For each node type, the visit method:
//     1. Evaluates child nodes recursively
//     2. Performs the operation
//     3. Returns the result as a MiniLangValue
//
// Key design choices:
//   - Return/break use exception-based control flow (see ReturnException)
//   - Each { block } creates a new child Environment (scope)
//   - Functions capture their definition environment (closures)
// =============================================================================
public class Interpreter implements ASTVisitor<MiniLangValue> {

    // The global environment — persists for the whole program run
    private Environment globalEnv;

    // Accumulated output lines (used by the HTTP server to return results)
    private final StringBuilder output = new StringBuilder();

    // Error collector — runtime errors are added here instead of thrown
    private final ErrorCollector errors;

    // Scanner for reading stdin (input() statement)
    private final Scanner scanner = new Scanner(System.in);

    public Interpreter(ErrorCollector errors) {
        this.errors    = errors;
        this.globalEnv = new Environment();
    }

    // ── Entry point ───────────────────────────────────────────
    public String run(ProgramNode program) {
        output.setLength(0); // Clear output for re-runs
        try {
            visitProgram(program);
        } catch (Exception e) {
            appendOutput(e.getMessage() != null ? e.getMessage() : "[Runtime Error] Unknown error");
        }
        return output.toString().trim();
    }

    private void appendOutput(String line) {
        if (output.length() > 0) output.append("\n");
        output.append(line);
    }

    // ─────────────────────────────────────────────────────────────
    // PROGRAM
    // ─────────────────────────────────────────────────────────────

    @Override
    public MiniLangValue visitProgram(ProgramNode node) {
        for (ASTNode stmt : node.getStatements()) {
            stmt.accept(this);
        }
        return MiniLangValue.VOID;
    }

    // ─────────────────────────────────────────────────────────────
    // STATEMENTS
    // ─────────────────────────────────────────────────────────────

    @Override
    public MiniLangValue visitAssignment(AssignmentNode node) {
        MiniLangValue value = node.getValue().accept(this);
        globalEnv.set(node.getVarName(), value);
        return MiniLangValue.VOID;
    }

    // This override is used when we need to assign inside a specific scope
    private MiniLangValue executeAssignment(AssignmentNode node, Environment env) {
        MiniLangValue value = evaluateInEnv(node.getValue(), env);
        env.set(node.getVarName(), value);
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitArrayAssignment(ArrayAssignmentNode node) {
        MiniLangValue arrayVal = globalEnv.get(node.getVarName());
        if (arrayVal == null || !arrayVal.isArray()) {
            throw new RuntimeException(
                    error(node, "'" + node.getVarName() + "' is not an array"));
        }
        MiniLangValue indexVal = node.getIndex().accept(this);
        int index = toInt(indexVal, node);
        List<MiniLangValue> list = arrayVal.asArray();
        if (index < 0 || index >= list.size()) {
            throw new RuntimeException(
                    error(node, "Array index " + index + " out of bounds (size " + list.size() + ")"));
        }
        list.set(index, node.getValue().accept(this));
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitPrint(PrintNode node) {
        MiniLangValue val = node.getExpression().accept(this);
        appendOutput(val.toString());
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitInput(InputNode node) {
        // Print prompt to output (GUI will display it), then read stdin
        appendOutput(node.getPrompt());
        String line = scanner.hasNextLine() ? scanner.nextLine() : "";
        // Try to parse as number, otherwise keep as string
        MiniLangValue value;
        try {
            if (line.contains(".")) {
                value = new MiniLangValue(Double.parseDouble(line));
            } else {
                value = new MiniLangValue(Integer.parseInt(line));
            }
        } catch (NumberFormatException e) {
            value = new MiniLangValue(line);
        }
        globalEnv.set(node.getVarName(), value);
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitIf(IfNode node) {
        MiniLangValue condition = node.getCondition().accept(this);
        if (condition.isTruthy()) {
            executeBlock((BlockNode) node.getThenBlock(), globalEnv.createChild());
        } else if (node.getElseBlock() != null) {
            executeBlock((BlockNode) node.getElseBlock(), globalEnv.createChild());
        }
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitWhile(WhileNode node) {
        try {
            while (node.getCondition().accept(this).isTruthy()) {
                try {
                    executeBlock((BlockNode) node.getBody(), globalEnv.createChild());
                } catch (BreakException e) {
                    break; // break; inside the while body
                }
            }
        } catch (BreakException ignored) {}
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitFor(ForNode node) {
        // Create the for-loop's own scope (init variable lives here)
        Environment forEnv = globalEnv.createChild();
        forEnv.define(node.getInitVar(), node.getInitValue().accept(this));

        try {
            while (evaluateInEnv(node.getCondition(), forEnv).isTruthy()) {
                try {
                    executeBlock((BlockNode) node.getBody(), forEnv.createChild());
                } catch (BreakException e) {
                    break;
                }
                // Update step: evaluate in forEnv and store back
                MiniLangValue updated = evaluateInEnv(node.getUpdateValue(), forEnv);
                forEnv.define(node.getUpdateVar(), updated);
            }
        } catch (BreakException ignored) {}
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitSwitch(SwitchNode node) {
        MiniLangValue subject = node.getSubject().accept(this);
        boolean matched = false;

        try {
            for (SwitchNode.SwitchCase sc : node.getCases()) {
                MiniLangValue caseVal = sc.getValue().accept(this);
                if (!matched && valuesEqual(subject, caseVal)) {
                    matched = true;
                }
                if (matched) {
                    try {
                        for (ASTNode stmt : sc.getBody()) stmt.accept(this);
                    } catch (BreakException e) {
                        return MiniLangValue.VOID; // break exits the switch
                    }
                }
            }
            if (!matched && node.getDefaultBody() != null) {
                for (ASTNode stmt : node.getDefaultBody()) stmt.accept(this);
            }
        } catch (BreakException ignored) {}

        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitFunctionDecl(FunctionDeclNode node) {
        // Don't execute — just register the function in the current environment
        MiniLangFunction fn = new MiniLangFunction(node, globalEnv);
        globalEnv.define(node.getName(), new MiniLangValue(fn.toString()) {
            // Store the function object itself (using a subclass trick for clarity)
            private final MiniLangFunction func = fn;
            public MiniLangFunction getFunction() { return func; }
        });
        // Simpler approach: store as a special value wrapper
        globalEnv.define(node.getName(), wrapFunction(fn));
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitReturn(ReturnNode node) {
        MiniLangValue value = node.getValue() != null
                ? node.getValue().accept(this)
                : MiniLangValue.VOID;
        throw new ReturnException(value);
    }

    @Override
    public MiniLangValue visitBreak(BreakNode node) {
        throw new BreakException();
    }

    @Override
    public MiniLangValue visitBlock(BlockNode node) {
        executeBlock(node, globalEnv.createChild());
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitExprStatement(ExprStatementNode node) {
        node.getExpression().accept(this);
        return MiniLangValue.VOID;
    }

    // ─────────────────────────────────────────────────────────────
    // EXPRESSIONS
    // ─────────────────────────────────────────────────────────────

    @Override
    public MiniLangValue visitBinaryOp(BinaryOpNode node) {
        String op = node.getOperator();

        // Short-circuit evaluation for && and ||
        // WHY short-circuit? "false && crash()" should NOT call crash()
        if (op.equals("&&")) {
            MiniLangValue left = node.getLeft().accept(this);
            if (!left.isTruthy()) return new MiniLangValue(false);
            return new MiniLangValue(node.getRight().accept(this).isTruthy());
        }
        if (op.equals("||")) {
            MiniLangValue left = node.getLeft().accept(this);
            if (left.isTruthy()) return new MiniLangValue(true);
            return new MiniLangValue(node.getRight().accept(this).isTruthy());
        }

        MiniLangValue left  = node.getLeft().accept(this);
        MiniLangValue right = node.getRight().accept(this);

        switch (op) {
            // ── Arithmetic ──────────────────────────────────
            case "+":
                // String concatenation if either side is a string
                if (left.isString() || right.isString()) {
                    return new MiniLangValue(left.toString() + right.toString());
                }
                if (left.isFloat() || right.isFloat()) {
                    return new MiniLangValue(left.asDouble() + right.asDouble());
                }
                return new MiniLangValue(left.asInt() + right.asInt());

            case "-":
                if (left.isFloat() || right.isFloat()) {
                    return new MiniLangValue(left.asDouble() - right.asDouble());
                }
                return new MiniLangValue(left.asInt() - right.asInt());

            case "*":
                if (left.isFloat() || right.isFloat()) {
                    return new MiniLangValue(left.asDouble() * right.asDouble());
                }
                return new MiniLangValue(left.asInt() * right.asInt());

            case "/":
                if (right.isNumeric() && right.asDouble() == 0.0) {
                    throw new RuntimeException(error(node, "Division by zero"));
                }
                if (left.isFloat() || right.isFloat()) {
                    return new MiniLangValue(left.asDouble() / right.asDouble());
                }
                return new MiniLangValue(left.asInt() / right.asInt());

            // ── Relational ───────────────────────────────────
            case ">":   return new MiniLangValue(left.asDouble() > right.asDouble());
            case "<":   return new MiniLangValue(left.asDouble() < right.asDouble());
            case ">=":  return new MiniLangValue(left.asDouble() >= right.asDouble());
            case "<=":  return new MiniLangValue(left.asDouble() <= right.asDouble());

            // ── Equality ─────────────────────────────────────
            case "==":  return new MiniLangValue(valuesEqual(left, right));
            case "!=":  return new MiniLangValue(!valuesEqual(left, right));

            default:
                throw new RuntimeException(error(node, "Unknown operator: " + op));
        }
    }

    @Override
    public MiniLangValue visitUnaryOp(UnaryOpNode node) {
        MiniLangValue operand = node.getOperand().accept(this);
        switch (node.getOperator()) {
            case "-":
                if (operand.isFloat()) return new MiniLangValue(-operand.asDouble());
                return new MiniLangValue(-operand.asInt());
            case "!":
                return new MiniLangValue(!operand.isTruthy());
            default:
                throw new RuntimeException(error(node, "Unknown unary operator: " + node.getOperator()));
        }
    }

    @Override
    public MiniLangValue visitLiteral(LiteralNode node) {
        Object raw = node.getValue();
        if (raw instanceof Integer) return new MiniLangValue((int) raw);
        if (raw instanceof Double)  return new MiniLangValue((double) raw);
        if (raw instanceof Boolean) return new MiniLangValue((boolean) raw);
        if (raw instanceof String)  return new MiniLangValue((String) raw);
        return MiniLangValue.VOID;
    }

    @Override
    public MiniLangValue visitVariable(VariableNode node) {
        MiniLangValue val = globalEnv.get(node.getName());
        if (val == null) {
            throw new RuntimeException(
                    error(node, "Undefined variable '" + node.getName() + "'"));
        }
        return val;
    }

    @Override
    public MiniLangValue visitFunctionCall(FunctionCallNode node) {
        // Handle built-in string methods: str.length(), str.substring(a,b), etc.
        MiniLangValue builtIn = tryBuiltIn(node);
        if (builtIn != null) return builtIn;

        MiniLangValue fnVal = globalEnv.get(node.getName());
        if (fnVal == null || !(fnVal.getRaw() instanceof MiniLangFunction)) {
            throw new RuntimeException(
                    error(node, "Undefined function '" + node.getName() + "'"));
        }

        MiniLangFunction fn = (MiniLangFunction) fnVal.getRaw();

        // Evaluate arguments in the CALLER's scope
        List<MiniLangValue> args = new ArrayList<>();
        for (ASTNode arg : node.getArguments()) {
            args.add(arg.accept(this));
        }

        // Create a new scope rooted at the function's closure environment
        Environment callEnv = fn.getClosureEnv().createChild();

        // Bind parameter names to argument values
        List<String> params = fn.getDeclaration().getParams();
        for (int i = 0; i < params.size(); i++) {
            callEnv.define(params.get(i), args.get(i));
        }

        // Execute the function body, catching any return value
        Environment savedEnv = globalEnv;
        globalEnv = callEnv;
        try {
            fn.getDeclaration().getBody().accept(this);
            return MiniLangValue.VOID; // No return statement hit
        } catch (ReturnException ret) {
            return ret.getValue();
        } finally {
            globalEnv = savedEnv; // ALWAYS restore the caller's environment
        }
    }

    @Override
    public MiniLangValue visitArrayLiteral(ArrayLiteralNode node) {
        List<MiniLangValue> elements = new ArrayList<>();
        for (ASTNode elem : node.getElements()) {
            elements.add(elem.accept(this));
        }
        return new MiniLangValue(elements);
    }

    @Override
    public MiniLangValue visitArrayAccess(ArrayAccessNode node) {
        MiniLangValue arrayVal = globalEnv.get(node.getVarName());
        if (arrayVal == null || !arrayVal.isArray()) {
            throw new RuntimeException(
                    error(node, "'" + node.getVarName() + "' is not an array"));
        }
        int index = toInt(node.getIndex().accept(this), node);
        List<MiniLangValue> list = arrayVal.asArray();
        if (index < 0 || index >= list.size()) {
            throw new RuntimeException(
                    error(node, "Array index " + index + " out of bounds (size " + list.size() + ")"));
        }
        return list.get(index);
    }

    // ─────────────────────────────────────────────────────────────
    // BUILT-IN FUNCTIONS
    // ─────────────────────────────────────────────────────────────
    // These are functions available without declaring them:
    //   length(str)   — length of string or array
    //   substring(str, start, end)
    //   toInt(val)    — converts to integer
    //   toFloat(val)  — converts to float
    //   toString(val) — converts to string
    //   push(arr, val) — appends to array
    //   pop(arr)       — removes last element

    private MiniLangValue tryBuiltIn(FunctionCallNode node) {
        String name = node.getName();
        List<ASTNode> args = node.getArguments();

        switch (name) {
            case "length": {
                if (args.size() != 1) return null;
                MiniLangValue v = args.get(0).accept(this);
                if (v.isString()) return new MiniLangValue(v.asString().length());
                if (v.isArray())  return new MiniLangValue(v.asArray().size());
                throw new RuntimeException(error(node, "length() requires a string or array"));
            }
            case "substring": {
                if (args.size() != 3) return null;
                MiniLangValue str   = args.get(0).accept(this);
                int start = toInt(args.get(1).accept(this), node);
                int end   = toInt(args.get(2).accept(this), node);
                if (!str.isString()) throw new RuntimeException(error(node, "substring() requires a string"));
                return new MiniLangValue(str.asString().substring(start, end));
            }
            case "toInt": {
                if (args.size() != 1) return null;
                MiniLangValue v = args.get(0).accept(this);
                if (v.isInt())    return v;
                if (v.isFloat())  return new MiniLangValue((int) v.asDouble());
                if (v.isString()) return new MiniLangValue(Integer.parseInt(v.asString()));
                throw new RuntimeException(error(node, "Cannot convert to int"));
            }
            case "toFloat": {
                if (args.size() != 1) return null;
                MiniLangValue v = args.get(0).accept(this);
                if (v.isFloat())  return v;
                if (v.isInt())    return new MiniLangValue((double) v.asInt());
                if (v.isString()) return new MiniLangValue(Double.parseDouble(v.asString()));
                throw new RuntimeException(error(node, "Cannot convert to float"));
            }
            case "toString": {
                if (args.size() != 1) return null;
                return new MiniLangValue(args.get(0).accept(this).toString());
            }
            case "push": {
                if (args.size() != 2) return null;
                MiniLangValue arr = args.get(0).accept(this);
                if (!arr.isArray()) throw new RuntimeException(error(node, "push() requires an array"));
                arr.asArray().add(args.get(1).accept(this));
                return MiniLangValue.VOID;
            }
            case "pop": {
                if (args.size() != 1) return null;
                MiniLangValue arr = args.get(0).accept(this);
                if (!arr.isArray() || arr.asArray().isEmpty())
                    throw new RuntimeException(error(node, "pop() requires a non-empty array"));
                return arr.asArray().remove(arr.asArray().size() - 1);
            }
            default:
                return null; // Not a built-in
        }
    }

    // ─────────────────────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────────────────────

    // Execute a block in an explicit environment (used for scoped blocks)
    private void executeBlock(BlockNode block, Environment env) {
        Environment saved = globalEnv;
        globalEnv = env;
        try {
            for (ASTNode stmt : block.getStatements()) {
                stmt.accept(this);
            }
        } finally {
            globalEnv = saved;
        }
    }

    // Evaluate a node in a specific environment (for for-loop conditions)
    private MiniLangValue evaluateInEnv(ASTNode node, Environment env) {
        Environment saved = globalEnv;
        globalEnv = env;
        try {
            return node.accept(this);
        } finally {
            globalEnv = saved;
        }
    }

    // Wrap a MiniLangFunction as a MiniLangValue using subclassing
    private MiniLangValue wrapFunction(MiniLangFunction fn) {
        return new MiniLangValue(fn.getName()) {
            private final MiniLangFunction func = fn;

            @Override
            public Object getRaw() { return func; }

            @Override
            public String toString() { return "<function " + func.getName() + ">"; }
        };
    }

    // Safe integer extraction with proper error message
    private int toInt(MiniLangValue val, ASTNode context) {
        if (val.isInt())   return val.asInt();
        if (val.isFloat()) return (int) val.asDouble();
        throw new RuntimeException(error(context, "Expected an integer, got: " + val));
    }

    // Structural equality check for == and !=
    private boolean valuesEqual(MiniLangValue a, MiniLangValue b) {
        if (a.isVoid() && b.isVoid()) return true;
        if (a.getRaw() == null || b.getRaw() == null) return false;
        // Numeric comparison: compare as doubles
        if (a.isNumeric() && b.isNumeric()) {
            return Double.compare(a.asDouble(), b.asDouble()) == 0;
        }
        return a.getRaw().equals(b.getRaw());
    }

    // Format a runtime error message with line/column info
    private String error(ASTNode node, String message) {
        if (node.getLine() > 0) {
            return "[Runtime] line " + node.getLine() + ":" + node.getColumn() + " - " + message;
        }
        return "[Runtime] " + message;
    }
}
