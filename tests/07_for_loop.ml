// TEST 07: For loop
for (i = 1; i <= 5; i = i + 1) {
    print i;
}

// For loop to compute factorial
result = 1;
for (k = 2; k <= 6; k = k + 1) {
    result = result * k;
}
print result;

// Nested for
for (row = 1; row <= 3; row = row + 1) {
    for (col = 1; col <= 3; col = col + 1) {
        print row * col;
    }
}
