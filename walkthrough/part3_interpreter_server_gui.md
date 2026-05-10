# MiniLang Compiler — Code Walkthrough (Part 3 of 3)
# Interpreter, Server, GUI, and How to Run

---

## 9. Interpreter Layer — src/interpreter/

This is the **engine** that actually runs your code.

### 9a. Environment.java — Runtime Memory

**Purpose**: Stores variable values at runtime. Same linked-list structure as SymbolTable, but stores actual values instead of types.

| Method | What it does |
|--------|-------------|
| set(name, value) | Walks up the scope chain to find where the variable was defined, then updates it there. If not found, creates it in the current scope. |
| define(name, value) | Always creates in the current scope (used for function parameters). |
| get(name) | Walks up the scope chain and returns the value, or null if not found. |
| createChild() | Creates a new scope that points to this one as its parent. |

### Complex Snippet: set() vs define()

```java
public void set(String name, MiniLangValue value) {
    Environment scope = findScope(name);  // Walk up to find where it was defined
    if (scope != null) {
        scope.values.put(name, value);    // Re-assign in ORIGINAL scope
    } else {
        values.put(name, value);          // First time -> create locally
    }
}

public void define(String name, MiniLangValue value) {
    values.put(name, value);              // Always create in CURRENT scope
}
```

**Why two methods?** set() is for assignments (x = 5;) — if x exists in a parent scope, it should update THAT scope, not create a new local x. define() is for function parameters — they should always be local.

### 9b. MiniLangValue.java — Value Wrapper

**Purpose**: Wraps every runtime value (int, double, boolean, string, array) in a single type.

### Complex Snippet: Truthiness

```java
public boolean isTruthy() {
    if (isBool())   return asBool();           // false -> falsy
    if (isInt())    return asInt() != 0;        // 0 -> falsy
    if (isFloat())  return asDouble() != 0.0;   // 0.0 -> falsy
    if (isString()) return !asString().isEmpty();// "" -> falsy
    if (isArray())  return !asArray().isEmpty(); // [] -> falsy
    if (isVoid())   return false;               // void -> falsy
    return true;
}
```

**Why?** MiniLang conditions accept any value, not just booleans. `if (x)` is true when x is non-zero, non-empty, etc. This is the same behavior as JavaScript and Python.

### 9c. MiniLangFunction.java — Closure

**Purpose**: Stores a function declaration + the environment it was defined in.

```java
private final FunctionDeclNode declaration;  // The AST node (has params + body)
private final Environment closureEnv;        // The scope at DEFINITION time
```

**Concept: Closure**
- **Breakdown**: "Closure" because the function "closes over" (captures) the variables from its surrounding scope. When the function is called later, it can still access those variables.
- Example: `x = 10; function getX() { return x; }` — getX closes over x.

### 9d. ReturnException.java / BreakException.java — Control Flow

**Purpose**: When `return 5;` runs deep inside nested if/while blocks, the interpreter needs to INSTANTLY unwind back to the function call site. Java exceptions do exactly this.

```java
public class ReturnException extends RuntimeException {
    private final MiniLangValue value;

    public ReturnException(MiniLangValue value) {
        super(null, null, true, false);  // Don't bother with stack trace
        this.value = value;
    }
}
```

**Why exceptions for control flow?** This is NOT an error — it's deliberate. When the interpreter visits a ReturnNode, it throws a ReturnException. The visitFunctionCall method catches it and extracts the return value. This is a standard interpreter technique.

### 9e. Interpreter.java — The Execution Engine

**Purpose**: Walks the AST node-by-node and executes each one.

### Complex Snippet: Short-Circuit Evaluation

```java
if (op.equals("&&")) {
    MiniLangValue left = node.getLeft().accept(this);
    if (!left.isTruthy()) return new MiniLangValue(false);  // Don't evaluate right!
    return new MiniLangValue(node.getRight().accept(this).isTruthy());
}
if (op.equals("||")) {
    MiniLangValue left = node.getLeft().accept(this);
    if (left.isTruthy()) return new MiniLangValue(true);    // Don't evaluate right!
    return new MiniLangValue(node.getRight().accept(this).isTruthy());
}
```

**Why short-circuit?** In `false && crash()`, the crash() function should NOT be called. If the left side of && is false, the whole thing is false — no need to evaluate the right. Same for `true || anything`.

### Complex Snippet: Function Call Execution

```java
public MiniLangValue visitFunctionCall(FunctionCallNode node) {
    MiniLangValue builtIn = tryBuiltIn(node);       // Check built-ins first
    if (builtIn != null) return builtIn;

    MiniLangFunction fn = /* look up function by name */;

    // Evaluate arguments in the CALLER's scope
    List<MiniLangValue> args = new ArrayList<>();
    for (ASTNode arg : node.getArguments()) {
        args.add(arg.accept(this));
    }

    // Create new scope rooted at the function's CLOSURE environment
    Environment callEnv = fn.getClosureEnv().createChild();

    // Bind parameter names to argument values
    for (int i = 0; i < params.size(); i++) {
        callEnv.define(params.get(i), args.get(i));
    }

    // Execute body — catch ReturnException for return values
    Environment savedEnv = globalEnv;
    globalEnv = callEnv;
    try {
        fn.getDeclaration().getBody().accept(this);
        return MiniLangValue.VOID;              // No return statement hit
    } catch (ReturnException ret) {
        return ret.getValue();                   // Got a return value!
    } finally {
        globalEnv = savedEnv;                   // ALWAYS restore caller's scope
    }
}
```

