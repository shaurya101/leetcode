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
    
// Approach 1
  
class Solution {
    public boolean isAnagram(String s, String t) {
        // if length is unequal then return false
        if(s.length() != t.length())
            return false;
        
        // we will store the chars of s as keys, and their frequency of occurrence as values in the map
        HashMap<Character, Integer> map = new HashMap<>();
        
        // we will increment the key's value by 1, if every time we encounter the key
        for(int i=0; i<s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        
        // we will decrement a key's value by 1, every time we encounter the key. If strings are anagram, the value of all keys should become 0 by the end of this loop
        for(int i=0; i<t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0)-1);
        }
        
        // checking if all values of hashmap are 0
        for(int value: map.values()) {
            if(value != 0)
                return false;
        }
        
        return true;
    }
} 
    
    
    
-------------

// Approach 2
    
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
