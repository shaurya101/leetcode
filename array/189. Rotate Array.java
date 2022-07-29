/*
189. Rotate Array

Given an array, rotate the array to the right by k steps, where k is non-negative. 

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
*/

--------------

class Solution {
    public void rotate(int[] nums, int k) {
        if(k <= 0)
            return;
        
        k = k%nums.length; //IMP - if k>size of array, nums.length-k will be invalid; We take mod of nums.length to remove any complete round rotations as they do not change the array
        Queue<Integer> q1 = new LinkedList<>();

        for(int i=nums.length-k; i<nums.length; i++)
            q1.add(nums[i]);
        for(int i=0; i<nums.length-k; i++)
            q1.add(nums[i]);
        
        for(int i=0; i<nums.length; i++)
            nums[i] = q1.remove();
    }
}

// Approach
// if k=1 we store last element in queue and then add elements from 0th index upto last index to queue.
// Now in general, for k we add elements from nums.length-k to end of array in queue and then from 0th index to nums.length-1 to the queue.
// Now we dequeue elements from the queue and store them in array