**Step by step:**
1. Check if it's a built-in function (length, push, etc.)
2. Evaluate all arguments in the caller's scope (important: BEFORE switching scopes)
3. Create a new child scope from the function's closure environment
4. Bind each parameter name to its argument value
5. Switch to the new scope and execute the body
6. If a ReturnException is thrown, catch it and return the value
7. **Always** restore the caller's scope in finally (even if an error occurs)

### Complex Snippet: Built-in Functions

```java
private MiniLangValue tryBuiltIn(FunctionCallNode node) {
    switch (node.getName()) {
        case "length": {
            MiniLangValue v = args.get(0).accept(this);
            if (v.isString()) return new MiniLangValue(v.asString().length());
            if (v.isArray())  return new MiniLangValue(v.asArray().size());
        }
        case "push": {
            MiniLangValue arr = args.get(0).accept(this);
            arr.asArray().add(args.get(1).accept(this));  // Mutates the array
            return MiniLangValue.VOID;
        }
        // ... substring, toInt, toFloat, toString, pop ...
    }
}
```

**Why separate from user functions?** Built-in functions are implemented in Java, not in MiniLang. They don't have AST bodies — they directly manipulate MiniLangValue objects.

---

## 10. Server Layer — src/server/

### 10a. CompilerServer.java — HTTP Bridge

**Purpose**: A lightweight HTTP server that connects the browser GUI to the compiler. Starts once, handles all requests in <50ms.

| Endpoint | Method | What it does |
|----------|--------|-------------|
| GET / | GET | Serves the GUI HTML page |
| POST /run | POST | Runs source code through the full pipeline, returns JSON |

### Complex Snippet: The /run endpoint

```java
private static void handleRun(HttpExchange exchange) throws IOException {
    String source = new String(exchange.getRequestBody().readAllBytes());
    String jsonResponse = runPipeline(source);    // Full pipeline!
    exchange.sendResponseHeaders(200, bytes.length);
    exchange.getResponseBody().write(bytes);
}
```

runPipeline() runs the exact same pipeline as CLI mode (lex -> parse -> AST -> semantic -> interpret) but returns JSON instead of printing to stdout.

### 10b. ASTSerializer.java — AST to JSON

**Purpose**: Converts our Java AST tree into a JSON string so the browser can display it.

```java
// Output example:
// { "type": "AssignmentNode", "varName": "x",
//   "children": [{ "type": "LiteralNode", "value": "5" }] }
```

Uses instanceof checks to serialize each node type differently. Each node gets a type field, node-specific fields (varName, operator, name), and a children array for child nodes.

---

## 11. GUI — gui/index.html

**Purpose**: A single-file web IDE with editor, console, and AST visualizer. All HTML + CSS + JavaScript in one file.

| Feature | How it works |
|---------|-------------|
| **Code Editor** | A textarea with a transparent overlay for syntax highlighting |
| **Syntax Highlighting** | Regex-based tokenizer paints keywords red, strings blue, numbers cyan, comments gray |
| **Run Button** | Sends a POST /run request to the Java server with the code as the body |
| **Output Panel** | Displays the output field from the JSON response |
| **Error Panel** | Parses error messages and displays them with phase, message, and line number |
| **AST Panel** | Recursively builds an expandable/collapsible tree from the ast JSON object |
| **Sample Buttons** | Preloads Hello World, Fibonacci, Arrays, or Switch examples |
| **Keyboard Shortcut** | Ctrl+Enter runs the code |

---

## 12. Tests — tests/

17 .ml test files + expected output files in tests/expected/:

| Test | Feature Tested |
|------|---------------|
| 01-03 | Arithmetic, variables, strings |
| 04-05 | Booleans, if/else |
| 06-07 | While loops, for loops |
| 08-09 | Functions, recursion |
| 10 | Arrays (push, pop, length, indexing) |
| 11 | Switch/case with break and default |
| 12 | Variable scoping and shadowing |
| 13 | Float/decimal number operations |
| 14-15 | Error handling (division by zero, undefined variable) |
| 16 | String operations (substring, concatenation, conversion) |
| 17 | Edge cases (precedence, short-circuit, nested returns) |

test_runner.ps1 runs each test, compares output against expected, and reports PASS/FAIL.

---

## How to Run Everything

### Start the GUI IDE
```powershell
cd d:\assignments\compiler-assignment
.\build.ps1
```
Then open **http://localhost:3000** in your browser.

### Run a single file
```powershell
.\build.ps1 -Run .\tests\09_recursion.ml
```

### Run all 17 tests
```powershell
.\build.ps1 -Test
```

Expected result: **17 / 17 passed**

### Stop the server
Press Ctrl+C in the PowerShell window.
