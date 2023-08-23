/*
767. Reorganize String

Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""

*/

-----------------


// Approach - // T: O(nlog(26)), S: O(n), where n is the length of the input string s.


class Solution {
    public String reorganizeString(String s) {
        //make a char frequency array
        int[] freq = new int[26];
        for(char c : s.toCharArray())
            freq[c-'a']++;

        // storing characters and their frequency in maxHeap
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b)->b[1]-a[1]);
        for(int i=0; i<26; i++) {
            if(freq[i]>0)
                maxHeap.offer(new int[] {i, freq[i]});
        }

        // to store result
        StringBuilder result = new StringBuilder();

        // to track last character added to result
        int[] prev = {-1, 0};
        while(!maxHeap.isEmpty()) {
            // Append the current character to the result
            int[] curr = maxHeap.poll();
            result.append((char)(curr[0]+'a'));
            curr[1]--;

            // If previous character's frequency is greater than 0, re-add it to maxHeap
            if(prev[1] > 0)
                maxHeap.offer(prev);
            prev = curr;
        }
        // if characters will alternate till either maxHeap is empty due to 2 possible reasons - 
        // all character used
        // all char used but one still remains with some frequency left in which case result length will be different than s.length (note in loop, we dont add curr back into maxHeap, we add prev if the frequency is > 0)
        return result.length() == s.length()? result.toString() : "";
    }
}
