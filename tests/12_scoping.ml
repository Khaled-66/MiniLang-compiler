// TEST 12: Nested scoping — variables in inner blocks are local
x = 10;
if (true) {
    y = 20;           // y is local to this block
    print x;          // can see outer x
    print y;
}
// y is not accessible here — but x still is
print x;

// Function scope isolation
function makeCounter() {
    count = 0;
    count = count + 1;
    return count;
}
print makeCounter();
print makeCounter();  // each call starts fresh
