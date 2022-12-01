/*
1929. Concatenation of Array

Given an integer array nums of length n, you want to create an array ans of length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).
Specifically, ans is the concatenation of two nums arrays.
Return the array ans.

Example 1:
Input: nums = [1,2,1]
Output: [1,2,1,1,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
- ans = [1,2,1,1,2,1]

Example 2:
Input: nums = [1,3,2,1]
Output: [1,3,2,1,1,3,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
- ans = [1,3,2,1,1,3,2,1]

*/

------------

class Solution {
    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length*2];
        
        for(int i=0; i<nums.length*2; i++) 
            ans[i] = nums[i%nums.length];
        
        return ans;
        
    }
}

// we can do it in a single loop using the property of mod (which is also used in hashing) and in circular queue incrementation
// suppose nums is of length 3, so the indexes of double nums are as follows 
// index - 0 1 2 3 4 5
// 0%3 = 0
// 1%3 = 1
// 2%3 = 2
// 3%3 = 0
// 4%3 = 1
// 5%3 = 2
