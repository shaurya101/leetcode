/*
934. Shortest Bridge

You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.


Example 1:
Input: grid = [[0,1],[1,0]]
Output: 1

Example 2:
Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2

*/

---------

// Approach: T: O(N^2) S: O(N^2)


class Solution {
    public int shortestBridge(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q1 = new ArrayDeque<>();

        // Step 1: Find and mark the first island using DFS
        boolean found = false;
        for (int i = 0; i < rows; i++) {
            if (found) break;
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, q1);
                    found = true;
                    break;
                }
            }
        }

        // Step 2: Initialize distances for BFS and perform BFS to find the shortest bridge
        int[][] distance = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int steps = 0;

        while (!q1.isEmpty()) {
            int size = q1.size();
            for (int k = 0; k < size; k++) {
                int[] curr = q1.poll();
                int currRow = curr[0];
                int currCol = curr[1];

                // Explore neighboring cells
                for (int i = 0; i < 4; i++) {
                    int newRow = currRow + distance[i][0];
                    int newCol = currCol + distance[i][1];

                    // Check if the neighboring cell is within bounds
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                        if (grid[newRow][newCol] == 1)
                            return steps; // Found the second island.
                        else if (grid[newRow][newCol] == 0) {
                            grid[newRow][newCol] = -1; // Mark as visited for BFS
                            q1.offer(new int[]{newRow, newCol});
                        }
                    }
                }
            }
            steps++;
        }

        // If no bridge is found, return -1
        return -1;
    }

    // Depth-First Search (DFS) to mark the first island
    private void dfs(int[][] grid, int row, int col, Queue<int[]> q1) {
        grid[row][col] = 2; // Mark as visited for DFS
        q1.offer(new int[]{row, col});

        int[][] distance = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // Explore neighboring cells
        for (int i = 0; i < 4; i++) {
            int newRow = row + distance[i][0];
            int newCol = col + distance[i][1];

            // Check if the neighboring cell is within bounds
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length 
                && grid[newRow][newCol] == 1)
                dfs(grid, newRow, newCol, q1);
        }
    }
}
