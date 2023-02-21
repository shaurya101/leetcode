/*
540. Single Element in a Sorted Array

You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

Example 1:
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2

Example 2:
Input: nums = [3,3,7,7,10,11,11]
Output: 10

*/

---------------

// Approach - T: O(log n), S: O(1)
// Since there is only one single element and rest are all pairs, the array will be of odd length; And if we divide the array into two halves, the half after making pair of nums[mid], with odd length will have the single element
// If pair forms on left side and left side is odd, it means single element is on left
// If pair forms on left side and left side is even, it means single element is on right
// Similarly two cases, for when pair forms on right side


class Solution {
    public int singleNonDuplicate(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        int low = 0;
        int high = nums.length-1;
        int mid = 0;

        while(low<=high) {
            mid = low + (high-low)/2;
            // if mid is at either corner than that is single element since mid cannot reach at corner otherwise
            if(mid == 0)
                return nums[mid];
            if(mid == nums.length-1)
                return nums[mid];
            // checking for single element when mid is not in corner
            if(nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1])
                return nums[mid];

            // Now, here mid is not the single element, so it will have a pair on one of the sides
            // if nums[mid] pair forms on left side
            if(nums[mid] == nums[mid-1]) {
                if((mid-low+1)%2 == 0) // if even length
                    low = mid+1;
                else // if odd length
                    high = mid-2;
            }
            else { // if pair forms on right side
                if((high-mid+1)%2 == 0) // if even length
                    high = mid-1;
                else // if odd length
                    low = mid+2;
            }
        }
        return nums[mid];
    }
}
