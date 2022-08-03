/*
283. Move Zeroes

Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.

Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:
Input: nums = [0]
Output: [0]

*/

-------------
  
  // 1ms
  
class Solution {
    public void moveZeroes(int[] nums) {
        // we will fill numbers in array if they are not zero. At the end of traversal we will fill the remaining cells with 0.
        int k=-1; // tracks the last non zero element's index
        
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0)
                nums[++k] = nums[i];
        }
        // Now nums[] contains non zero elements till index k with order maintained
        // now the remaining cells we will fill 0's
        for(int i=k+1; i<nums.length; i++)
            nums[i] = 0;
    }
}

------------
  
  // same approach but slow (2ms). Why?
  
class Solution {
    public void moveZeroes(int[] nums) {
        // we will fill numbers in array if they are not zero. At the end of traversal we will fill the remaining cells with 0.
        int k=-1; // tracks the last non zero element's index
        
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0)
                nums[++k] = nums[i];
        }
        // Now nums[] contains non zero elements till index k with order maintained
        // now the remaining cells we will fill 0's
        for(int i=k+1; i<nums.length; i++)
            nums[i] = 0;
    }
}


-------------
  
  // Same approach and same speed 1ms

class Solution {
    public void moveZeroes(int[] nums) {
        int i=0;
        int pointer=0;
        
        // fill non zero values in pointer cell as we traverse the array
        while(i<nums.length) {
            if(nums[i] != 0){
                nums[pointer] = nums[i];
                pointer++;
            }
            i++;
        }
        // fill the remaining array with 0
        while(pointer<nums.length) {
            nums[pointer++] = 0;
        }
    }
}

// Approach - in place solution
// We will have one pointer and it would point to the cell where the next non zero number should be put.
// We will traverse the array from beginning and store the non zero values in pointer and increase the pointer by 1.
// We do not need to shift all the numbers ahead of pointer left by 1 cell as they will eventually be moved to their corect location(pointer cell) as we go on traversing the array ahead. This way we only need to traverse the array once.
// When the array is traversed completely, we will fill the rest of the cells with zero. 
