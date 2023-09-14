/*
1254. Number of Closed Islands

Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a 
closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:
Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).

Example 2:
Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2

*/

---------

// Approach 1 - more efficient as we do early exit on boundary
// Time :  O(rows * cols)
// Space :  O(rows * cols)


class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int closedIslandCount = 0; // Initialize the count of closed islands.

        // Iterate through each cell in the grid.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If the cell is unvisited land (grid[i][j] == 0) and it's a closed island, increment the count.
                if (grid[i][j] == 0 && isClosedIsland(grid, i, j)) {
                    closedIslandCount++;
                }
            }
        }
        return closedIslandCount; // Return the total count of closed islands.
    }

    // Depth-First Search (DFS) function to explore and mark a closed island.
    private boolean isClosedIsland(int[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Base cases:
        // If the current cell is out of bounds, return false.
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            return false;
        }
        // If the current cell is water (grid[row][col] == 1), return true, indicating it's part of the boundary.
        if (grid[row][col] == 1) {
            return true;
        }

        // Mark the current cell as visited by setting it to 1.
        grid[row][col] = 1;

        // Recursively check the neighboring cells (up, down, left, and right) to determine if the island is closed.
        boolean leftIsClosed = isClosedIsland(grid, row, col - 1);
        boolean rightIsClosed = isClosedIsland(grid, row, col + 1);
        boolean upIsClosed = isClosedIsland(grid, row - 1, col);
        boolean downIsClosed = isClosedIsland(grid, row + 1, col);

        // To be a closed island, all neighboring cells must also be part of the island.
        // Return true if all neighbors are part of the island.
        return leftIsClosed && rightIsClosed && upIsClosed && downIsClosed;
    }
}

---------------

// Approach 2 - Intuitive but little less efficent(although same complexity)
// Time :  O(rows * cols)
// Space :  O(rows * cols)

  
class Solution {
    public int closedIsland(int[][] grid) {
        // Traverse the first and last rows to mark boundary-connected land cells
        for(int i = 0; i < grid[0].length; i++) {
            if(grid[0][i] == 0)
                dfs(grid, 0, i);
            if(grid[grid.length - 1][i] == 0)
                dfs(grid, grid.length - 1, i);
        }
        
        // Traverse the first and last columns to mark boundary-connected land cells
        for(int i = 0; i < grid.length; i++) {
            if(grid[i][0] == 0)
                dfs(grid, i, 0);
            if(grid[i][grid[0].length - 1] == 0)
                dfs(grid, i, grid[0].length - 1);
        }

        int count = 0;
        // Count the number of closed islands
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    // Start DFS from a land cell and mark the connected land as visited
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void dfs(int[][] grid, int row, int col) {
        grid[row][col] = 1; // Mark the current land cell as water(visited)

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // Possible directions to explore
        for(int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if the new cell is within bounds and is unvisited land
            if(newRow >= 0 && newRow < grid.length &&
               newCol >= 0 && newCol < grid[0].length &&
               grid[newRow][newCol] == 0) {
                // Recursively explore the unvisited land cell
                dfs(grid, newRow, newCol);
            }
        }
    }
}
