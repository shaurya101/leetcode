/*

205. Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself. 

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true

*/

-----------------
  
  // Using two hasmaps.
  // One hashmap has key as char of 's', and values as char of 't'
  // Other hasmap has key as char of 't', and values as char of 's'

class Solution {
    public boolean isIsomorphic(String s, String t) {
        // if length are unequal, it cant be isomorphic
        if(s.length() != t.length())
            return false;
        
        HashMap<Character, Character> map = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            if(map.containsKey(s.charAt(i))) {
                if(t.charAt(i) != map.get(s.charAt(i)))
                    return false;
                continue;
            }
            // add key value (<s.charAt(i), t.charAt(i)) in map if it doesnt exist
            // note that we dont compare s.charAt(i) and t.charAt(i).
            // we compare t.charAt(i) with the value of key-value pair of s.charAt(i) if the key(that is s.charAt(i)) already exist in map 
            map.put(s.charAt(i), t.charAt(i));
        }
        
        HashMap<Character, Character> map1 = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            if(map1.containsKey(t.charAt(i))) {
                if(s.charAt(i) != map1.get(t.charAt(i)))
                    return false;
                continue;
            }
            // add key value (<s.charAt(i), t.charAt(i)) in map if it doesnt exist
            // note that we dont compare s.charAt(i) and t.charAt(i).
            // we compare t.charAt(i) with the value of key-value pair of s.charAt(i) if the key(that is s.charAt(i)) already exist in map 
            map1.put(t.charAt(i), s.charAt(i));
        }
        
        return true;
    }
}

// length should be equal
// In a hashtable, we will put the char of first word as key and corresponding char of second word as value.
// If there is a char that appears twice, its value in its key-value pair in the hashtable and the corresponding char in second word should be equal.
// check both ways, ie chars of 's' as keys and chars of 't' as keys
