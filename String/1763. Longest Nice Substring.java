/*
1763. Longest Nice Substring

A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.

Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.

Example 1:
Input: s = "YazaAay"
Output: "aAa"
Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
"aAa" is the longest nice substring.

Example 2:
Input: s = "Bb"
Output: "Bb"
Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.

*/

=============

// Approach - Divide and Conquer
// We follow a recursive approach.
// We make a set and store all the chars of string in it.
// Then we iterate the String from left; if charAt(i)'s both upper and lower case are present in the set, it means this could still be a nice substring and we continue to next char
// We continue incrementing i, till set has both upper and lower cases of charAt(i).
// Now, when the condition is not satisfied; then it means this whole string cannot be a nice substring as only one (upper or lower)case is present of this particular character. But still subtring from on either side of ith character can be nice. So we recursively call the function with the possible nice substrings
// We return the largest subtring back
// If all cases of chars are present in the set, then return entire string


class Solution {
    public String longestNiceSubstring(String s) {
        if(s.length() < 2)
            return "";

        HashSet<Character> set = new HashSet<>();

        // adding all chars of string in the set
        for(int i=0; i<s.length(); i++)
            set.add(s.charAt(i));

        // traversing the string
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            // if upper and lower cases of 'c' are present in set, then check for next char 
            if(set.contains(Character.toUpperCase(c)) && set.contains(Character.toLowerCase(c)))
                continue;
            // if c has only one case present, divide the string into two from i and check for the nice substrings for both parts
            String leftSubstring = longestNiceSubstring(s.substring(0, i));
            String rightSubstring = longestNiceSubstring(s.substring(i+1));
            // return greater size string
            return leftSubstring.length()>=rightSubstring.length()? leftSubstring :rightSubstring;
        }
        // if all chars have both cases present, return s
        return s;
    }
}
