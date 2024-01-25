/*
1143. Longest Common Subsequence

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing 
the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
*/

=============

// Approach 1 - T: O(n*m), S: O(n*m); n=text1.length, m=text2.length
// DP tabulation


class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        // base case dp=0, if either ind1==text1.length || ind2==text2.length
        /*
        for(int ind1=0; ind1<=text1.length(); ind1++)
            dp[ind1][text2.length()] = 0;
        for(int ind2=0; ind2<=text2.length(); ind2++)
            dp[text1.length()][ind2] = 0;
        */

        for(int ind1 = text1.length()-1; ind1>=0; ind1--) {
            for(int ind2 = text2.length()-1; ind2>=0; ind2--) {
                // match
                if(text1.charAt(ind1) == text2.charAt(ind2))
                    dp[ind1][ind2] = 1+dp[ind1+1][ind2+1];
                else // not match
                    dp[ind1][ind2] = Math.max(dp[ind1+1][ind2], dp[ind1][ind2+1]);
            }
        }

        return dp[0][0];
    }
}


=============

// Approach 1 - T: O(n*m), S: O(n*m) + O(n+m)
// Recursion with memoization

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int[] arr : dp)
            Arrays.fill(arr, -1);

        return f(0, 0, text1, text2, dp);
    }
    private int f(int ind1, int ind2, String text1, String text2, int[][] dp) {
        if(ind1 == text1.length() || ind2 == text2.length())
            return 0;
        if(dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        int len = 0;
        // match
        if(text1.charAt(ind1) == text2.charAt(ind2))
            len = 1+f(ind1+1, ind2+1, text1, text2, dp);
        else // not match
            len = Math.max(f(ind1+1, ind2, text1, text2, dp), f(ind1, ind2+1, text1, text2, dp));

        return dp[ind1][ind2] = len;
    }
}
