/*
171. Excel Sheet Column Number

Given a string columnTitle that represents the column title as appears in an Excel sheet, return its corresponding column number.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
*/


----------------------------------

class Solution {
    public int titleToNumber(String columnTitle) {
        int i = 0; //iterator
        int sum = 0; // column number
        while(i<columnTitle.length()){
            int x = columnTitle.charAt(i) -'A' + 1; // for A x=1, for B x=2 and so on.
            sum = (sum*26) + x;
            i++;
        }
        return sum;
    }
}

// We iterate string from left to right.
// We extract the ith char and using ASCII to int as following:
// A = 1
// B = 2
// ... Z = 26
// We add this int x to sum. Before adding x we multiply inintial sum by 26. Consider the follwing - 
// normal number expansion
// 1250 = 1*10*10*10 + 2*10*10 + 5*10 + 0*10
// In this probelm, as string go from 1 to 26 (a to z)
// AAZ = A*26*26 + A*26 + Z.
// ZZY = Z*26 + Z*26 + Y

