/*
2202. Maximize the Topmost Element After K Moves

You are given a 0-indexed integer array nums representing the contents of a pile, where nums[0] is the topmost element of the pile.

In one move, you can perform either of the following:

If the pile is not empty, remove the topmost element of the pile.
If there are one or more removed elements, add any one of them back onto the pile. This element becomes the new topmost element.
You are also given an integer k, which denotes the total number of moves to be made.

Return the maximum value of the topmost element of the pile possible after exactly k moves. In case it is not possible to obtain a non-empty pile after k moves, return -1. 

Example 1:
Input: nums = [5,2,2,4,0,6], k = 4
Output: 5
Explanation:
One of the ways we can end with 5 at the top of the pile after 4 moves is as follows:
- Step 1: Remove the topmost element = 5. The pile becomes [2,2,4,0,6].
- Step 2: Remove the topmost element = 2. The pile becomes [2,4,0,6].
- Step 3: Remove the topmost element = 2. The pile becomes [4,0,6].
- Step 4: Add 5 back onto the pile. The pile becomes [5,4,0,6].
Note that this is not the only way to end with 5 at the top of the pile. It can be shown that 5 is the largest answer possible after 4 moves.

Example 2:
Input: nums = [2], k = 1
Output: -1
Explanation: 
In the first move, our only option is to pop the topmost element of the pile.
Since it is not possible to obtain a non-empty pile after one move, we return -1.

*/

============

// Approach
// If nums.length == 1, and k is odd then as we cannot obtain a non-empty pile after removing top element, return -1.
// If k<nums.length, the max element could be either in the first k elements or (k+1)th element(if we remove k elements in pile). If it is in the first k elements 
// If k>nums.length, then we have to return the max element of the array, we can always pop and then push in some manner where we push the max element last.


class Solution {
    public int maximumTop(int[] nums, int k) {
        if(nums.length == 1)
            return k%2 == 1? -1:nums[0];
        int max = Integer.MIN_VALUE;
        int i = 0;
        // we only run till index i = k-1 or kth index, since if k steps are there we cannot remove the index (k-1)th element as we will not be able to put it back
        while(i<nums.length && i<k-1) {
            max = Math.max(max, nums[i]);
            i++;
        }
        // if k<nums.length && (k+1)th element(index k) > kth element(index k-1)
        if(k<nums.length && nums[k]> max)
            return nums[k];
        // if k>nums.length, we return max element of array
        return max;
    }
}
