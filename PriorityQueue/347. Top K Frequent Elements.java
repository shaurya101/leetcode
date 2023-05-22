/*
347. Top K Frequent Elements

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

*/

---------
  
// Approach - T: O(nlogk), where n is the number of elements in the input array nums and k is the given value for the top k frequent elements.
// The initial step of counting the frequency of each element in nums takes O(n) time. Then, inserting elements into the min heap takes O(log k) time for each element, which results in O(n log k) time complexity overall.
// S: O(n)
// The space complexity is O(n) since we need to store the frequency of each element in the frequencyMap, and in the worst case, all elements in nums could be unique.

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // tracking frequency of all unique numbers
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(int num : nums)
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);

        // minHeap of entries of frequencyMap with lowest element-freq pair in top to be removed first
        // entrySet() is used to get both key and value of a map
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());
        // storing element and their frequency in a minHeap
        for(Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            // maintaing a heap size of k
            if(minHeap.size() > k)
                minHeap.poll();
        }

        // storing elements in result from back as lowest frequency element will be popped first
        int[] result = new int[k];
        for(int i=k-1; i>=0; i--) {
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }
}
