// TEST 11: Switch-case with break and default
day = 3;
switch (day) {
    case 1: print "Monday"; break;
    case 2: print "Tuesday"; break;
    case 3: print "Wednesday"; break;
    case 4: print "Thursday"; break;
    default: print "Weekend";
}

// Switch on string
lang = "Java";
switch (lang) {
    case "Python": print "Python selected"; break;
    case "Java":   print "Java selected";   break;
    default:       print "Other language";
}

// Switch with no match -> default
code = 99;
switch (code) {
    case 1: print "one"; break;
    case 2: print "two"; break;
    default: print "unknown";
}
