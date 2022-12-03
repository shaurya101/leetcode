/*
451. Sort Characters By Frequency

Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
Return the sorted string. If there are multiple answers, return any of them.

Example 1:
Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.

*/

-------------


class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        // put chars of String in map
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // now we use a MaxHeap in PriorityQueue
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a,b) -> map.get(b)-map.get(a));
        maxHeap.addAll(map.keySet()); // adding map in queue
        // Note that one char will be added only once in priority queue. It won't be added the number of frrquency times
        
        StringBuilder result = new StringBuilder();
        while(!maxHeap.isEmpty()) {
            char current = maxHeap.remove();
            for(int i=0; i<map.get(current); i++)
                result.append(current);
        }
        return result.toString();
    }
}
