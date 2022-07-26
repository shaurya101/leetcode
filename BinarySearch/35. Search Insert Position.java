/*
35. Search Insert Position

Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [1,3,5,6], target = 5
Output: 2

Example 2:
Input: nums = [1,3,5,6], target = 2
Output: 1
*/

-----------------
  
class Solution {
    public int searchInsert(int[] nums, int target) {
        int low=0;
        int high=nums.length-1;
        int mid=0;
        
        while(low<=high) {
            mid = low+(high-low)/2;
            if(target < nums[mid])
                high=mid-1;
            else if(target > nums[mid])
                low=mid+1;
            else
                return mid;
        }
        // At this point means, target was not present in array
        // our mid is at the index just smaller or just bigger than our target (distinct numbers in array)
        // example if our target is 2 then mid will be index of value 1 or 3
        if(target<nums[mid])
            return mid;
        else
            return mid+1;
    }
}

// Approach
// return mid if target is found
// if target is not there, our mid will either be just greater or less than our target.
// example: nums = [1,3,5,6], target = 2; mid will be 1 or 3.
// Now if mid is 1, 2 will come one place ahead it
// if mid is 3, 2 will come in its place
