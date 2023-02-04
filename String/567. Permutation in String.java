/*
567. Permutation in String

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
In other words, return true if one of s1's permutations is the substring of s2.

Example 1:
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input: s1 = "ab", s2 = "eidboaoo"
Output: false

*/

---------------

// Approach - Same as below but cleaner code

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] map = new int[26];
        for(char c : s1.toCharArray()) map[c - 'a']++;
        int j = 0, i = 0;
        int count_chars = s1.length();
        while(j < s2.length()){
            if(map[s2.charAt(j++) - 'a']-- > 0)
                count_chars--;
            if(count_chars == 0) return true;
            if(j - i == s1.length() && map[s2.charAt(i++) - 'a']++ >= 0)
                count_chars++;
        }
        return false;
    }
}


---------------

// Approach
// We will have two pointers and a charLeft variable to track if all chars of s1 are placed in the subarray
// We will make a array of frequency of characters in string s1
// We will start to traverse the string s2 using variable right and reduce the current char frequency by one
// If our char was in s2, we reduce the charsLeft by one as well
// Now if charsLeft has become zero or all characters are placed, return true
// If not, check the window size and if our char at left index is in s1.
// Increment left if the former condition is true, and if both true then increment charsLeft by one as well as the char will now become unplaced


class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] map = new int[26];
        for(int i=0; i<s1.length(); i++)
            map[s1.charAt(i)-'a']++;

        int left = 0, right = 0;
        int windowSize = s1.length();
        int charsLeft = s1.length(); // chars that have to be placed in a subarray

        while(right < s2.length()) {
            if(map[s2.charAt(right) - 'a']-- > 0) // we reduce the frequency of char even if condition is not true, as the decrement is done in the if condition statement itself
                charsLeft--; // leftover chars to be placed in subarray also reduces by one
            
            right++; // move right ahead by one
            if(charsLeft == 0)
                return true;
            if(right-left == windowSize && map[s2.charAt(left++) - 'a']++ >= 0) { // the left pointer will move ahead only if first condition is true, irrespective of the second condition is true or not
                charsLeft++; // increment charsLeft if we are moving left ahead and our char was placed in the subarray but will become unplaced now
            }
        }
        return false;
    }
}
