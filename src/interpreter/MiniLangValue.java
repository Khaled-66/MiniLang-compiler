package interpreter;

import java.util.List;

// =============================================================================
// MiniLangValue.java  —  A runtime value with its type attached
//
// Pipeline Position:  Used exclusively by the Interpreter at runtime
//
// WHY wrap values in a class instead of using raw Java objects?
//   - The interpreter needs to know the TYPE of every value at runtime
//     for string concatenation vs. numeric addition (both use "+")
//   - Wrapping allows us to add methods like isNumeric(), isTruthy()
//     that make interpreter logic much cleaner
//   - A single type serves as the return type for ALL visitor methods
// =============================================================================
public class MiniLangValue {

    // The actual Java value stored inside
    // Can be: Integer, Double, Boolean, String, List<MiniLangValue>
    private final Object raw;

    // ── Constructors ──────────────────────────────────────────
    public MiniLangValue(int value)              { this.raw = value; }
    public MiniLangValue(double value)           { this.raw = value; }
    public MiniLangValue(boolean value)          { this.raw = value; }
    public MiniLangValue(String value)           { this.raw = value; }
    public MiniLangValue(List<MiniLangValue> v)  { this.raw = v; }

    // Null/void value (for void function returns)
    public static final MiniLangValue VOID = new MiniLangValue((Object) null);

    private MiniLangValue(Object raw) { this.raw = raw; }

    // ── Type Checks ───────────────────────────────────────────
    public boolean isInt()     { return raw instanceof Integer; }
    public boolean isFloat()   { return raw instanceof Double; }
    public boolean isBool()    { return raw instanceof Boolean; }
    public boolean isString()  { return raw instanceof String; }
    public boolean isArray()   { return raw instanceof List; }
    public boolean isVoid()    { return raw == null; }
    public boolean isNumeric() { return isInt() || isFloat(); }

    // ── Raw Access ────────────────────────────────────────────
    public Object getRaw() { return raw; }

    // ── Typed Access (with safe casts) ────────────────────────
    public int    asInt()    { return (Integer) raw; }
    public double asDouble() {
        if (isInt()) return ((Integer) raw).doubleValue();
        return (Double) raw;
    }
    public boolean    asBool()   { return (Boolean) raw; }
    public String     asString() { return (String) raw; }

    @SuppressWarnings("unchecked")
    public List<MiniLangValue> asArray() { return (List<MiniLangValue>) raw; }

    // ── Truthiness (for conditions) ───────────────────────────
    // In MiniLang: false, 0, 0.0, "", [] are all falsy. Everything else is truthy.
    public boolean isTruthy() {
        if (isBool())   return asBool();
        if (isInt())    return asInt() != 0;
        if (isFloat())  return asDouble() != 0.0;
        if (isString()) return !asString().isEmpty();
        if (isArray())  return !asArray().isEmpty();
        if (isVoid())   return false;
        return true;
    }

    // ── Display ───────────────────────────────────────────────
    @Override
    public String toString() {
        if (isVoid())   return "void";
        if (isInt())    return Integer.toString(asInt());
        if (isFloat()) {
            double d = asDouble();
            // Print 3.0 as "3.0" (not "3") to distinguish from int
            return Double.toString(d);
        }
        if (isBool())   return Boolean.toString(asBool());
        if (isString()) return asString();
        if (isArray()) {
            StringBuilder sb = new StringBuilder("[");
            List<MiniLangValue> list = asArray();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i < list.size() - 1) sb.append(", ");
            }
            sb.append("]");
            return sb.toString();
        }
        return "null";
    }
}
