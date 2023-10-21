/*
64. Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers 
along its path.

Note: You can only move either down or right at any point in time.
 

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12

*/


-----------------

// Approach 1 - T: O(m*n), S: O(n)
// DP tabulation with space optimization

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        dp[0] = grid[0][0];

        // Initialize dp[1]->dp[n-1]; the values for the first row.
        for (int j = 1; j < n; j++) {
            dp[j] = grid[0][j] + dp[j - 1];
        }

        // Fill in the rest of the DP table.
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                int up = dp[j];
                int left = dp[j - 1];
                dp[j] = grid[i][j] + Math.min(up, left);
            }
        }

        // The result is stored in the bottom-right cell.
        return dp[n - 1];
    }
}
  
-----------------

// Approach 2 - T: O(m*n), S: O(m*n)
// DP tabulation
  

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // Initialize the top-left cell.
        dp[0][0] = grid[0][0];

        // Initialize the values for the first row and the first column.
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }

        // Fill in the rest of the DP table.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int up = dp[i - 1][j];
                int left = dp[i][j - 1];
                dp[i][j] = grid[i][j] + Math.min(up, left);
            }
        }

        // The result is stored in the bottom-right cell.
        return dp[m - 1][n - 1];
    }
}


-----------------

// Approach 3 - T: O(m*n), S: O(m*n)
// Recursion with memoization
  

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++)
            Arrays.fill(dp[i], -1);
        return travel(grid, m-1, n-1, dp);
    }
    private int travel(int[][] grid, int i, int j, int[][] dp) {
        if(i==0 && j==0)
            return grid[0][0];
        if(i<0 || j<0)
            return Integer.MAX_VALUE;
        if(dp[i][j] != -1)
            return dp[i][j];
        int up = travel(grid, i-1, j, dp);
        int left = travel(grid, i, j-1, dp);
        return dp[i][j] = grid[i][j] + Math.min(up, left);
    }
}

/*

Time Complexity: O(m * n)
In the worst case, you visit all M * N cells in the grid exactly once.
The memoization array dp ensures that you don't recompute the same subproblems.

Space Complexity: O(m * n)
The space complexity is determined by the memoization array dp, which is of size M * N.
This solution has a time and space complexity of O(M * N), which is relatively efficient. 
It calculates the minimum path sum in the grid using a top-down recursive approach with memoization.

*/
  
-----------------

// Approach 4 - T: O(m * n * log(m * n)), S: O(m*n)
// Dijkstra's algo
  

class Solution {
    public int minPathSum(int[][] grid) {
        // {pathSum, i, j}
        PriorityQueue<int[]> q1 = new PriorityQueue<>((a, b)->a[0]-b[0]);
        q1.offer(new int[] {grid[0][0], 0, 0});

        int[][] sum = new int[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++)
            Arrays.fill(sum[i], Integer.MAX_VALUE);

        while(!q1.isEmpty()) {
            int[] curr = q1.poll();
            int pathSum = curr[0];
            int row = curr[1];
            int col = curr[2];
            System.out.println(pathSum+" "+row + " " +col);

            if(row == grid.length-1 && col == grid[0].length-1)
                return pathSum;
            if(pathSum >= sum[row][col])
                continue;
            sum[row][col] = pathSum;

            int[][] directions = {{0, 1}, {1, 0}};
            for(int[] dir : directions) {
                int newRow = row+dir[0];
                int newCol = col+dir[1];
                if(newRow>=0 && newRow<grid.length &&
                   newCol>=0 && newCol<grid[0].length) {
                       q1.offer(new int[] {pathSum+grid[newRow][newCol], newRow, newCol});
                   }
            }

        }
        return -1;
    }
}

/* 

Time Complexity: O(m * n * log(m * n))
In the worst case, you can visit all M * N cells of the grid.
For each cell, you perform a log(M * N) operation when adding to the priority queue.
So, the total time complexity is O(M * N * log(M * N)).

Space Complexity: O(m * n)
The sum array is used to keep track of the minimum path sum for each cell, which is O(M * N) in space.
The q1 priority queue can also hold up to M * N elements in the worst case.

Overall, this solution has a relatively efficient time complexity for finding the minimum path sum in the grid,
thanks to the priority queue. However, the space complexity is somewhat high due to the sum array, and there are
more memory-efficient approaches to solve this problem using dynamic programming.

*/
