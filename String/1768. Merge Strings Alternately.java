/*
1768. Merge Strings Alternately

You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

Example 1:
Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r

Example 2:
Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s

*/


------------------------

// Approach 1 : T: O(N), S: O(N)


class Solution {
    public String mergeAlternately(String word1, String word2) {
        int i=0, j=0, k=0;
        char[] wordArray= new char[word1.length()+word2.length()];
        while(i<word1.length() && j<word2.length()) {
            wordArray[k++] = word1.charAt(i++);
            wordArray[k++] = word2.charAt(j++);
        }
        while(i<word1.length())
            wordArray[k++] = word1.charAt(i++);

        while(j<word2.length())
            wordArray[k++] = word2.charAt(j++);

        return new String(wordArray);
    }
}


----------------------

// Approach 2 - T: O(N), S: O(N); 
// Note StrinBuilder also use O(N) space

class Solution {
    public String mergeAlternately(String word1, String word2) {
        int n = Math.min(word1.length(), word2.length());
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }
        
        if (word1.length() > n) {
            sb.append(word1.substring(n));
        }
        
        if (word2.length() > n) {
            sb.append(word2.substring(n));
        }
        
        return sb.toString();
    }
}
