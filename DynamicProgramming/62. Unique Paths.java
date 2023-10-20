/*
62. Unique Paths

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move
to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

 

Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3

*/

---------------

// DP Tabulation with space optimization
// Approach - T: O(m*n), S: O(n)

class Solution {
    public int uniquePaths(int m, int n) {
        // Initialize an array to represent the previous row of cells.
        int[] prev = new int[n];

        // Initialize the values for the first row to 1 since there's only one way to reach each cell.
        for (int i = 0; i < n; i++) {
            prev[i] = 1;
        }

        // Calculate the unique paths for each cell based on the sum of paths from the cell above and left.
        for (int i = 1; i < m; i++) {
            int[] cur = new int[n];
            cur[0] = 1; // The leftmost cell in each row has only one way to reach it.
            for (int j = 1; j < n; j++) {
                cur[j] = prev[j] + cur[j - 1]; // Sum of paths from the cell above and from the left.
            }
            prev = cur; // Update the previous row to the current row for the next iteration.
        }

        // The result is stored in the rightest cell of the array.
        return prev[n - 1];
    }
}



---------------

// DP Tabulation
// Approach - T: O(m*n), S: O(m*n)


class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Initialize the values for the first row and first column to 1.
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Calculate the unique paths for each cell based on the sum of paths from the cell above and left.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // The result is stored in the bottom-right cell.
        return dp[m - 1][n - 1];
    }
}


---------------

// DP recursion with memoization
// Approach - T: O(m*n), S: O(m+n + m*n) ie O(recursive stack + dp array)


class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        // Initialize the memoization table with -1 to indicate uncalculated values.
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], -1);

        // Start the traversal from the bottom-right corner and calculate the number of unique paths to the top-left corner.
        return travel(m - 1, n - 1, dp);
    }

    // Helper function to recursively calculate the number of unique paths from (i, j) to (0, 0) with memoization.
    private int travel(int i, int j, int[][] dp) {
        // Base case: If we have reached the top-left corner, or top row or leftest column we have found a unique path to (0,0)
        if (i == 0 || j == 0)
            return 1;
        
        // Base case: If we go out of bounds, there are no unique paths.
        if (i < 0 || j < 0)
            return 0;

        // If the value is already calculated, return it from the memoization table.
        if (dp[i][j] != -1)
            return dp[i][j];

        // Calculate the number of unique paths by summing the paths coming from above and from the left.
        int up = travel(i - 1, j, dp); // Unique paths from the cell above.
        int left = travel(i, j - 1, dp); // Unique paths from the cell on the left.

        // Store the calculated value in the memoization table.
        dp[i][j] = up + left;

        return dp[i][j]; // Return the total unique paths from (i, j) to (0, 0).
    }
}

