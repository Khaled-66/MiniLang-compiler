// =============================================================================
// MiniLang.g4  —  Complete Grammar for MiniLang Interpreter
//
// Pipeline Position:  Source Code  →  [LEXER + PARSER]  →  Parse Tree
//
// ANTLR reads this file and auto-generates:
//   - MiniLangLexer.java      (tokenizer)
//   - MiniLangParser.java     (syntax checker + parse tree builder)
//   - MiniLangVisitor.java    (visitor interface we implement)
//   - MiniLangBaseVisitor.java (default visitor we extend)
//
// KEY ANTLR4 PRECEDENCE RULE:
//   In a left-recursive expr rule, alternatives listed FIRST bind
//   MORE TIGHTLY (higher precedence). So * / must come BEFORE + -.
// =============================================================================

grammar MiniLang;

// ─────────────────────────────────────────────────────────────
// PARSER RULES  (start with lowercase)
// ─────────────────────────────────────────────────────────────

program
    : statement* EOF
    ;

statement
    : assignmentStmt      // x = expr;
    | arrayAssignStmt     // arr[i] = expr;
    | inputStmt           // x = input("prompt");
    | printStmt           // print expr;
    | ifStmt              // if (expr) { } else { }
    | whileStmt           // while (expr) { }
    | forStmt             // for (init; cond; update) { }
    | switchStmt          // switch (expr) { case ...: }
    | functionDecl        // function name(params) { }
    | returnStmt          // return expr;
    | breakStmt           // break;
    | exprStmt            // functionCall();
    ;

// ── Assignment ───────────────────────────────────────────────
// NOTE: assignmentStmt and inputStmt BOTH start with  ID '='
// ANTLR resolves this with its LL(*) look-ahead: it peeks
// further ahead to distinguish them via the INPUT token.
assignmentStmt
    : ID '=' expr ';'
    ;

arrayAssignStmt
    : ID '[' expr ']' '=' expr ';'
    ;

// ── Print ─────────────────────────────────────────────────────
printStmt
    : PRINT expr ';'
    ;

// ── Input ─────────────────────────────────────────────────────
inputStmt
    : ID '=' INPUT '(' STRING ')' ';'
    ;

// ── If / Else ─────────────────────────────────────────────────
ifStmt
    : IF '(' expr ')' block (ELSE block)?
    ;

// ── While ─────────────────────────────────────────────────────
whileStmt
    : WHILE '(' expr ')' block
    ;

// ── For Loop ──────────────────────────────────────────────────
forStmt
    : FOR '(' forInit expr ';' forUpdate ')' block
    ;

forInit
    : ID '=' expr ';'
    ;

forUpdate
    : ID '=' expr
    ;

// ── Switch-Case ───────────────────────────────────────────────
switchStmt
    : SWITCH '(' expr ')' '{' caseClause* defaultClause? '}'
    ;

caseClause
    : CASE literal ':' statement*
    ;

defaultClause
    : DEFAULT ':' statement*
    ;

// ── Function Declaration ──────────────────────────────────────
functionDecl
    : FUNCTION ID '(' paramList? ')' block
    ;

paramList
    : ID (',' ID)*
    ;

// ── Return / Break ────────────────────────────────────────────
returnStmt
    : RETURN expr? ';'
    ;

breakStmt
    : BREAK ';'
    ;

// ── Expression Statement (e.g. standalone function call) ──────
exprStmt
    : expr ';'
    ;

// ── Block ─────────────────────────────────────────────────────
block
    : '{' statement* '}'
    ;

// ─────────────────────────────────────────────────────────────
// EXPRESSIONS  —  ANTLR4 Operator Precedence
//
// CRITICAL: In ANTLR4 left-recursive rules, alternatives listed
// FIRST have HIGHER precedence (bind more tightly).
//
// Precedence table (highest first):
//   1. Primary atoms       literals, IDs, calls, arrays, (expr)
//   2. Unary               ! -
//   3. Multiplicative      * /
//   4. Additive            + -
//   5. Relational          > < >= <=
//   6. Equality            == !=
//   7. Logical AND         &&
//   8. Logical OR          ||   (lowest)
// ─────────────────────────────────────────────────────────────

expr
    // ── Multiplicative (HIGHEST binary precedence — listed first)
    : expr op=('*' | '/') expr               # MulDivExpr

    // ── Additive
    | expr op=('+' | '-') expr               # AddSubExpr

    // ── Relational
    | expr op=('>' | '<' | '>=' | '<=') expr # RelationalExpr

    // ── Equality
    | expr op=('==' | '!=') expr             # EqualityExpr

    // ── Logical AND
    | expr '&&' expr                          # AndExpr

    // ── Logical OR (LOWEST binary precedence — listed last)
    | expr '||' expr                          # OrExpr

    // ── Unary prefix (right-associative; '-' binds to atom only)
    | '!' expr                                # NotExpr
    | '-' atom                                # UnaryMinusExpr

    // ── Atom (primary — delegate to atom rule)
    | atom                                    # AtomExpr
    ;

// atom = an indivisible primary expression.
// Unary '-' applies only to atoms, preventing -(a+b) ambiguity with -a+b.
atom
    : '(' expr ')'                            # ParenAtom
    | ID '(' argList? ')'                     # FunctionCallAtom
    | ID '[' expr ']'                         # ArrayAccessAtom
    | '[' argList? ']'                        # ArrayLiteralAtom
    | literal                                 # LiteralAtom
    | ID                                      # VariableAtom
    ;

// ── Argument list (for function calls and array literals)
argList
    : expr (',' expr)*
    ;

// ── Literals (concrete values in source code)
literal
    : NUMBER                                  # NumberLiteral
    | STRING                                  # StringLiteral
    | BOOL                                    # BoolLiteral
    ;

// ─────────────────────────────────────────────────────────────
// LEXER RULES  (start with uppercase)
// Order matters: earlier rules take priority over later ones.
// Keywords MUST come before ID so 'if' is not matched as an ID.
// ─────────────────────────────────────────────────────────────

// ── Keywords
PRINT    : 'print';
IF       : 'if';
ELSE     : 'else';
WHILE    : 'while';
FOR      : 'for';
SWITCH   : 'switch';
CASE     : 'case';
DEFAULT  : 'default';
BREAK    : 'break';
FUNCTION : 'function';
RETURN   : 'return';
INPUT    : 'input';

// ── Boolean literals — defined as a single token (not split TRUE/FALSE)
//    This way 'true'/'false' are always BOOL tokens, never ID tokens.
BOOL     : 'true' | 'false';

// ── Identifier (must come AFTER all keywords)
ID       : [a-zA-Z_][a-zA-Z_0-9]*;

// ── Numeric literal (int or float)
NUMBER   : [0-9]+ ('.' [0-9]+)?;

// ── String literal
STRING   : '"' (~["\\\r\n])* '"';

// ── Skip whitespace and comments
LINE_COMMENT  : '//' ~[\r\n]*     -> skip;
BLOCK_COMMENT : '/*' .*? '*/'     -> skip;
WS            : [ \t\r\n]+        -> skip;