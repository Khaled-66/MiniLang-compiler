package semantic;

// =============================================================================
// MiniLangType.java  —  Enum of all data types the language supports
//
// Pipeline Position:  Used by SemanticAnalyzer and Interpreter
//
// WHY an enum?
//   An enum is a fixed set of named constants. Since MiniLang has exactly
//   5 possible types (INT, FLOAT, BOOL, STRING, VOID), an enum is cleaner
//   than using magic strings like "int" or "float" which could be misspelled.
//
// VOID is used as the "return type" for functions that don't return a value.
// ARRAY is used for list variables.
// UNKNOWN is used when type cannot be determined (error recovery).
// =============================================================================
public enum MiniLangType {
    INT,        // Whole numbers:   42, -5, 0
    FLOAT,      // Decimal numbers: 3.14, -0.5
    BOOL,       // true or false
    STRING,     // "hello world"
    ARRAY,      // [1, 2, 3]
    VOID,       // No value (function returns nothing)
    UNKNOWN     // Used when type is unresolvable (after a semantic error)
}
