/*
198. House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only 
constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically 
contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight 
without alerting the police.
 

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

*/

-------------

// DP - tabulation with space optimization
// Approach 1 : T:O(N), S: O(1)

class Solution {
    public int rob(int[] nums) {
        // Initialize variables to store the maximum amount that can be robbed for the current and previous houses.
        int prev = nums[0];
        int prev2 = 0;

        for (int i = 1; i < nums.length; i++) {
            // Calculate the maximum amount if the current house is robbed, considering the possibility of skipping one house.
            int robIndex = nums[i] + prev2;

            // Maximum amount if the current house is not robbed.
            int notRob = 0 + prev;

            // Update the current maximum amount as the greater of robbing the current house or not robbing it.
            int currI = Math.max(robIndex, notRob);

            // Update the previous and two-houses-ago maximum amounts for the next iteration.
            prev2 = prev;
            prev = currI;
        }

        // The maximum amount that can be robbed is found in the 'prev' variable after iterating through all houses.
        return prev;
    }
}




-------------

// DP - tabulation
// Approach 2 : T:O(N), S: O(N)

class Solution {
    public int rob(int[] nums) {
        // Create a dynamic programming array to store the maximum amount that can be robbed for each house.
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        // Iterate through the houses, calculating the maximum amount that can be robbed for each house.
        for (int i = 1; i < nums.length; i++) {
            // Calculate the maximum amount if the current house is robbed, considering that index 'i-2' could be negative index, which would happen only when i=1
            int robIndex = (i > 1) ? nums[i] + dp[i - 2] : nums[i];

            // Maximum amount if the current house is not robbed.
            int notRob = 0 + dp[i - 1];

            // Store the maximum of robbing the current house or not robbing it in the dp array.
            dp[i] = Math.max(robIndex, notRob);
        }

        // The maximum amount that can be robbed is found at the last position of the dp array.
        return dp[nums.length - 1];
    }
}




-------------

// DP - recursive memoization
// Approach 3 : T:O(N), S: O(N)


class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return robPlan(nums, nums.length - 1, dp);
    }

    private int robPlan(int[] nums, int index, int[] dp) {
        // Base case: If we have reached index 0, we will always take as there is no houses prior to it to consider
        if (index == 0)
            return nums[0];
        
        // Base case: If the index is negative, there are no more houses to consider.
        if (index < 0)
            return 0;
        
        // If the result for the current index is already calculated, return it.
        if (dp[index] != -1)
            return dp[index];
        
        // Calculate the maximum amount if the current house is robbed or not robbed.
        int robIndex = nums[index] + robPlan(nums, index - 2, dp);
        int notRob = robPlan(nums, index - 1, dp);
        
        // Store the maximum value in the memoization table and return it.
        return dp[index] = Math.max(robIndex, notRob);
    }
}
