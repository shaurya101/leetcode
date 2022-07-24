/*
1. Two Sum

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]

*/

------------------

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
      
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target-nums[i]))
                return new int[]{map.get(target - nums[i]), i};
             else 
                map.put(nums[i], i);                            
        }
        
        return null;
    }
}

// Iterate the array and check if its target-nums[i] value is present in hasMap. 
// if yes, return the current index and the index of key of target-nums[i]
// else add the key in hashmap

