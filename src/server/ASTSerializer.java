// ASTSerializer lives in the default package to access ANTLR generated classes.

import ast.*;
import ast.expressions.*;
import ast.statements.*;

import java.util.List;

// =============================================================================
// ASTSerializer.java  —  Converts the custom AST into a JSON string
//
// Pipeline Position:  Used by CompilerServer to send AST data to the GUI
//
// WHY serialize to JSON?
//   The GUI is written in JavaScript running in a browser. The only universal
//   data format between Java and JavaScript is JSON. Once the GUI has the AST
//   as a JSON object, it can render it as an interactive tree diagram.
//
// Output format (example for  x = 3 + 4;):
// {
//   "type": "AssignmentNode",
//   "varName": "x",
//   "line": 1,
//   "children": [
//     {
//       "type": "BinaryOpNode",
//       "operator": "+",
//       "children": [
//         { "type": "LiteralNode", "value": "3" },
//         { "type": "LiteralNode", "value": "4" }
//       ]
//     }
//   ]
// }
// =============================================================================
public class ASTSerializer {

    public static String toJson(ASTNode node) {
        if (node == null) return "null";
        StringBuilder sb = new StringBuilder();
        serialize(node, sb);
        return sb.toString();
    }

    private static void serialize(ASTNode node, StringBuilder sb) {
        if (node == null) { sb.append("null"); return; }

        sb.append("{");
        sb.append("\"type\":\"").append(node.getClass().getSimpleName()).append("\"");
        sb.append(",\"line\":").append(node.getLine());

        // ── Per-node fields + children ─────────────────────────
        if (node instanceof ProgramNode) {
            ProgramNode n = (ProgramNode) node;
            sb.append(",\"children\":"); serializeList(n.getStatements(), sb);

        } else if (node instanceof AssignmentNode) {
            AssignmentNode n = (AssignmentNode) node;
            sb.append(",\"varName\":\"").append(n.getVarName()).append("\"");
            sb.append(",\"children\":["); serialize(n.getValue(), sb); sb.append("]");

        } else if (node instanceof ArrayAssignmentNode) {
            ArrayAssignmentNode n = (ArrayAssignmentNode) node;
            sb.append(",\"varName\":\"").append(n.getVarName()).append("\"");
            sb.append(",\"children\":["); serialize(n.getIndex(), sb); sb.append(","); serialize(n.getValue(), sb); sb.append("]");

        } else if (node instanceof PrintNode) {
            sb.append(",\"children\":["); serialize(((PrintNode) node).getExpression(), sb); sb.append("]");

        } else if (node instanceof InputNode) {
            InputNode n = (InputNode) node;
            sb.append(",\"varName\":\"").append(n.getVarName()).append("\"");
            sb.append(",\"prompt\":\"").append(escapeJson(n.getPrompt())).append("\"");

        } else if (node instanceof IfNode) {
            IfNode n = (IfNode) node;
            sb.append(",\"children\":[");
            serialize(n.getCondition(), sb); sb.append(",");
            serialize(n.getThenBlock(), sb);
            if (n.getElseBlock() != null) { sb.append(","); serialize(n.getElseBlock(), sb); }
            sb.append("]");

        } else if (node instanceof WhileNode) {
            WhileNode n = (WhileNode) node;
            sb.append(",\"children\":["); serialize(n.getCondition(), sb); sb.append(","); serialize(n.getBody(), sb); sb.append("]");

        } else if (node instanceof ForNode) {
            ForNode n = (ForNode) node;
            sb.append(",\"initVar\":\"").append(n.getInitVar()).append("\"");
            sb.append(",\"updateVar\":\"").append(n.getUpdateVar()).append("\"");
            sb.append(",\"children\":["); serialize(n.getInitValue(), sb); sb.append(","); serialize(n.getCondition(), sb); sb.append(","); serialize(n.getUpdateValue(), sb); sb.append(","); serialize(n.getBody(), sb); sb.append("]");

        } else if (node instanceof SwitchNode) {
            SwitchNode n = (SwitchNode) node;
            sb.append(",\"children\":["); serialize(n.getSubject(), sb); sb.append("]");

        } else if (node instanceof FunctionDeclNode) {
            FunctionDeclNode n = (FunctionDeclNode) node;
            sb.append(",\"name\":\"").append(n.getName()).append("\"");
            sb.append(",\"params\":").append(jsonStringArray(n.getParams()));
            sb.append(",\"children\":["); serialize(n.getBody(), sb); sb.append("]");

        } else if (node instanceof ReturnNode) {
            ReturnNode n = (ReturnNode) node;
            if (n.getValue() != null) { sb.append(",\"children\":["); serialize(n.getValue(), sb); sb.append("]"); }

        } else if (node instanceof BlockNode) {
            sb.append(",\"children\":"); serializeList(((BlockNode) node).getStatements(), sb);

        } else if (node instanceof ExprStatementNode) {
            sb.append(",\"children\":["); serialize(((ExprStatementNode) node).getExpression(), sb); sb.append("]");

        } else if (node instanceof BinaryOpNode) {
            BinaryOpNode n = (BinaryOpNode) node;
            sb.append(",\"operator\":\"").append(escapeJson(n.getOperator())).append("\"");
            sb.append(",\"children\":["); serialize(n.getLeft(), sb); sb.append(","); serialize(n.getRight(), sb); sb.append("]");

        } else if (node instanceof UnaryOpNode) {
            UnaryOpNode n = (UnaryOpNode) node;
            sb.append(",\"operator\":\"").append(escapeJson(n.getOperator())).append("\"");
            sb.append(",\"children\":["); serialize(n.getOperand(), sb); sb.append("]");

        } else if (node instanceof LiteralNode) {
            sb.append(",\"value\":\"").append(escapeJson(((LiteralNode) node).getValue().toString())).append("\"");

        } else if (node instanceof VariableNode) {
            sb.append(",\"name\":\"").append(((VariableNode) node).getName()).append("\"");

        } else if (node instanceof FunctionCallNode) {
            FunctionCallNode n = (FunctionCallNode) node;
            sb.append(",\"name\":\"").append(n.getName()).append("\"");
            sb.append(",\"children\":"); serializeList(n.getArguments(), sb);

        } else if (node instanceof ArrayLiteralNode) {
            sb.append(",\"children\":"); serializeList(((ArrayLiteralNode) node).getElements(), sb);

        } else if (node instanceof ArrayAccessNode) {
            ArrayAccessNode n = (ArrayAccessNode) node;
            sb.append(",\"varName\":\"").append(n.getVarName()).append("\"");
            sb.append(",\"children\":["); serialize(n.getIndex(), sb); sb.append("]");
        }

        sb.append("}");
    }

    private static void serializeList(List<ASTNode> nodes, StringBuilder sb) {
        sb.append("[");
        for (int i = 0; i < nodes.size(); i++) {
            serialize(nodes.get(i), sb);
            if (i < nodes.size() - 1) sb.append(",");
        }
        sb.append("]");
    }

    private static String jsonStringArray(List<String> items) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < items.size(); i++) {
            sb.append("\"").append(items.get(i)).append("\"");
            if (i < items.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String escapeJson(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
