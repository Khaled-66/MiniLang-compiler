# MiniLang Compiler — Code Walkthrough (Part 1 of 3)
# Pipeline, Grammar, Entry Point, and AST Layer

---

## The Big Picture — How the Pipeline Works

```
Your Code (.ml file)
     |
     v
+----------+   ANTLR reads MiniLang.g4 and auto-generates
|  Lexer   |   MiniLangLexer.java — breaks text into tokens
+----+-----+   (words like "print", numbers like 42, symbols like +)
     |
     v
+----------+
|  Parser  |   MiniLangParser.java — arranges tokens into a Parse Tree
+----+-----+   (checks grammar: "is this sentence valid?")
     |
     v
+--------------+
|  ASTBuilder  |   Our code — converts messy Parse Tree into clean AST
+----+---------+   (strips away semicolons, parentheses, keywords)
     |
     v
+--------------------+
|  SemanticAnalyzer  |   Our code — validates meaning BEFORE running
+----+---------------+   ("does variable x exist? is this function call correct?")
     |
     v
+-------------+
|  Optimizer  |   Our code — Constant Folding: replaces 2+3 with 5
+----+--------+   (bonus optimization pass)
     |
     v
+--------------+
|  Interpreter |   Our code — walks the AST and EXECUTES each node
+----+---------+   (actually runs your program!)
     |
     v
   Output
```

---

## 1. MiniLang.g4 — The Grammar (Rule Book)

**Purpose**: Defines what MiniLang code looks like. ANTLR reads this file and auto-generates MiniLangLexer.java, MiniLangParser.java, MiniLangVisitor.java, and MiniLangBaseVisitor.java.

**What it contains**:
- **Parser rules** (lowercase): program, statement, expr, atom, literal — the structure
- **Lexer rules** (uppercase): PRINT, IF, ID, NUMBER, STRING — the vocabulary

### Complex Snippet: Operator Precedence (The Hardest Part)

```antlr
expr
    : expr op=('*' | '/') expr               # MulDivExpr      <- HIGHEST (listed first)
    | expr op=('+' | '-') expr               # AddSubExpr
    | expr op=('>' | '<' | '>=' | '<=') expr # RelationalExpr
    | expr op=('==' | '!=') expr             # EqualityExpr
    | expr '&&' expr                          # AndExpr
    | expr '||' expr                          # OrExpr          <- LOWEST (listed last)
    | '!' expr                                # NotExpr
    | '-' atom                                # UnaryMinusExpr  <- binds to atom ONLY
    | atom                                    # AtomExpr
    ;
```

**Why this order matters**: In ANTLR4, alternatives listed **FIRST** bind **MORE TIGHTLY**. So * (first) beats + (second), which means `2 + 3 * 4` correctly evaluates as `2 + (3 * 4) = 14`, not `(2 + 3) * 4 = 20`.

**Why `-` grabs `atom` not `expr`**: If we wrote `'-' expr`, then `-5 + 3` would parse as `-(5 + 3) = -8`. By writing `'-' atom`, the minus can only grab a single atom (a number, variable, or parenthesized expression), so it parses as `(-5) + 3 = -2`.

### Complex Snippet: The atom Rule

```antlr
atom
    : '(' expr ')'          # ParenAtom          <- grouping
    | ID '(' argList? ')'   # FunctionCallAtom   <- function call
    | ID '[' expr ']'       # ArrayAccessAtom    <- array indexing
    | '[' argList? ']'      # ArrayLiteralAtom   <- array creation
    | literal               # LiteralAtom        <- numbers, strings, bools
    | ID                    # VariableAtom        <- variable name
    ;
```

**Why a separate rule?** Atoms are the smallest, indivisible pieces of an expression. They form the "leaves" of the expression tree. By separating them from expr, we prevent precedence bugs.

### Complex Snippet: BOOL Token

```antlr
BOOL : 'true' | 'false';
ID   : [a-zA-Z_][a-zA-Z_0-9]*;
```

