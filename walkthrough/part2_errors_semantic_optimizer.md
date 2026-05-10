# MiniLang Compiler — Code Walkthrough (Part 2 of 3)
# Error System, ASTBuilder, Semantic Analyzer, Optimizer

---

## 5. Error System — src/errors/

| File | Purpose |
|------|---------|
| MiniLangError.java | Abstract base class. Every error has: phase, line, column, message |
| LexerError.java | "I can't read this character" — e.g., @ is not valid |
| ParseError.java | "This sentence is grammatically wrong" — e.g., x = ; |
| SemanticError.java | "This doesn't make logical sense" — e.g., using variable z before defining it |
| RuntimeError.java | "Something broke while running" — e.g., dividing by zero |
| ErrorCollector.java | Bag that collects ALL errors from all phases |

### Complex Snippet: Why ErrorCollector exists

```java
public class ErrorCollector {
    private final List<MiniLangError> errors = new ArrayList<>();

    public void add(MiniLangError error) { errors.add(error); }
    public boolean hasErrors()           { return !errors.isEmpty(); }
    public String getSummary()           { /* joins all errors with newlines */ }
}
```

**Why collect instead of throw?** Naive compilers stop at the first error. Real compilers (like javac) collect ALL errors and report them together. You can fix 10 errors in one go instead of running the compiler 10 times. This pattern is called **Error Recovery**.

### Complex Snippet: MiniLangError.toString()

```java
return String.format("[%s] line %d:%d - %s", phase, line, column, message);
// Output: [Semantic] line 5:3 - Undefined variable 'z'
```

All 4 subclasses inherit this format — consistent error messages everywhere.

---

## 6. src/parser/ASTBuilder.java — Parse Tree to AST Converter

**Purpose**: ANTLR builds a **Parse Tree** (full of grammar noise like semicolons and parentheses). ASTBuilder visits each node and creates our clean **AST nodes** with typed fields.

**How it works**: Extends MiniLangBaseVisitor<ASTNode> — for every grammar rule, there's a visit*() method that returns the corresponding AST node.

### Complex Snippet: visitForStmt

```java
public ASTNode visitForStmt(MiniLangParser.ForStmtContext ctx) {
    // forInit:   ID '=' expr ';'
    MiniLangParser.ForInitContext init = ctx.forInit();
    String  initVar   = init.ID().getText();     // "i"
    ASTNode initValue = visit(init.expr());      // 0

    ASTNode condition = visit(ctx.expr());       // i < 10

    // forUpdate:  ID '=' expr
    MiniLangParser.ForUpdateContext update = ctx.forUpdate();
    String  updateVar   = update.ID().getText(); // "i"
    ASTNode updateValue = visit(update.expr());  // i + 1

    ASTNode body = visit(ctx.block());           // { ... }

    return new ForNode(line(ctx), column(ctx),
            initVar, initValue, condition,
            updateVar, updateValue, body);
}
```

**What's happening**: The grammar splits `for (i = 0; i < 10; i = i + 1)` into three sub-rules: forInit, the condition expr, and forUpdate. This method pulls each piece out and packages them into a ForNode.

### Complex Snippet: visitSwitchStmt

```java
public ASTNode visitSwitchStmt(MiniLangParser.SwitchStmtContext ctx) {
    ASTNode subject = visit(ctx.expr());   // The value being switched on

    List<SwitchNode.SwitchCase> cases = new ArrayList<>();
    for (MiniLangParser.CaseClauseContext c : ctx.caseClause()) {
        LiteralNode caseValue = (LiteralNode) visit(c.literal());  // case 1:
        List<ASTNode> body = new ArrayList<>();
        for (MiniLangParser.StatementContext s : c.statement()) {
            body.add(visit(s));  // Each statement inside the case
        }
        cases.add(new SwitchNode.SwitchCase(caseValue, body));
    }
    // ... handle default clause ...
    return new SwitchNode(line(ctx), column(ctx), subject, cases, defaultBody);
}
```

**Why cast to LiteralNode?** The grammar forces case values to be literals (numbers, strings, bools). You can't write `case x:` where x is a variable — that's by design.

### Complex Snippet: visitUnaryMinusExpr

```java
public ASTNode visitUnaryMinusExpr(MiniLangParser.UnaryMinusExprContext ctx) {
    return new UnaryOpNode(line(ctx), column(ctx), "-", visit(ctx.atom()));
    //                                                          ^^^^^^^^
    //                                                   atom, NOT expr!
}
```

**Why ctx.atom() not ctx.expr()?** This is the precedence fix. The grammar says `'-' atom`, so the minus can only grab a single atom (a single number, variable, or parenthesized expression). This prevents `-5 + 3` from being parsed as `-(5 + 3)`.

---

## 7. src/semantic/ — The Rule Checker (Compile-Time Validation)

### 7a. MiniLangType.java — The Type Enum

```java
public enum MiniLangType {
    INT, FLOAT, BOOL, STRING, ARRAY, VOID, UNKNOWN
}
```

**Why UNKNOWN?** When the analyzer encounters an error (undefined variable), it can't determine the type. Instead of crashing, it returns UNKNOWN and continues analyzing — this is error recovery.

### 7b. Symbol.java — A Dictionary Entry

