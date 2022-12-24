/*
1017. Convert to Base -2

Given an integer n, return a binary string representing its representation in base -2.
Note that the returned string should not have leading zeros unless the string is "0".

Example 1:
Input: n = 2
Output: "110"
Explantion: (-2)2 + (-2)1 = 2

Example 2:
Input: n = 3
Output: "111"
Explantion: (-2)2 + (-2)1 + (-2)0 = 3

*/

=========

class Solution {
    public String baseNeg2(int n) {
        if(n == 0)
            return "0";

        String s = "";
        while(n != 0) {
            int i = n%-2;
            n/=-2;
            if(i<0) {
                i += Math.abs(-2);
                n++;
            }
            s = i+s;
        }
        return s;
    }
}

// https://en.wikipedia.org/wiki/Negative_base#Calculation
