/*
387. First Unique Character in a String

Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

Example 1:

Input: s = "leetcode"
Output: 0

Example 2:
Input: s = "loveleetcode"
Output: 2

Example 3:
Input: s = "aabb"
Output: -1
*/
--------------

class Solution {
    public int firstUniqChar(String s) {
        HashSet<Character> set = new HashSet<>(); 
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(set.contains(c))
            {
                if( map.containsKey(c)) // we need to check this as in the third occurence case, the key would not be there even though it  is a repition. This is because in the second occurence, the value is removed.
                     map.remove(c);
            }
            else
            {
                set.add(c);
                map.put(c,i);
            }
        }
        
        for(int i=0; i<s.length(); i++){
            if(map.containsKey(s.charAt(i)))
                return map.get(s.charAt(i));
        }
        return -1;
    }
}

// Approach
// We use a hashmap and a hashset
// we will add the char values in the hashset, and we can check if any element is repeated using this.
// We will iterate the string and check if the value is in the hashset. If yes, then remove the value from hashMap but not the hashSet. If no, add the value in the hashSet and hashMap.
// After the string is traversed and hashMap and hashSet are filled; In a second for loop traverse the string from the beginging and check if the char is present in hashMap.
// If yes, we found our first non-repeating char.
// If no char found after complete traversal of string, return -1.

