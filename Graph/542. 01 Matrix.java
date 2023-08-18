/*
542. 01 Matrix

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]


*/
--------------------

// Approach
T: O(rows * cols).
S: O(rows * cols).

  
// import javafx.util.Pair;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
            return mat;

        // Initialize distances matrix with appropriate initial values
        int[][] distances = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                distances[i][j] = mat[i][j] == 0 ? 0 : Integer.MAX_VALUE;
            }
        }

        // Create a queue for BFS
        Queue<Pair<Integer, Integer>> q1 = new LinkedList<>();

        // Add all cells with value 0 to the queue to start BFS
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] == 0)
                    q1.offer(new Pair<>(i, j));
            }
        }

        // Arrays for exploring four possible directions (up, right, down, left)
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        // Perform BFS traversal
        while(!q1.isEmpty()) {
            Pair<Integer, Integer> cell = q1.poll();
            int row = cell.getKey();
            int col = cell.getValue();

            // Explore all four directions
            for(int i = 0; i < 4; i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];

                // Check if the new coordinates are within valid bounds
                if(newRow >= 0 && newCol >= 0 && newRow < mat.length && newCol < mat[0].length) {
                    // Check if updating the distance through the current cell is beneficial
                    if (distances[newRow][newCol] > distances[row][col] + 1) {
                        // Update the distance and enqueue the new cell for further exploration
                        distances[newRow][newCol] = distances[row][col] + 1;
                        q1.offer(new Pair<>(newRow, newCol));
                    }
                }
            }
        }
        return distances;
    }
}
