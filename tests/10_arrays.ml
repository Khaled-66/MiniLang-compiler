// TEST 10: Arrays — creation, access, mutation, built-ins
nums = [1, 2, 3, 4, 5];
print nums[0];
print nums[4];

// Modify element
nums[2] = 99;
print nums[2];

// Length
print length(nums);

// Push and pop
push(nums, 6);
print length(nums);
print nums[5];

last = pop(nums);
print last;
print length(nums);

// Array of strings
words = ["hello", "world", "foo"];
print words[1];
