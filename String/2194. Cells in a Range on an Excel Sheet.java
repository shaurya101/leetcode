2194. Cells in a Range on an Excel Sheet

A cell (r, c) of an excel sheet is represented as a string "<col><row>" where:

<col> denotes the column number c of the cell. It is represented by alphabetical letters.
For example, the 1st column is denoted by 'A', the 2nd by 'B', the 3rd by 'C', and so on.
<row> is the row number r of the cell. The rth row is represented by the integer r.
You are given a string s in the format "<col1><row1>:<col2><row2>", where <col1> represents the column c1, <row1> represents the row r1, <col2> represents the column c2, and <row2> represents the row r2, such that r1 <= r2 and c1 <= c2.

Return the list of cells (x, y) such that r1 <= x <= r2 and c1 <= y <= c2. The cells should be represented as strings in the format mentioned above and be sorted in non-decreasing order first by columns and then by rows.  

Example 1:

Input: s = "K1:L2"
Output: ["K1","K2","L1","L2"]

Constraints:

s.length == 5
'A' <= s[0] <= s[3] <= 'Z'
'1' <= s[1] <= s[4] <= '9'
s consists of uppercase English letters, digits and ':'.

-----------------------------

class Solution {
    public List<String> cellsInRange(String s) {
        LinkedList<String> list = new LinkedList<>();
        // extract c1, r1, c2, r2
        char c1=s.charAt(0);
        char r1 = s.charAt(1);
        char c2 = s.charAt(3);
        char r2 = s.charAt(4);
        
        for(char c = c1; c<=c2; c++)
            for(char r=r1; r<=r2; r++){
                list.add(new String(new char[]{c,r})); // new String(char[]);
            }
        return list;
    }
}

