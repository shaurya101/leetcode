/*
520. Detect Capital

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Given a string word, return true if the usage of capitals in it is right.

Example 1:
Input: word = "USA"
Output: true

Example 2:
Input: word = "FlaG"
Output: false

*/

========


class Solution {
    public boolean detectCapitalUse(String word) {
        // if all letters uppercase
        if(word.toUpperCase().equals(word))
            return true;
        // if all letters lower case
        if(word.toLowerCase().equals(word))
            return true;

        // if 1st letter Uppercase and rest is Lowercase
        if(word.charAt(0) >= 65 && word.charAt(0)<=91) {
            if(word.substring(1).toLowerCase().equals(word.substring(1)))
                return true;
        }
        return false;
    }
}
