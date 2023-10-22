/*
931. Minimum Falling Path Sum

Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or 
diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), 
or (row + 1, col + 1).

 

Example 1:


Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.
Example 2:


Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.

*/


--------------

// Approach 1 - T: O(m*n), S: O(n)
// DP tabulation with space optimization

  
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int[] prev = new int[matrix[0].length];
        
        // Initialize the first row of the DP array with values from the first row of the matrix.
        for(int j=0; j<matrix[0].length; j++) {
            prev[j] = matrix[0][j];
        }
        
        // Traverse through the matrix from the second row to calculate the minimum falling path sum.
        for(int i=1; i<matrix.length; i++) {
            int[] cur = new int[matrix[0].length];

            for(int j=0; j<matrix[0].length; j++) {
                // Calculate the values of left, up, and right cells considering boundaries.
                int upLeft = j > 0 ? prev[j - 1] : Integer.MAX_VALUE;
                int up = prev[j];
                int upRight = j < matrix[0].length - 1 ? prev[j + 1] : Integer.MAX_VALUE;

                // Calculate the minimum falling path sum for the current cell.
                cur[j] = matrix[i][j] + Math.min(Math.min(upLeft, up), upRight);
            }
            
            // Update the previous row values with the current row for the next iteration.
            prev = cur;
        }
        
        // Find the minimum falling path sum in the last row (DP array).
        int minSum = Integer.MAX_VALUE;
        for(int j=0; j<matrix[0].length; j++) {
            minSum = Math.min(minSum, prev[j]);
        }
        
        // Return the minimum falling path sum.
        return minSum;
    }
}


--------------

// Approach 2 - T: O(m*n), S: O(m*n)
// Recursion with memoization


class Solution {
    public int minFallingPathSum(int[][] matrix) {
        // Create a DP table to store computed results
        int[][] dp = new int[matrix.length][matrix[0].length];
        
        // Initialize the DP table with a value that indicates uncomputed state
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(dp[i], matrix.length * (-100) - 1);
        }
        
        int minSum = Integer.MAX_VALUE;
        for (int j = matrix[0].length - 1; j >= 0; j--) {
            minSum = Math.min(minSum, travel(matrix, matrix.length - 1, j, dp));
        }
        return minSum;
    }
    
    private int travel(int[][] matrix, int i, int j, int[][] dp) {
        if (j < 0 || j >= matrix[0].length)
            return Integer.MAX_VALUE;
        if (i == 0)
            return matrix[0][j];
        if (dp[i][j] != matrix.length * (-100) - 1)
            return dp[i][j];
        
        // Calculate the minimum path sum recursively by considering three possible moves (up left, up, up right)
        int upLeft = travel(matrix, i - 1, j - 1, dp);
        int up = travel(matrix, i - 1, j, dp);
        int upRight = travel(matrix, i - 1, j + 1, dp);
        
        // Update the DP table with the minimum path sum for the current cell
        dp[i][j] = matrix[i][j] + Math.min(Math.min(upLeft, up), upRight);
        return dp[i][j];
    }
}
