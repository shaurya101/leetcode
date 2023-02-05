/*
438. Find All Anagrams in a String

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

*/

------------

// Approach (Similar to leeetcode 567-Counting permutations in string)
// We use a frequency counter array to track letters of String p
// We have a right and left pointer, and a charLeftToCheck counter which tracks how many more lettersn from String p need to be there in String s so all letters are utilised and an anagram is formed
// Notice how we use incrementor(++) and decrement operator in if statements. Keep in mind the increment/decrement will happen after the if statement and inside that if statement, comparison happens according to old values only
// We use counter charLeftToCheck so that we know all characters have been used. That way we do not need to traverse the map array and check for each letter if frequency is zero.
// We shift the right forward everytime, but the comparison happens for the pre-updated right pointer only!
// We add to the list pointer left, if all chars are placed
// We move left forward if window size is more than number of characters in p. And increment charLeftToCheck if left is shifted


class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new LinkedList<>();
        int[] map = new int[26];
        // creating frequency array for String p
        for(int i=0; i<p.length(); i++)
            map[p.charAt(i) - 'a']++;

        int right = 0, left = 0;
        int charLeftToCheck = p.length();
        while(right < s.length()) {
            if(map[s.charAt(right++) - 'a']-- > 0)
                charLeftToCheck--;
            if(charLeftToCheck == 0)
                ans.add(left);
            if(right - left >= p.length() && map[s.charAt(left++)-'a']++ >= 0)
                charLeftToCheck++;
        }
        return ans;
    }
}
