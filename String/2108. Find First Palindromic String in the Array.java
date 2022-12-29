/*
2108. Find First Palindromic String in the Array

Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".
A string is palindromic if it reads the same forward and backward.

Example 1:
Input: words = ["abc","car","ada","racecar","cool"]
Output: "ada"
Explanation: The first string that is palindromic is "ada".
Note that "racecar" is also palindromic, but it is not the first.

*/

===============

class Solution {
    public String firstPalindrome(String[] words) {
        for(int i=0; i<words.length; i++) {
            if(isPalindrome(words[i]))
                return words[i];
        }
        return "";
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;

        while(left<right) {
            if(s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
}

// Approach
// traverse from beginning of words[] to check for palindrom strings
// return first palindrome string
