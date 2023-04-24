/*
1046. Last Stone Weight

You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0. 

Example 1:
Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.

*/


----------------

// Approach : T: O(NlogN), S: O(N)
// Time complexity  - The time complexity of the given code is O(n log n) where n is the length of the stones array.
// This is because adding an element to the priority queue takes O(log n) time, and we add n elements to the queue. 
// Extracting the two largest elements and performing subtraction on them also takes O(log n) time, and we do this n-1 times. (see java function it is log N)

 

class Solution {
    public int lastStoneWeight(int[] stones) {
        // we take a maxHeap. Default java Priority Queue is minHeap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // add from array to maxHeap
        for(int stone : stones)
            maxHeap.add(stone);

        // while there are more than 1 element, we can do some calculation. Else exit loop
        while(maxHeap.size() > 1) {
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();
            if(stone1 != stone2)
                maxHeap.add(stone1-stone2);
        }

        return maxHeap.isEmpty() ? 0  : maxHeap.poll();
    }
}
