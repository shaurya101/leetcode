/*

242. Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

*/

-------------

class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        
        for(int i=0; i<s.length(); i++) {
            if(map1.containsKey(s.charAt(i)))
                map1.put(s.charAt(i), map1.get(s.charAt(i))+1);
            else
                map1.put(s.charAt(i), 1);
        }
        for(int i=0; i<t.length(); i++) {
            if(map2.containsKey(t.charAt(i)))
                map2.put(t.charAt(i), map2.get(t.charAt(i))+1);
            else
                map2.put(t.charAt(i), 1);
        }
        
        
       /*
       One case is failing in Leetcode, if we use this to check if the two hashmaps are equal or not instead of using equals()
       for(char key: map1.keySet()) {
            if(!map2.containsKey(key))
                return false;
            if(map1.get(key) != map2.get(key))
                return false;
        }
        */
        
        if(map1.equals(map2)) {
            return true;
        }
        return false;
    }
}
