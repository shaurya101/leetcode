/*
53. Maximum Subarray

Given an integer array nums, find the subarray with the largest sum, and return its sum.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Example 2:
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.

*/

-----------

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for(int i : nums) {
            currSum += i;
            maxSum = maxSum>currSum? maxSum: currSum;
            if(currSum < 0)
                currSum = 0;
        }
        return maxSum;
    }
}

// Kadane's Algorithm
// We will track the sum of the current subarray and the max Sum till now
// We will add the ith element to the currSum and then compare with maxSum. Update maxSum if necessary
// if currSum becomes less than zero, then whatever sum is there ahead it will become less of we consider this negative net sum subarray too. So we will not consider this negative sum for upcoming sum and reset sum to zero.
