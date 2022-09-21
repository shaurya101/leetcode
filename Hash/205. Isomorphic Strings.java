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
  
// Approach 1 - using 1 hashmap only
// leetcode shows time and space as 43ms and 43.4mb
// takes more time and almost same space then second approach. Why? we have used two hashmaps in approach two
  
class Solution {
    public boolean isIsomorphic(String s, String t) {
        // if length is unequal strings cannot be isophormic
        if(s.length() != t.length())
            return false;
        
        HashMap<Character, Character> map = new HashMap<>();
        
        for(int i=0; i<s.length(); i++) {
            // if map contains s.charAt(i) in key
            if(map.containsKey(s.charAt(i))) {
                // if it contains s.charAt(i) key, then its corresponding value in hashmap should be equal to t.charAt(i)
                if(map.get(s.charAt(i)) != t.charAt(i))
                    return false;
            }
            // if s.charAt(i) key is not there, t.charAt(i) should not be present in any of the 'values' of the map  
            else if(map.containsValue(t.charAt(i)))
                return false;
            // put key-value pair if above two conditions are not met.
            map.put(s.charAt(i), t.charAt(i));
        }
        return true;
    }
}
  
  
-----------------
  
  // Using two hashmaps.
  // One hashmap has key as char of 's', and values as char of 't'
  // Other hasmap has key as char of 't', and values as char of 's'
  // leetcode shows time and space as 26ms and 42.9ms. Why is it faster than first approach? and why it uses same space even though first way uses only one hashmap?

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
