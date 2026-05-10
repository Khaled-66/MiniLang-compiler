# MiniLang Compiler System

A complete programming language compiler and interpreter built from scratch using **ANTLR4** and **Java**.
This project implements the full compilation pipeline — from raw source code to executed output — including
lexical analysis, parsing, AST construction, semantic analysis, optimization, interpretation, and a web-based IDE.
S
---

## System Architecture

```
Source Code (.ml)
       |
       v
  +---------+     Breaks raw text into tokens
  |  LEXER  |     (keywords, numbers, strings, operators)
  +---------+
       |
       v
  +---------+     Arranges tokens into a tree using grammar rules
  | PARSER  |     (validates syntax: "is this sentence legal?")
  +---------+
       |
       v
  +-------------+   Strips away noise (semicolons, parens) and
  | AST BUILDER |   builds a clean, typed Abstract Syntax Tree
  +-------------+
       |
       v
  +--------------------+   Validates logic before running:
  | SEMANTIC ANALYZER  |   undefined vars, arity, scope rules
  +--------------------+
       |
       v
  +-------------+   Constant folding (2+3 becomes 5)
  |  OPTIMIZER  |   Dead code elimination (if true -> skip else)
  +-------------+
       |
       v
  +---------------+   Walks the AST and executes each node.
  |  INTERPRETER  |   Produces program output.
  +---------------+
       |
       v
    OUTPUT
```

---

## Language Features

| Category | Feature | Example |
|----------|---------|---------|
| **Variables** | Assignment | `x = 42;` |
| **Arithmetic** | Operators with correct precedence | `y = (2 + 3) * 4 / 2;` |
| **Strings** | Literals and concatenation | `print "Hello, " + name + "!";` |
| **Booleans** | `true` / `false` and logical operators | `flag = x > 5 && !done;` |
| **If / Else** | Conditional branching | `if (x > 5) { ... } else { ... }` |
| **While** | Condition-based loop | `while (i < 10) { i = i + 1; }` |
| **For** | C-style loop | `for (i = 0; i < 10; i = i + 1) { ... }` |
| **Functions** | Declaration, parameters, return | `function add(a, b) { return a + b; }` |
| **Recursion** | Functions calling themselves | `function fib(n) { return fib(n-1) + fib(n-2); }` |
| **Closures** | Functions capture their defining scope | A function remembers variables from where it was born |
| **Arrays** | Creation, indexing, mutation | `nums = [10, 20, 30]; print nums[0];` |
| **Switch/Case** | Multi-branch matching with break | `switch (day) { case 1: print "Mon"; break; }` |
| **Built-ins** | Standard library functions | `length()`, `push()`, `pop()`, `substring()`, `toInt()`, `toFloat()`, `toString()` |
| **Input** | User input from console | `x = input("Enter a number: ");` |
| **Comments** | Single-line and block comments | `// comment` and `/* block */` |
| **Print** | Output to console | `print x + 1;` |
| **Error Recovery** | Collect all errors, not just the first | Reports multiple errors in one run |

---

## Project Structure

```
compiler-assignment/
|
|-- MiniLang.g4                     Grammar (lexer + parser rules)
|-- build.ps1                       Build, run, and test script
|-- antlr-4.13.1-complete.jar       ANTLR4 runtime library
|
|-- src/
|   |-- Main.java                   Entry point (CLI mode + GUI server mode)
|   |
|   |-- parser/
|   |   +-- ASTBuilder.java         Parse Tree -> custom AST conversion
|   |
|   |-- ast/                        Abstract Syntax Tree node classes
|   |   |-- ASTNode.java            Base class (line, column, accept)
|   |   |-- ASTVisitor.java         Generic visitor interface
|   |   |-- ProgramNode.java        Root node
|   |   |-- expressions/            LiteralNode, VariableNode, BinaryOpNode,
|   |   |                           UnaryOpNode, FunctionCallNode,
|   |   |                           ArrayLiteralNode, ArrayAccessNode
|   |   +-- statements/             AssignmentNode, PrintNode, IfNode,
|   |                               WhileNode, ForNode, SwitchNode,
|   |                               FunctionDeclNode, ReturnNode, BreakNode,
|   |                               BlockNode, InputNode, ExprStatementNode,
|   |                               ArrayAssignmentNode
|   |
|   |-- semantic/                   Compile-time validation
|   |   |-- SemanticAnalyzer.java   Walks AST, checks rules, reports errors
|   |   |-- SymbolTable.java        Scoped name lookup (linked-list of maps)
|   |   |-- Symbol.java             Single entry: name + type + function info
|   |   +-- MiniLangType.java       Type enum: INT, FLOAT, BOOL, STRING, ARRAY, VOID
|   |
|   |-- interpreter/                Runtime execution engine
|   |   |-- Interpreter.java        Visits each AST node and executes it
|   |   |-- Environment.java        Scoped variable storage (runtime)
|   |   |-- MiniLangValue.java      Runtime value wrapper with type info
|   |   |-- MiniLangFunction.java   Closure: function + captured scope
|   |   |-- ReturnException.java    Control flow for return statements
|   |   +-- BreakException.java     Control flow for break statements
|   |
|   |-- optimizer/
|   |   +-- Optimizer.java          Constant folding and dead code elimination
|   |
|   |-- errors/                     Error reporting system
|   |   |-- MiniLangError.java      Abstract base error class
|   |   |-- LexerError.java         Tokenization errors
|   |   |-- ParseError.java         Grammar/syntax errors
|   |   |-- SemanticError.java      Meaning errors (undefined var, bad arity)
|   |   |-- RuntimeError.java       Execution errors (division by zero)
|   |   +-- ErrorCollector.java     Aggregates errors from all phases
|   |
|   +-- server/                     Web IDE backend
|       |-- CompilerServer.java     HTTP server (serves GUI + runs code)
|       +-- ASTSerializer.java      Converts AST to JSON for the browser
|
|-- gui/
|   +-- index.html                  Web IDE (editor + console + AST viewer)
|
|-- tests/
|   |-- *.ml                        17 test programs
|   |-- expected/                   Expected output for each test
|   +-- test_runner.ps1             Automated test runner
|
|-- walkthrough/                    Detailed code explanations
|   |-- part1_pipeline_grammar_ast.md
|   |-- part2_errors_semantic_optimizer.md
|   +-- part3_interpreter_server_gui.md
|
+-- generated/                      ANTLR auto-generated files (do not edit)
```

