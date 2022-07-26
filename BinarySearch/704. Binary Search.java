/*
704. Binary Search

Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

Example 2:
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
*/

---------------

class Solution {
    public int search(int[] nums, int target) {
        int low=0;
        int high = nums.length-1;
        
        while(low<=high) { // NOTE condition includes equal to
            int mid = low+(high-low)/2;
            if(target>nums[mid])
                low = mid+1;
            else if(target<nums[mid])
                high = mid-1;
            else
                return mid;
        }
        return -1;
    }
}
// Binary search
// In sorted array we check if our target is > or < or equal to our mid.
// We update the low or high accordingly.
// we return mid if nums[mid] = target
// if low becomes greater than high, it means our target is not present in array and we return -1. 