```java
public class Symbol {
    private final String       name;        // "x" or "add"
    private final MiniLangType type;        // INT, FLOAT, etc.
    private final boolean      isFunction;  // true for functions
    private final List<String> paramNames;  // ["a", "b"] for functions
}
```

One class for both variables AND functions. isFunction tells them apart.

### 7c. SymbolTable.java — The Scoped Dictionary

**Purpose**: Stores all known names (variables, functions) with support for nested scopes.

### Complex Snippet: Scope Chain (Linked List)

```java
private static class Scope {
    final Map<String, Symbol> symbols = new HashMap<>();
    final Scope parent;  // null = global scope
}

public Symbol lookup(String name) {
    Scope scope = current;
    while (scope != null) {
        if (scope.symbols.containsKey(name)) {
            return scope.symbols.get(name);
        }
        scope = scope.parent;  // Walk up to parent scope
    }
    return null;  // Not found anywhere
}
```

**How scoping works**: Imagine a stack of transparent papers. Each paper is a scope. When you look for a variable, you look at the top paper first. If it's not there, look through to the next paper down. Global scope is at the bottom.

- pushScope() = add a new paper on top (entering a { block })
- popScope() = remove the top paper (leaving a { block })

### 7d. SemanticAnalyzer.java — The Validator

**Purpose**: Walks the AST BEFORE execution to catch errors early.

### Complex Snippet: Two-Pass Function Registration

```java
public MiniLangType visitProgram(ProgramNode node) {
    // FIRST PASS: register all function declarations
    for (ASTNode stmt : node.getStatements()) {
        if (stmt instanceof FunctionDeclNode) {
            FunctionDeclNode fn = (FunctionDeclNode) stmt;
            symbolTable.define(new Symbol(fn.getName(), MiniLangType.UNKNOWN, fn.getParams()));
        }
    }
    // SECOND PASS: analyze everything
    for (ASTNode stmt : node.getStatements()) {
        stmt.accept(this);
    }
}
```

**Why two passes?** Without this, calling a function that's defined later in the file would fail. The first pass scans for all function declarations and registers their names. Then the second pass can analyze calls to them. This is called **Hoisting**.

### Complex Snippet: Built-in Function Registration

```java
private void registerBuiltIns() {
    String[] builtIns = { "length", "substring", "toInt", "toFloat", "toString", "push", "pop" };
    for (String name : builtIns) {
        symbolTable.define(new Symbol(name, MiniLangType.UNKNOWN, List.of("__builtin__")));
    }
}
```

**Why "__builtin__"?** Built-in functions have variable arities (e.g., push takes 2 args, length takes 1). We mark them with a sentinel value so the arity checker skips them:

```java
boolean isBuiltIn = fn.getParamCount() == 1
        && fn.getParamNames().get(0).equals("__builtin__");
if (!isBuiltIn) {
    // ... strict arity check for user-defined functions ...
}
```

### Complex Snippet: loopDepth for break validation

```java
private int loopDepth = 0;

public MiniLangType visitWhile(WhileNode node) {
    loopDepth++;           // Entering a loop
    node.getBody().accept(this);
    loopDepth--;           // Leaving the loop
}

public MiniLangType visitBreak(BreakNode node) {
    if (loopDepth == 0) {
        errors.add(new SemanticError(..., "'break' used outside of a loop"));
    }
}
```

**How it works**: Every time we enter a loop or switch, we increment loopDepth. When we leave, we decrement. If someone writes break; outside any loop (loopDepth == 0), we catch it.

---

## 8. src/optimizer/Optimizer.java — Constant Folding

**Purpose**: Walks the AST and replaces constant expressions with their pre-computed values. This is a real compiler optimization used by GCC, javac, and LLVM.

### Complex Snippet: The Core — tryFold

```java
public ASTNode visitBinaryOp(BinaryOpNode node) {
    ASTNode left  = node.getLeft().accept(this);   // Optimize children first
    ASTNode right = node.getRight().accept(this);

    // If BOTH sides are literals -> compute NOW
    if (left instanceof LiteralNode && right instanceof LiteralNode) {
        ASTNode folded = tryFold(node, (LiteralNode) left, (LiteralNode) right);
        if (folded != null) return folded;  // Replace entire subtree with one number
    }
    return new BinaryOpNode(..., left, right);  // Can't fold -> return as-is
}
```

**Example transformation**:
```
BEFORE:  BinaryOpNode(+)         AFTER:  LiteralNode(7)
         +-- LiteralNode(3)
         +-- LiteralNode(4)
```

The tree shrinks from 3 nodes to 1. The interpreter never has to compute 3 + 4 at runtime.

### Complex Snippet: Dead Code Elimination (Bonus!)

```java
public ASTNode visitIf(IfNode node) {
    ASTNode condition = node.getCondition().accept(this);
    if (condition instanceof LiteralNode) {
        LiteralNode lit = (LiteralNode) condition;
        if (lit.isBool()) {
            return (boolean) lit.getValue() ? thenBlock : elseBlock;
        }
    }
}
```

**What this does**: If the condition is a constant true or false, the optimizer throws away the entire if node and keeps only the branch that would execute. `if (true) { A } else { B }` becomes just `A`.
