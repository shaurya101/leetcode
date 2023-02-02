/*
953. Verifying an Alien Dictionary

In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.

Example 1:
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

*/

----------------

Approach 1

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        // Storing the letters with their serial number according to alien dictionary
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<26; i++) {
            map.put(order.charAt(i), i);
        }
        
        // compare ith with (i+1)th word
        for(int i=0; i<words.length-1; i++) {
            System.out.println(i);
            int j = 0;
            // we traverse till we find a different letter on the words
            while(j<words[i].length() && j<words[i+1].length() && words[i].charAt(j) == words[i+1].charAt(j)) 
                j++;
            // if both words are completely traversed and all letters were same or if all letters were same till end of first word traversal but second word contains more letters than first word and not completely traversed yet, then these two words are in correct order so we continue and check for further words
            if(j==words[i].length() && j<=words[i+1].length())
                continue;
            // if all letters were same till end of second word traversal but first word still has more letters, return false
            if(j<words[i].length() && j==words[i+1].length())
                return false;
            // Here it means all, we have found different letter and neither word is completely traversed yet
            if(map.get(words[i].charAt(j)) - map.get(words[i+1].charAt(j)) > 0)
                return false;
        }
        return true;
    }
}
