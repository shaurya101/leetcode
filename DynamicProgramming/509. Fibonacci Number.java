/*
509. Fibonacci Number

The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).

Example 1:
Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.

*/
-------------

// Approach : T: O(N), S: O(1)
// DP - tabulation with space optimisation
    
class Solution {
    public int fib(int n) {
        // Base case: If n is 0 or 1, return n
        if (n <= 1)
            return n;

        // Initialize variables to keep track of the previous two Fibonacci numbers
        // We dont need dp array as we only need to track previous two fibonnaci numbers
        int prev2 = 0; // Represents the Fibonacci number at index (i-2)
        int prev = 1;  // Represents the Fibonacci number at index (i-1)

        // Calculate Fibonacci numbers from 2 to 'n' using a bottom-up approach
        for (int i = 2; i <= n; i++) {
            // Calculate the Fibonacci number at index 'i' by adding the previous two numbers
            int currI = prev + prev2; 

            // Update the previous two numbers for the next iteration
            prev2 = prev;
            prev = currI;
        }

        // Return the Fibonacci number at index 'n'
        return prev;
    }
}


    
-------------
    
// Approach : T: O(N), S: O(N)
// DP - tabulation(bottom up approach)
    
class Solution {
    public int fib(int n) {
        // Base case: If n is 0 or 1, return n
        if (n <= 1)
            return n;

        // Initialize an array to store Fibonacci numbers up to 'n'
        int[] dp = new int[n + 1];
        dp[0] = 0; // Fibonacci number at index 0 is 0
        dp[1] = 1; // Fibonacci number at index 1 is 1

        // Calculate Fibonacci numbers from 2 to 'n' using a bottom-up approach
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // Calculate Fibonacci number at index 'i'
        }

        // Return the Fibonacci number at index 'n'
        return dp[n];
    }
}


-------------

class Solution {
    public int fib(int n) {
        if(n<=0)
            return n;
        return fib(n-1)+fib(n-2);
    }
}

// Approach - less efficient
// We use recursion to find F(n) which is equal to F(n-1)+F(n-2).
// if n==0, return 0; if n==1 return 1