**Why BOOL comes before ID**: ANTLR gives priority to rules defined earlier. If ID came first, it would match `true` as a variable name instead of a boolean keyword. The order is: keywords first, then BOOL, then ID.

---

## 2. build.ps1 — The Build Script

**Purpose**: One command to generate, compile, and run everything.

| Mode | Command | What happens |
|------|---------|-------------|
| GUI IDE | `.\build.ps1` | Builds + starts web server on port 3000 |
| Run a file | `.\build.ps1 -Run .\tests\01_arithmetic.ml` | Builds + runs that file in terminal |
| Run tests | `.\build.ps1 -Test` | Builds + runs all 17 test cases |

The script does 4 things in order:
1. Runs ANTLR to generate lexer/parser from grammar
2. Collects all .java files (generated + hand-written)
3. Compiles everything into build/ directory
4. Copies GUI assets into build/gui/

---

## 3. src/Main.java — The Front Door

**Purpose**: The entry point. Decides whether to start the GUI web server or run a file from the terminal.

### Complex Snippet: The CLI Pipeline

```java
// Step 1: Read the source file into characters
CharStream input = CharStreams.fromFileName(filePath);

// Step 2: Create the lexer (tokenizer)
MiniLangLexer lexer = new MiniLangLexer(input);
lexer.removeErrorListeners();                    // Remove ANTLR's ugly default errors
lexer.addErrorListener(new CliErrorListener(...)); // Add our clean error format

// Step 3: Create the parser
CommonTokenStream tokens = new CommonTokenStream(lexer);
MiniLangParser parser = new MiniLangParser(tokens);

// Step 4: Parse -> get the parse tree
MiniLangParser.ProgramContext parseTree = parser.program();

// Step 5: Convert parse tree -> our clean AST
ASTBuilder builder = new ASTBuilder(errors);
ProgramNode ast = (ProgramNode) builder.visit(parseTree);

// Step 6: Check for semantic errors (undefined variables, etc.)
SemanticAnalyzer semantic = new SemanticAnalyzer(errors);
semantic.analyze(ast);

// Step 7: Optimize (constant folding)
Optimizer optimizer = new Optimizer();
ast = optimizer.optimize(ast);

// Step 8: Execute!
Interpreter interpreter = new Interpreter(errors);
String output = interpreter.run(ast);
```

**Why removeErrorListeners() then addErrorListener()?** ANTLR's default listener prints ugly messages to stderr. We replace it with CliErrorListener which formats errors as `[Lexer] line 5:3 - ...` — clean and consistent.

### Complex Snippet: CliErrorListener Inner Class

```java
private static class CliErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        if (phase.equals("Lexer")) {
            errors.add(new LexerError(line, charPositionInLine, msg));
        } else {
            errors.add(new ParseError(line, charPositionInLine, msg));
        }
    }
}
```

**Why an inner class?** It only makes sense inside Main — no other file needs it. ANTLR calls syntaxError() automatically whenever it finds bad syntax. We convert it into our error format and add it to the ErrorCollector.

---

## 4. AST Layer — src/ast/

The AST (Abstract Syntax Tree) is the heart of the compiler. Every piece of your source code becomes a **node** in a tree.

### 4a. ASTNode.java — The Base Class

**Purpose**: Every single node (assignment, print, if, literal, etc.) extends this. It stores **where** in the source file the node came from (line + column).

### Complex Snippet: The accept() Method (Visitor Pattern)

```java
public abstract <T> T accept(ASTVisitor<T> visitor);
```

**What this does**: Every node has this method. When the Interpreter says "I want to visit you," the node calls the right method on the visitor. For example, AssignmentNode.accept(visitor) calls visitor.visitAssignment(this).

**Concept: Visitor Pattern**
- **Breakdown**: "Visitor" = someone who goes door-to-door. Each node is a "house." The visitor (Interpreter, Optimizer, etc.) knocks on each door, and the node tells the visitor which room to go to.
- **Why?** It separates the tree STRUCTURE (nodes) from the BEHAVIOR (what to do at each node). You can add new behaviors (like an Optimizer) without changing any node class.

