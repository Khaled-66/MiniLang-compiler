// TEST 09: Recursion — fibonacci and factorial
function fib(n) {
    if (n <= 1) {
        return n;
    }
    return fib(n - 1) + fib(n - 2);
}

function factorial(n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

print fib(0);
print fib(1);
print fib(5);
print fib(7);

print factorial(1);
print factorial(5);
print factorial(7);
