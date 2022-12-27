/*
922. Sort Array By Parity II

Given an array of integers nums, half of the integers in nums are odd, and the other half are even.
Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
Return any answer array that satisfies this condition.

Example 1:
Input: nums = [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.

Example 2:
Input: nums = [2,3]
Output: [2,3]

*/

=========

class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int i=0; // even pointer
        int j=nums.length-1; // odd pointer

        // we will arrange all even elements first then all odd elements in nums[]
        // at end of this loop 'i' will point at first odd element
        while(i<j) {
            while(i<nums.length && nums[i]%2 == 0)
                i++;
            while(j>0 && nums[j]%2 != 0)
                j--;
            if(i<j)
                swap(nums, i, j);
        }

        for(int x=1; x<nums.length; x += 2, i++) {
                swap(nums, x, i);
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// Approach - In place
// We will arrange all even elements first then all odd elements in nums[]. NOTE THIS CONCEPT AND METHOD.
// The odd elements will be from odd index (n/2 + 1)th index till n or from i to n
// We will traverse the odd indexes of nums again and swap odd elements in those indexes
// We traverse from 1 to n and increment by 2, traversing only odd indexes. We pull the odd elements from i to n, and swap them in xth index.
// Note, the original order will not be preserved
