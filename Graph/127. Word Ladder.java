/*
127. Word Ladder

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence
from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

*/


-----------------

// Approach - : T- O(N * M), S- O(N * M);  N is the number of words, and M is the average word length.


import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Create a set to efficiently check word existence in the word list
        Set<String> wordSet = new HashSet<>(wordList);

        // Check if the end word is not in the word list; if not, return 0
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // Create a queue for BFS and add the starting word
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int ladderLength = 1; // Initialize ladder length to 1

        while (!queue.isEmpty()) {
            int size = queue.size(); // Get the current level's size

            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll(); // Dequeue the current word

                // Generate all possible one-letter variations of the word
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j]; // Store the original character

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue; // Skip the same character
                        }

                        wordChars[j] = c; // Replace the character
                        String newWord = String.valueOf(wordChars); // Generate the new word

                        // If the new word matches the end word, return the ladder length
                        if (newWord.equals(endWord)) {
                            return ladderLength + 1; // Add 1 to include both start and end words
                        }

                        // If the new word exists in the word set, enqueue it and remove it from the set
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }

                    wordChars[j] = originalChar; // Restore the original character
                }
            }

            ladderLength++; // Increment the ladder length for the next level
        }

        return 0; // No transformation sequence exists
    }
}
