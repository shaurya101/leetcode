/*

541. Reverse String II

Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.

If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.

Example 1:

Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Example 2:

Input: s = "abcd", k = 2
Output: "bacd"

Constraints:

1 <= s.length <= 104
s consists of only lowercase English letters.
1 <= k <= 104

*/


-----------------------------------------------

class Solution {
    public String reverseStr(String s, int k) {
        char[] s1 = s.toCharArray(); // String to char[]
       // incrementation of i=2*k as reversal process begins from indexes like - 0, 2k, 4k, 6k, 8k and so on.
        for(int i=0; i<s1.length; i+=2*k){
            reverse(s1, i, Math.min(i+k-1, s1.length-1));
            // Math.min used incase less than k elements are left in array
        }
        return new String(s1);
    }
    private static void reverse(char[] s1, int i, int j){
        // i is the startIndex; j is endIndex
        while(i<j){
            char temp = s1[j];
            s1[j--] = s1[i];
            s1[i++] = temp;
        }
    }
}

// we can use private class as we only want to call reverse() from within the same class.

