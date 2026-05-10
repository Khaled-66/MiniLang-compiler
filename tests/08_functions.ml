// TEST 08: Functions — declaration, call, return value
function add(a, b) {
    return a + b;
}

function multiply(x, y) {
    return x * y;
}

function greet(name) {
    return "Hello, " + name + "!";
}

print add(3, 4);
print multiply(5, 6);
print greet("World");

// Function using other functions
function addAndDouble(a, b) {
    return multiply(add(a, b), 2);
}
print addAndDouble(3, 4);
