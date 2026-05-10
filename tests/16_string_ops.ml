// TEST 16: String operations — built-in functions
s = "Hello, World!";
print length(s);
print substring(s, 0, 5);
print substring(s, 7, 12);

// Concatenation with different types
n = 42;
b = true;
print "Number: " + n;
print "Bool: " + b;

// toInt and toFloat conversions
numStr = "123";
converted = toInt(numStr);
print converted + 1;

pi = toFloat("3.14");
print pi + 1.0;
