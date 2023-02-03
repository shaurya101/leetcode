/*
6. Zigzag Conversion

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);
 
Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

*/

------------

class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1)
            return s;
            
        StringBuilder[] sbArr = new StringBuilder[numRows];
        for(int i=0; i<numRows; i++)
            sbArr[i] = new StringBuilder();
        
        boolean flag = true; // to alternate going up and down in zigzag manner
        int currRow = 0; // t track current row, which will be from 0 to (numRows-1)
        // we traverse the string s from beginning and store the character in respective StringBuilder array accprding to its currRow
        for(int i=0; i<s.length(); i++) {
            if(flag == true) { // if flag true we go down
                sbArr[currRow++].append(s.charAt(i));
                if(currRow == numRows) {
                    flag = false;
                    currRow--;
                }
            }
            else { // if flag false we go up
                sbArr[--currRow].append(s.charAt(i));
                if(currRow == 0) {
                    flag = true;
                    currRow++;
                }
            }
        }

        // Finally append all StringBuilder into one, and return as String
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<numRows; i++)
            sb.append(sbArr[i]);
        return sb.toString();
    }
}
