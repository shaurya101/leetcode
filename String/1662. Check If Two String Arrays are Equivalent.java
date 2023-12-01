/*
1662. Check If Two String Arrays are Equivalent

Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
A string is represented by an array if the array elements concatenated in order forms the string.

Example 1:
Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.

Example 2:
Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
Output: false

*/

------------

// Approach 1
// T: O(N + M), where N is the total number of characters in word1 and M in word2
// S: O(N + M)

class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        for(int i=0; i<word1.length; i++)
            s1.append(word1[i]);

        for(int i=0; i<word2.length; i++)
            s2.append(word2[i]);

        return s1.toString().equals(s2.toString()); //Checking for equality in StringBuilder
    }
}
