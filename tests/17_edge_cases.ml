// TEST 17: Edge cases — operator precedence, short-circuit, empty returns
// Operator precedence: * before +
result = 2 + 3 * 4;
print result;          // Should be 14, not 20

// Short-circuit: || stops at first true
x = 5;
if (x > 0 || x / 0 > 1) {
    print "short-circuit OR works";
}

// Short-circuit: && stops at first false
if (x < 0 && x / 0 > 1) {
    print "should not print";
} else {
    print "short-circuit AND works";
}

// Nested function calls
function double(n) { return n * 2; }
function inc(n)    { return n + 1; }
print double(inc(double(3)));

// Empty array
empty = [];
print length(empty);
push(empty, 42);
print length(empty);
