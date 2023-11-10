/*
1743. Restore the Array From Adjacent Pairs

There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of 
adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and
vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]]
or [nums[i+1], nums[i]]. The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.

 
Example 1:
Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.

Example 2:
Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.

Example 3:
Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]

*/


-------------


// Approach 1 - T: O(n⋅m) = O(n*2) = O(n), S: O(n+m) = O(n+2) = O(n), where m=2 (number of neighbors), n=number of elements in array

class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        // Step 1: Build adjacency list
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            // Add pair[1] to neighbors of pair[0], and vice versa
            adjList.computeIfAbsent(pair[0], key -> new ArrayList<>()).add(pair[1]); // Note syntax
            adjList.computeIfAbsent(pair[1], key -> new ArrayList<>()).add(pair[0]);
        }

        int n = adjList.size();
        int[] result = new int[n];

        // Step 2: Find the starting element
        int start = 0;
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) { // Note syntax
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }

        int i = 0;

        // Step 3: Restore the array
        while (adjList.get(start).size() != 0) {
            result[i] = start;
            int nextElement = adjList.get(start).get(0);
            
            // Remove the connection in both directions
            adjList.get(result[i]).remove(Integer.valueOf(nextElement)); // Note syntax
            adjList.get(nextElement).remove(Integer.valueOf(result[i]));

            // Move to the next element
            start = nextElement;
            i++;
        }

        // Add the last element to the result array
        result[i] = start;

        // Step 4: Return the result
        return result;
    }
}
