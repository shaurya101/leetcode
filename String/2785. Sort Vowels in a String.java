/*
2785. Sort Vowels in a String

Given a 0-indexed string s, permute s to get a new string t such that:

All consonants remain in their original places. More formally, if there is an index i with 0 <= i < s.length such that s[i] is a 
consonant, then t[i] = s[i].
The vowels must be sorted in the nondecreasing order of their ASCII values. More formally, for pairs of indices i, j 
with 0 <= i < j < s.length such that s[i] and s[j] are vowels, then t[i] must not have a higher ASCII value than t[j].
Return the resulting string.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in lowercase or uppercase. Consonants comprise all letters that 
are not vowels.
 

Example 1:
Input: s = "lEetcOde"
Output: "lEOtcede"
Explanation: 'E', 'O', and 'e' are the vowels in s; 'l', 't', 'c', and 'd' are all consonants. The vowels are sorted according to
their ASCII values, and the consonants remain in the same places.

Example 2:
Input: s = "lYmpH"
Output: "lYmpH"
Explanation: There are no vowels in s (all characters in s are consonants), so we return "lYmpH".

*/

-------------

// Approach - T: O(N), S: O(N)
// Even if we use StringBuilder instead of char[] the space will be O(N)


class Solution {
    public String sortVowels(String s) {
        int[] freq = new int[118]; // ASCII of 'u' is 117; index will represent ASCII char

        char[] chars = s.toCharArray();

        // Count the frequency of each vowel in the input string
        for (char c : chars) {
            if (isVowel(c)) {
                freq[c - '0']++; // Increment the frequency count based on the ASCII value of the vowel
            }
        }

        int k = 0;
        // Iterate through the characters of the input string
        for (int i = 0; i < chars.length; i++) {
            if (isVowel(chars[i])) {
                while (freq[k] == 0)
                    k++;
                chars[i] = (char) (k + '0'); // Replace the vowel with the next available vowel based on frequency
                freq[k]--;
            }
        }

        return new String(chars); // Return the modified string with sorted vowels
    }

    // Helper method to check if a character is a vowel
    private boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ||
               c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}


-----------------

// Approach - T: O(NlogN), S: O(N)


class Solution {
    public String sortVowels(String s) {
        PriorityQueue<Character> vowelQueue = new PriorityQueue<>();

        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (isVowel(c)) {
                vowelQueue.offer(c);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (isVowel(chars[i])) {
                chars[i] = vowelQueue.poll();
            }
        }

        return new String(chars);
    }

    private boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ||
               c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
