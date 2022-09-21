/*

290. Word Pattern

Given a pattern and a string s, find if s follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.


Example 1:
Input: pattern = "abba", s = "dog cat cat dog"
Output: true

Example 2:
Input: pattern = "abba", s = "dog cat cat fish"
Output: false

Example 3:
Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false

Constraints:
- 1 <= pattern.length <= 300
- pattern contains only lower-case English letters.
- 1 <= s.length <= 3000
- s contains only lowercase English letters and spaces ' '.
- s does not contain any leading or trailing spaces.
- All the words in s are separated by a single space.

*/

-----------------

class Solution {
    public boolean wordPattern(String pattern, String s) {
        // storing words of s in str[]
        String[] str = s.split(" ");
        // if length of pattern is not equal to str, return false
        if(pattern.length() != str.length)
            return false;
        
        HashMap<Character, String> map = new HashMap<>();
        
        for(int i=0; i<pattern.length(); i++) {
            // if map contains char key
            if(map.containsKey(pattern.charAt(i))) {
                // if map contains the key and the value of corresponding key is not equal to str[i]
                // use equals() method to compare strings
                if(!map.get(pattern.charAt(i)).equals(str[i]))
                    return false;
            }
            // if key is not present the correspoding str[i] should be present in the value of map
            else if(map.containsValue(str[i]))
                return false;
            
            map.put(pattern.charAt(i), str[i]);
            }
        return true;
    }
}
