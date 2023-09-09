/*
126. Word Ladder II

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord,
or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

*/


--------------

CP approach - clears all cases


class Solution {
    String b; // Stores the 'beginWord'

    // Create a hashmap of type word->level to get the idea
    // on which level the word comes after the transformations.
    HashMap<String, Integer> mpp;

    // A list for storing the final answer.
    List<List<String>> ans;

    private void dfs(String word, List<String> seq) {
        // Function for implementing backtracking using the created map
        // in reverse order to find the transformation sequence in less time.

        // Base condition:
        // If 'word' equals 'beginWord', we've found one of the sequences.
        // Simply reverse the sequence and add it to 'ans'.
        if (word.equals(b)) {
            // Since Java works with references, create a duplicate and store the reverse of it.
            List<String> dup = new ArrayList<>(seq);
            Collections.reverse(dup);
            ans.add(dup);
            return;
        }
        int steps = mpp.get(word);
        int sz = word.length();

        // Replace each character of the 'word' with letters from 'a' to 'z'
        // and check whether the transformed word is present in the map
        // and at the previous level or not.
        for (int i = 0; i < sz; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                char replacedCharArray[] = word.toCharArray();
                replacedCharArray[i] = ch;
                String replacedWord = new String(replacedCharArray);
                if (mpp.containsKey(replacedWord) &&
                    mpp.get(replacedWord) + 1 == steps) {

                    seq.add(replacedWord);
                    dfs(replacedWord, seq);

                    // Pop the current word from the back of the sequence
                    // to traverse other possibilities.
                    seq.remove(seq.size() - 1);
                }
            }
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        // Push all values of 'wordList' into a set
        // to make deletion from it easier and with less time complexity.
        Set<String> st = new HashSet<>();
        int len = wordList.size();
        for (int i = 0; i < len; i++) {
            st.add(wordList.get(i));
        }

        // Perform BFS traversal and push the string in the queue
        // as soon as they're found in 'wordList'.
        Queue<String> q = new LinkedList<>();
        b = beginWord;
        q.add(beginWord);
        mpp = new HashMap<>();

        // 'beginWord' initialized with level 1.
        mpp.put(beginWord, 1);
        int sizee = beginWord.length();
        st.remove(beginWord);
        while (!q.isEmpty()) {
            String word = q.peek();
            int steps = mpp.get(word);
            q.remove();

            // Break out if 'word' matches 'endWord'.
            if (word.equals(endWord)) break;

            // Replace each character of 'word' with letters from 'a' to 'z'
            // and check whether the transformed word is present in 'wordList',
            // if yes, then push it to the queue along with its level.
            for (int i = 0; i < sizee; i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char replacedCharArray[] = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);
                    if (st.contains(replacedWord) == true) {
                        q.add(replacedWord);
                        st.remove(replacedWord);

                        // Push the word along with its level in the map data structure.
                        mpp.put(replacedWord, steps + 1);
                    }
                }
            }
        }
        ans = new ArrayList<>();

        // If we reach 'endWord', we stop and move to step-2
        // which is to perform reverse DFS traversal.
        if (mpp.containsKey(endWord) == true) {
            List<String> seq = new ArrayList<>();
            seq.add(endWord);
            dfs(endWord, seq);
        }
        return ans;
    }
}


-------------

// Approach 2 - Correct and intuituve but gives Time limit exceeded for few cases


import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        
        if (!wordSet.contains(endWord)) {
            return new ArrayList<>(); // End word is not in the word list, return an empty list
        }

        List<List<String>> ans = new ArrayList<>();
        Queue<List<String>> q1 = new LinkedList<>();
        q1.offer(new ArrayList<>(Arrays.asList(beginWord))); // Initialize with a list containing the beginWord
        Set<String> usedOnLevel = new HashSet<>();
        usedOnLevel.add(beginWord);

        while (!q1.isEmpty()) {
            int size = q1.size();
            usedOnLevel.forEach(wordSet::remove); // Remove used words from wordSet

            for (int k = 0; k < size; k++) {
                List<String> currPath = q1.poll();
                String lastWord = currPath.get(currPath.size() - 1);
                char[] currWord = lastWord.toCharArray();

                for (int i = 0; i < currWord.length; i++) {
                    char originalChar = currWord[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue; // Skip the same character
                        }

                        currWord[i] = c;
                        String newWord = String.valueOf(currWord);

                        if (wordSet.contains(newWord)) {
                            List<String> newPath = new ArrayList<>(currPath);
                            newPath.add(newWord);
                            q1.offer(newPath);
                            usedOnLevel.add(newWord);

                            if (newWord.equals(endWord)) {
                                ans.add(newPath);
                            }
                        }
                    }

                    currWord[i] = originalChar; // Restore the original character
                }
            }

            if (!ans.isEmpty()) {
                return ans; // If a ladder is found, return it
            }
        }

        return ans; // If no ladder is found, return an empty list
    }
}
