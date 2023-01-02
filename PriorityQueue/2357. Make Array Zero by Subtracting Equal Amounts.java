/*
2357. Make Array Zero by Subtracting Equal Amounts

You are given a non-negative integer array nums. In one operation, you must:

Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
Subtract x from every positive element in nums.
Return the minimum number of operations to make every element in nums equal to 0.

Example 1:
Input: nums = [1,5,0,3,5]
Output: 3
Explanation:
In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].

*/

============

class Solution {
    public int minimumOperations(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // default is min Heap

        for(int n : nums)
            minHeap.add(n);

        int subtractedSum = 0;
        int count = 0;
        while(!minHeap.isEmpty()) {
            int num = minHeap.remove();
            num -= subtractedSum;
            if(num > 0) {
                subtractedSum += num;
                count++;
            }
        }
        return count;
    }
}

// Approach
// Whenever we subtract the smallest number we also want to subtract it from the rest of elements
// However, we will not traverse the array again and again to subtract the smallest numbers from other elements
// To avoid this, we will store the 'total subtracted value' at all point of the many subtractions we will do
// We will store the numbers of array in a minHeap, the smallest number will be in the top. We will remove it and subtract the total subtracted value. If this resultant number after subtraction is greater than 0, then we will add the resultant number to the total subtracted d value and increase the count of steps by one.
