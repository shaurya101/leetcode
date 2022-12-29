/*
2000. Reverse Prefix of Word

Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.

For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
Return the resulting string.

Example 1:
Input: word = "abcdefd", ch = "d"
Output: "dcbaefd"
Explanation: The first occurrence of "d" is at index 3. 
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".

*/

=========


class Solution {
    public String reversePrefix(String word, char ch) {
        int chIndex = -1;

        for(int i=0; i<word.length(); i++) {
            if(word.charAt(i) == ch) {
                chIndex = i;
                break;
            }
        }

        // if ch not found
        if(chIndex == -1)
            return word;

        return reverse(word, chIndex);
    }

    private String reverse(String word, int chIndex) {
        char[] word1 = word.toCharArray();

        int left = 0;
        while(left < chIndex) {
            swap(word1, left, chIndex);
            left++;
            chIndex--;
        }
        return new String(word1); // we CANNOT use toString()!
    }
    private void swap(char[] word1, int i, int j) {
        char temp = word1[i];
        word1[i] = word1[j];
        word1[j] = temp;
    }
}

// Approach
// Find first index of ch
// Reverse the required string
