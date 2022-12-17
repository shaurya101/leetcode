/*
151. Reverse Words in a String

Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

Example 1:
Input: s = "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

*/

------------

class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        Stack<String> s1 = new Stack<>();
        String[] str = s.split(" ");
        
        String ans = "";
        for(int i=str.length-1; i>=0; i--) {
            // System.out.println("." + str[i] + ".");
            if(!str[i].equals("")) // split has some empty strings if there are more than one space together
                ans = ans + str[i] + " ";
        }
        return ans.trim(); // trimming the last extra " " at the end
    }
}
