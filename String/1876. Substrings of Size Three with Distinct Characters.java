/*
1876. Substrings of Size Three with Distinct Characters

A string is good if there are no repeated characters.
Given a string s, return the number of good substrings of length three in s.
Note that if there are multiple occurrences of the same substring, every occurrence should be counted.

A substring is a contiguous sequence of characters in a string.

Example 1:
Input: s = "xyzzaz"
Output: 1
Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz". 
The only good substring of length 3 is "xyz".

Example 2:
Input: s = "aababcabc"
Output: 4
Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
The good substrings are "abc", "bca", "cab", and "abc".

*/

===========

// Approach 1 - general approach. Not for specific window of size 3
// We use a frequency counter to keep track of occurence of a char
// we have a variable 'duplicate' which tracks if we have a duplicate value in the substring. It stores the number of duplicates in the substring at a time.
// We then slide the window forward and decrease the frequency of (i-2)th char. We decrease the duplicate count, if now the decremented frequency equals to one.

class Solution {
    public int countGoodSubstrings(String s) {
        char[] chars = new char[26]; // a frequency counter for a char
        int count_good = 0; // count of good strings
        int duplicate = 0; // number of duplicates

        for(int i=0; i<s.length(); i++) {
            // increasing the frequency of ith element by 1. If it becomes 2, we increment the duplicate count by 1
            if(++chars[s.charAt(i)-'a'] == 2)
                duplicate++;
            // if substring size != 3
            if(i<2)
                continue;
            // if we have a substring size of 3 now, and if duplicate are zero, we have a good string
            if(duplicate == 0)
                count_good++;
            // decreasing the frequency of (i-2)th char, if it becomes 1 now, it means it was 2 earlier, hence we decrease the duplicate by 1
            if(--chars[s.charAt(i-2)-'a'] == 1)
                duplicate--;
        }
        return count_good;
    }
}

==========

// Approach 2 - specific to window of size 3
// If no repeated character is there set.size() = substring length
  
class Solution {
    public int countGoodSubstrings(String s) {
        int count = 0;

        for(int i=0; i<s.length()-2; i++) {
            HashSet<Character> set = new HashSet<>();
            for(int j=0; j<3; j++) {
                set.add(s.charAt(i));
                set.add(s.charAt(i+1));
                set.add(s.charAt(i+2));
            }
            if(set.size() == 3)
                count++;
        }
        return count;
    }
}


