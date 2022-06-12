/*

168. Excel Sheet Column Title

Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...

*/
---------------------------------

class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while(columnNumber != 0){
            columnNumber--; // to make A=0, B=1, Z=25 when doing %26
            sb = sb.insert(0,(char)(columnNumber%26+'A')); // insert before the string
            columnNumber /= 26;
        }
        return sb.toString(); // we cannot return StringBuilder when return type is String. Also we cannot directly convert StringBuilder to String, so we use toString() method.
    }
}

// We have to do columnNumber-- as if we dont do it then
// A=1
// B=2
// Y=25 
// Z=0, but 0 could also mean columnNumber=0 which in excel counting system is not possible
// so to fix that, we subtract 1 from columnNumber and then to mod 26
// Now, A=0
// B=1
// ..Z=25