### 4b. ASTVisitor.java — The Contract

**Purpose**: An interface listing every visit*() method — one per node type. The Interpreter, SemanticAnalyzer, and Optimizer all implement this.

```java
public interface ASTVisitor<T> {
    T visitProgram(ProgramNode node);
    T visitAssignment(AssignmentNode node);
    T visitBinaryOp(BinaryOpNode node);
    // ... one method per node type (20 total)
}
```

**Why generic <T>?** Different visitors return different things:
- Interpreter returns MiniLangValue (runtime values like 42, "hello")
- SemanticAnalyzer returns MiniLangType (type info like INT, STRING)
- Optimizer returns ASTNode (transformed tree nodes)

### 4c. ProgramNode.java — The Root

**Purpose**: The top of every AST. Contains a list of all top-level statements. There's exactly ONE per file.

### 4d. Expression Nodes — src/ast/expressions/

| File | Represents | Example |
|------|-----------|---------|
| LiteralNode.java | A concrete value | 42, "hello", true, 3.14 |
| VariableNode.java | A variable name | x, myVar |
| BinaryOpNode.java | Two-operand operation | x + 1, a > b, x && y |
| UnaryOpNode.java | One-operand operation | -5, !flag |
| FunctionCallNode.java | Function call | add(3, x), length(arr) |
| ArrayLiteralNode.java | Array creation | [1, 2, 3] |
| ArrayAccessNode.java | Array indexing | arr[0], nums[i+1] |

#### Complex Snippet: LiteralNode stores Object

```java
private final Object value;  // Integer, Double, Boolean, or String

public boolean isNumeric() { return isInt() || isFloat(); }
```

**Why Object?** Instead of 4 separate classes (IntLiteralNode, StringLiteralNode, etc.), we use ONE class with an Object field. The type is checked at runtime with instanceof. Simpler code, same result.

#### Complex Snippet: BinaryOpNode stores operator as String

```java
private final String operator;  // "+", "-", "*", "/", ">", "==", "&&", "||"
private final ASTNode left;
private final ASTNode right;
```

**Why one class for ALL operators?** All binary ops share the same structure: left OP right. The interpreter uses a switch(operator) to decide what to do. 1 class instead of 12.

### 4e. Statement Nodes — src/ast/statements/

| File | Represents | Example |
|------|-----------|---------|
| AssignmentNode.java | Variable assignment | x = 5; |
| ArrayAssignmentNode.java | Array element assignment | arr[0] = 99; |
| PrintNode.java | Print statement | print x + 1; |
| InputNode.java | User input | x = input("Enter:"); |
| IfNode.java | If/else branching | if (x > 5) { } else { } |
| WhileNode.java | While loop | while (i < 10) { } |
| ForNode.java | For loop | for (i=0; i<10; i=i+1) { } |
| SwitchNode.java | Switch/case | switch(x) { case 1: ... } |
| FunctionDeclNode.java | Function definition | function add(a,b) { } |
| ReturnNode.java | Return from function | return x + 1; |
| BreakNode.java | Break from loop | break; |
| BlockNode.java | Block of statements | { stmt1; stmt2; } |
| ExprStatementNode.java | Expression as statement | doSomething(); |

#### Complex Snippet: SwitchNode has an inner class

```java
public static class SwitchCase {
    private final LiteralNode value;      // The value to match (case 1:)
    private final List<ASTNode> body;     // Statements in this case
}
```

**Why an inner class?** Each case clause has a value AND a list of statements. An inner class bundles them together. SwitchNode then holds a List<SwitchCase>.

#### Complex Snippet: ForNode stores init/update separately

```java
private final String  initVar;      // "i"
private final ASTNode initValue;    // 0
private final ASTNode condition;    // i < 10
private final String  updateVar;    // "i"
private final ASTNode updateValue;  // i + 1
private final ASTNode body;         // the { block }
```

**Why not just nested statements?** The for-loop header has a rigid structure. Storing each part explicitly makes the Interpreter much simpler — it knows exactly where to find the init, condition, and update.
