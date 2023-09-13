/*
200. Number of Islands

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.
 

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

*/


-------------------

// Approach - 
// Time Complexity: O(M * N)
// Space Complexity: O(1) (additional space), O(M * N) (including the input grid)

  
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0; // Initialize the count of islands

        // Iterate through each cell in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j); // Explore the island using depth-first search
                    count++; // Increment the island count
                }
            }
        }
        return count; // Return the total number of islands
    }

    private void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0'; // Mark the current cell as visited (land to water)

        // Define the four possible directions to explore
        int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int[] dir : direction) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];

            // Check if the new cell is within bounds and part of the island
            if (newRow >= 0 && newRow < grid.length &&
                newCol >= 0 && newCol < grid[0].length &&
                grid[newRow][newCol] == '1') {
                dfs(grid, newRow, newCol); // Recursively explore the neighboring cell
            }
        }
    }
}