---

## Prerequisites

- **Java JDK 17+** — `java` and `javac` must be available in your terminal
- **ANTLR4 JAR** — `antlr-4.13.1-complete.jar` must be in the project root

Verify Java is installed:
```powershell
java -version
javac -version
```

---

## How to Run

### Option 1: Start the GUI IDE (Recommended)

```powershell
cd d:\assignments\compiler-assignment
.\build.ps1
```

This will:
1. Generate the lexer and parser from `MiniLang.g4`
2. Compile all Java source files
3. Start the web server
4. Open **http://localhost:3000** in your browser automatically

The IDE includes:
- A code editor with syntax highlighting
- Sample programs (Hello World, Fibonacci, Arrays, Switch)
- An output console
- An error panel with formatted error messages
- An interactive AST (Abstract Syntax Tree) visualizer
- Keyboard shortcut: **Ctrl+Enter** to run

### Option 2: Run a File from the Terminal

```powershell
.\build.ps1 -Run .\tests\08_functions.ml
```

### Option 3: Run All 17 Tests

```powershell
.\build.ps1 -Test
```

Expected result: **17 / 17 passed**

---

## Test Suite

| # | Test File | What It Validates |
|---|-----------|------------------|
| 01 | `01_arithmetic.ml` | Basic arithmetic and operator precedence |
| 02 | `02_variables.ml` | Variable assignment and reuse |
| 03 | `03_strings.ml` | String literals and concatenation |
| 04 | `04_booleans.ml` | Boolean values, logical operators |
| 05 | `05_if_else.ml` | If/else branching |
| 06 | `06_while.ml` | While loops |
| 07 | `07_for.ml` | For loops |
| 08 | `08_functions.ml` | Function declarations and calls |
| 09 | `09_recursion.ml` | Recursive functions (Fibonacci) |
| 10 | `10_arrays.ml` | Arrays: creation, indexing, push, pop, length |
| 11 | `11_switch.ml` | Switch/case with break and default |
| 12 | `12_scoping.ml` | Variable scoping and shadowing |
| 13 | `13_floats.ml` | Floating-point numbers |
| 14 | `14_error_divzero.ml` | Runtime error: division by zero |
| 15 | `15_error_undefined.ml` | Semantic error: undefined variable |
| 16 | `16_strings_advanced.ml` | String built-ins: substring, conversion |
| 17 | `17_edge_cases.ml` | Precedence, short-circuit, nested returns |

---

## Key Design Decisions

### 1. Visitor Pattern for AST Traversal
Each AST node has an `accept(visitor)` method. The Interpreter, SemanticAnalyzer, and Optimizer
all implement the same `ASTVisitor<T>` interface — separating tree structure from behavior.

### 2. Error Recovery
The compiler collects ALL errors across all phases (lexer, parser, semantic, runtime) instead of
stopping at the first one. This lets the user fix multiple issues per compile cycle.

### 3. Closures via Environment Chains
Functions capture the `Environment` where they were defined. When called later, they create a
child scope from that captured environment — not from the caller's scope.

### 4. Constant Folding Optimization
The Optimizer walks the AST before execution and pre-computes constant expressions. For example,
`x = 2 * 3 + 10;` becomes `x = 16;` — the interpreter never evaluates the arithmetic.

### 5. Control Flow via Exceptions
`return` and `break` statements use Java exceptions (`ReturnException`, `BreakException`) to
instantly unwind nested blocks. This is a standard interpreter technique — not an error.

---

## Documentation

The `walkthrough/` folder contains a detailed explanation of every file in the project:

| Part | File | Covers |
|------|------|--------|
| 1 | `part1_pipeline_grammar_ast.md` | Pipeline, grammar precedence, Main.java, all AST node classes |
| 2 | `part2_errors_semantic_optimizer.md` | Error system, ASTBuilder, semantic analysis, optimizer |
| 3 | `part3_interpreter_server_gui.md` | Interpreter, closures, server, GUI, tests |
