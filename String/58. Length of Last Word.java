/*
58. Length of Last Word

Given a string s consisting of words and spaces, return the length of the last word in the string.
A word is a maximal substring consisting of non-space characters only.

Example 1:
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.

Example 2:
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.

*/

-----------

class Solution {
    public int lengthOfLastWord(String s) {
        int index1=0;
        int index2=0;
        
        int ptr = s.length()-1;
        while(s.charAt(ptr) == ' ') // traversing to the first letter from back and skipping the spaces
            ptr--;
        index1 = ptr; // ptr stops at last letter of last word
        
        while(ptr>=0 && s.charAt(ptr) != ' ')
            ptr--;
        index2 = ptr; // ptr stops at space before last word
        
        return index1-index2;
    }
}
