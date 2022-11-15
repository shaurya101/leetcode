/*
34. Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/
-------------------

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int low=0;
        int high = nums.length-1;
        int index=-1; // this index will have target value. It could be the first occurence or last or anyone in between but it will hold target.
        
        while(low<=high) {
            int mid = low + (high-low)/2;
            if(target > nums[mid])
                low = mid+1;
            else if(target<nums[mid])
                high = mid-1;
            else{
                index=mid;
                break;
            }
        }
        // if index was not updated, it means target was not present
        if(index == -1)
            return new int[] {-1,-1};
        
        // At this point index holds one of the targets index. Now we will find the first and last index.
        int firstIndex=index;
        int lastIndex=index;
        
        // finding first index
        // we will consider a sub array of original array from index 0 to our variable index
        low=0;
        high=index;
        while(low<=high) {
            int mid = low+(high-low)/2;
            if(target != nums[mid])
                low=mid+1;
            else{
                firstIndex = mid;
                high = mid-1;
            }
        }
        
        // finding last occurence
        // we will consider the second part of array as sub array ie from index=index to nums.length-1.
        low = index;
        high = nums.length-1;
        
        while(low<=high) {
            int mid = low+(high-low)/2;
            if(target != nums[mid])
                high=mid-1;
            else{
                lastIndex = mid;
                low = mid+1;
            }
        }
        
        return new int[]{firstIndex, lastIndex};
    }
}

// Approach
// We will find one index of target. Could be any occurence.
// Now we will imagine two subarrays of our array like
// 1. 0 to index for finding first occurence
// 2. and index to nums.length-1 for finding last occurence
// In both the arrays there will be two types of numbers - there will be our target values and rest will be non-target values. And since its a sorted array, our target values will be grouped together at the beginning or end of these sub arrays.
// This question is an extension of Q.278


----------
    
// Approach 2
    
    class Solution {
    public int[] searchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        int mid = 0;
        int firstPos = -1;
        
        while(low<=high) {
            mid = low+(high-low)/2;
            if(nums[mid] < target){
                low = mid+1;
                continue;
            }
            if(nums[mid] == target)
                firstPos = mid;
            high = mid-1;
            }
        
        if(firstPos == -1) // if target does not exist return {-1,-1}
            return new int[] {-1, -1};
        else
            low = firstPos;
        high = nums.length-1;
        int lastPos = -1;
        // Now since we already know our firstPos, we only have to search from firstPos to last, ie out nums[mid] will never be less than target(since the low starts from target)!
        
        while(low<=high) {
            mid = low+(high-low)/2;
            if(nums[mid]>target) {
                high = mid-1;
                continue;
            }
            if(nums[mid] == target)
                lastPos = mid;
            low = mid+1;
        }
        
        return new int[] {firstPos, lastPos};
    }
}
