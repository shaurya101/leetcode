/*
416. Partition Equal Subset Sum

Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both
subsets is equal or false otherwise.
 
Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

*/


---------------

// DP tabulation with space optimisation
// Approach 1 - T: O(n * sum), S: O(sum)

  
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0; // Variable to store the total sum of the numbers.
        
        // Calculate the total sum of the numbers in the array.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        // Check if the total sum is odd, which makes partitioning impossible.
        if (sum % 2 != 0) {
            return false;
        }

        // Create an array to track previous results (booleans).
        boolean[] prev = new boolean[sum / 2 + 1];
        
        // Initialize values for 'prev' based on the first number.
        prev[0] = true;
        if (nums[0] <= sum / 2) {
            prev[nums[0]] = true;
        }

        // Loop through the numbers in the array.
        for (int ind = 1; ind < nums.length; ind++) {
            // Create an array to track current results (booleans).
            boolean[] curr = new boolean[sum / 2 + 1];
            curr[0] = true;
            
            // Loop through potential target sums.
            for (int target = 1; target <= sum / 2; target++) {
                boolean notTake = prev[target];
                boolean take = false;
                
                // Check if it's possible to take the current number without going out of bounds.
                if (nums[ind] <= target) {
                    take = prev[target - nums[ind]];
                }
                
                // Update the 'curr' array based on taking or not taking the current number.
                curr[target] = take || notTake;
            }
            
            // 'prev' is updated to 'curr' for the next iteration.
            prev = curr;
        }
        
        // The result is stored in 'prev[sum/2]' which indicates if partitioning is possible.
        return prev[sum / 2];
    }
}


------------------

// DP tabulation
// Approach 2 - T: O(n * sum), S: O(n * sum)
  
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0; // Variable to store the total sum of the numbers.

        // Calculate the total sum of the numbers in the array.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        // Check if the total sum is odd, which makes partitioning impossible.
        if (sum % 2 != 0) {
            return false;
        }

        // Create a 2D boolean array 'dp' to track if a sum can be achieved with selected numbers.
        boolean[][] dp = new boolean[nums.length][sum / 2 + 1];

        // Initialize values for the first row of 'dp' based on the first number.
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= sum / 2) {
            dp[0][nums[0]] = true;
        }

        // Loop through the numbers in the array and potential target sums.
        for (int ind = 1; ind < nums.length; ind++) {
            for (int target = 1; target <= sum / 2; target++) {
                boolean notTake = dp[ind - 1][target];
                boolean take = false;
                
                // Check if it's possible to take the current number without going out of bounds.
                if (nums[ind] <= target) {
                    take = dp[ind - 1][target - nums[ind]];
                }
                
                // Update 'dp' for the current number and target sum.
                dp[ind][target] = take || notTake;
            }
        }
        
        // The result is stored in 'dp[nums.length-1][sum/2]' which indicates if partitioning is possible.
        return dp[nums.length - 1][sum / 2];
    }
}


-----------------

// Recursion with memoization
// Approach 2 - T: O(n * sum), S: O(n * sum)
// Boolean[] vs boolean[]

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0; // Variable to store the total sum of the numbers.

        // Calculate the total sum of the numbers in the array.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        // Check if the total sum is odd, which makes partitioning impossible.
        if (sum % 2 != 0) {
            return false;
        }

        // Create a 2D Boolean array 'dp' to track if a sum can be achieved with selected numbers.
        // Using Boolean allows for three possible values: true, false, and null.
        // Initialize the array with null values, indicating that no subproblems have been solved initially.
        Boolean[][] dp = new Boolean[nums.length][sum / 2 + 1];

        // Call the recursive function to determine if partitioning is possible.
        return fn(nums.length - 1, sum / 2, nums, dp);
    }

    private boolean fn(int ind, int target, int[] nums, Boolean[][] dp) {
        // If the target sum is 0, we've successfully partitioned the array.
        if (target == 0) {
            return true;
        }
        
        // If we've reached the first element and the target is equal to that element, we've successfully partitioned.
        if (ind == 0) {
            return target == nums[0];
        }
        
        // If the result for the current index and target is already computed, return it.
        if (dp[ind][target] != null) {
            return dp[ind][target];
        }

        // Calculate the result for the current index and target.
        boolean notTake = fn(ind - 1, target, nums, dp);
        boolean take = false;
        
        // Check if it's possible to take the current number without going out of bounds.
        if (target >= nums[ind]) {
            take = fn(ind - 1, target - nums[ind], nums, dp);
        }
        
        // Update the result in the dp array and return it.
        return dp[ind][target] = take || notTake;
    }
}
