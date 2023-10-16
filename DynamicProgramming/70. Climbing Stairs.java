/*
70. Climbing Stairs

You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 

Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

*/

----------------

// Approach 1 : T: O(N) S: O(1)
// DP tabulation with space optimisation

class Solution {
    public int climbStairs(int n) {
        // Base case: If there are 0 or 1 step, there is only one way to climb (no steps or one step).
        if (n <= 1)
            return 1;

        // Initialize variables to keep track of the number of ways to climb 'n' steps.
        int prev2 = 1; // Represents the number of ways to climb (i-2) steps
        int prev = 1;  // Represents the number of ways to climb (i-1) steps

        // Calculate the number of ways to climb the stairs for each number of steps from 2 to n.
        for (int i = 2; i <= n; i++) {
            // The number of ways to climb 'i' steps is the sum of the ways to climb 'i-1' steps and 'i-2' steps.
            int currI = prev + prev2;

            // Update the 'prev' and 'prev2' variables for the next iteration.
            prev2 = prev;
            prev = currI;
        }

        // Return the number of ways to climb 'n' steps, which is stored in the 'prev' variable.
        return prev;
    }
}



----------------

// Approach 2 : T: O(N) S: O(N)
// DP tabulation

class Solution {
    public int climbStairs(int n) {
        // Base case: If there are 0 or 1 step, there is only one way to climb (no steps or one step).
        if (n <= 1)
            return 1;

        // Create an DP array to store the number of ways needed to climb x steps.
        int[] dp = new int[n + 1];
        
        // There is one way to climb 0 steps and one way to climb 1 step.
        dp[0] = 1;
        dp[1] = 1;

        // Calculate the number of ways to climb the stairs for each number of steps from 2 to n.
        for (int i = 2; i <= n; i++) {
            // The number of ways to climb 'i' steps is the sum of the ways to climb 'i-1' steps and 'i-2' steps.
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Return the number of ways to climb 'n' steps, which is stored in dp[n].
        return dp[n];
    }
}



----------------
  
// Approach 3 : Recursive without DP (Will give TLE) We can use this to come up with DP tabulation approach Approach 2

class Solution {
    public int climbStairs(int n) {
        // only one way to reach step 0 or 1 from step 0 or 1
        if(n <= 1)
            return 1;
        // We can reach ith step from (i-1)th step or (i-2)th step since we can take 1 or 2 steps only
        // Note there will be further some ways we could reach (i-1)th step or (i-2)th step, hence the recursion
        // in our base condition, we have included the n==1, to avoid reaching negative index when we do climbStairs(n-2)
        return climbStairs(n-1) + climbStairs(n-2);
    }
}
