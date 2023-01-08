/*
1358. Number of Substrings Containing All Three Characters

Given a string s consisting only of characters a, b and c.
Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 

Example 1:
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

Example 2:
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

*/

=============

// Approach
// We start traversing from 0th index.
// If all three chars present, then we add the count of all possible substrings starting from left. Count+=lastIndex-left+1
// Then move left ahead and delete char from hashmap and find the substring where all three chars present. Add all possible count from the new left index.


class Solution {
    public int numberOfSubstrings(String s) {
        int left = 0;
        int right = 0;
        int count = 0; // count of substrings
        HashMap<Character, Integer> map = new HashMap<>(); // character with their count

        while(right<s.length()) {
            char x = s.charAt(right);
            map.put(x, map.getOrDefault(x,0)+1);

            // if all three characters are present
            while(map.getOrDefault('a', 0)>0 && map.getOrDefault('b', 0)>0 && map.getOrDefault('c', 0)>0) {
                count += s.length()-right; // add all counts for that subtring starting from left. Example string - "abcabc". Let right be at index 3. This is one string with left= index 0, that has all three characters present. In addition to this, any string from left = 0 to right = s.length()-1 index will have all three characters. And the count would be lastIndex-left+1
                // After adding to count, we will move left ahead by one and remove that char from hash map as well
                map.put(s.charAt(left), map.getOrDefault(s.charAt(left), 0)-1);
                left++;
            }
            right++;
        }
        return count;
    }
}
