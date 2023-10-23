/*
221. Maximal Square

Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 
Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4

Example 2:
Input: matrix = [["0","1"],["1","0"]]
Output: 1

*/

----------

// Approach 1 - T: O(m * n), S: O(n)
// DP tabulation with space optimisation

  
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSide = 0;

        // Initialize an array to store the values for the first row.
        int[] prev = new int[n];

        // Initialize the first row and maxSide.
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') {
                prev[i] = 1;
                maxSide = 1;
            }
        }

        // Loop through the matrix to find the maximal square.
        for (int i = 1; i < m; i++) {
            int[] cur = new int[n];
            cur[0] = matrix[i][0]-'0'; //convert from char to int
            maxSide = Math.max(maxSide, cur[0]); // we can have a case when only cur[0] is '1' and rest matrix is '0'
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    prev[j] = 0;
                    continue;
                }
                // else
                int up = prev[j];
                int upLeft = prev[j - 1];
                int left = cur[j - 1];

                // Calculate the side of the square at the current cell.
                cur[j] = Math.min(Math.min(up, upLeft), left) + 1;

                // Update maxSide with the maximum square size found so far.
                maxSide = Math.max(maxSide, cur[j]);
            }
            prev = cur;
        }

        // Return the area of the maximal square (side length squared).
        return maxSide * maxSide;
    }
}


-----------------

// Approach 2 - T: O(m * n), S: O(m*n)
// DP tabulation
  

class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSide = 0;

        int[][] dp = new int[m][n];
        
        // Initialize the first row and column of the DP table and maxSide.
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                maxSide = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                maxSide = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                    continue;
                }
                int up = dp[i - 1][j];
                int upLeft = dp[i - 1][j - 1];
                int left = dp[i][j - 1];
                dp[i][j] = Math.min(Math.min(up, upLeft), left) + 1;
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }

        // Return the area of the maximal square (side length squared).
        return maxSide * maxSide;
    }
}
