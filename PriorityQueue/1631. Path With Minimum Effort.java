/*
1631. Path With Minimum Effort

You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 
Example 1:
Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.


*/

-------------

// Approach - T: O(m * n * log(m * n)), S: O(m * n)


class Solution {
    public int minimumEffortPath(int[][] heights) {
        // int[] = {i, j, dist};
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((x,y)->x[2]-y[2]);
        int m = heights.length;
        int n = heights[0].length;

        // Create a 2D array to store the minimum effort required to reach each cell.
        int[][] effort = new int[m][n];
        // Initialize all cells with maximum effort.
        for (int i = 0; i < m; i++) {
            Arrays.fill(effort[i], Integer.MAX_VALUE);
        }

        // Directions for moving up, down, left, and right.
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Start from the top-left corner.
        minHeap.offer(new int[] {0,0,0});

        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int row = curr[0];
            int col = curr[1];
            int currEffort = curr[2];
        
            // If we reached the bottom-right cell, return the minimum effort.
            if(row == m - 1 && col == n - 1) {
                return currEffort;
            }

            // Skip this cell if we have already visited it with a lower effort.
            // We could avoid the if condition around line 72 if we use >= here to make sure we dont oscillate b/w parent and curr cell 
            if(currEffort > effort[row][col])
                continue;

            // Update the minimum effort for this cell.
            effort[row][col] = currEffort;

            for(int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                    int newEffort = Math.max(currEffort, Math.abs(heights[newRow][newCol] - heights[row][col]));
                    if(effort[newRow][newCol] > newEffort) { // W/o this IF statement we get a TLE (time limit exceeded).
                     // We need to avoid infinite oscillations b/w parent and current cell which could happen if effort is equal. 
                     // For cells other than parent this efort will be max integer if it is not visited yet and even if it is visited 
                     // and we have the same effort we can skip it
                        effort[newRow][newCol] = newEffort;
                        minHeap.offer(new int[] {newRow, newCol, newEffort});
                    }
                }
            }
        }
        return -1; // If there is no path to the bottom-right corner.
    }
}

-----------------

// Approach 2
 

 class Solution {
    public int minimumEffortPath(int[][] heights) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> (a[0]-b[0]));
        minHeap.offer(new int[] {0, 0, 0});

        int[][] effort = new int[heights.length][heights[0].length];
        // Initialize all cells with maximum effort.
        for (int i = 0; i < heights.length; i++) {
            Arrays.fill(effort[i], Integer.MAX_VALUE);
        }


        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int distDiff = curr[0];
            int row = curr[1];
            int col = curr[2];

            if(row == heights.length-1 && col == heights[0].length-1)
                return curr[0];

            if(distDiff >= effort[row][col])
                continue;

            effort[row][col] = distDiff;
            int[][] directions = {{-1,0}, {0,1}, {1,0}, {0, -1}};
            for(int[] dir : directions) {
                int newRow = curr[1] + dir[0];
                int newCol = curr[2] + dir[1];

                if(newRow>=0 && newRow<heights.length &&
                   newCol>=0 && newCol<heights[0].length) {
                       int currDiff = Math.abs(heights[newRow][newCol] - heights[row][col]);
                       minHeap.offer(currDiff>distDiff ? new int[] {currDiff, newRow, newCol} : new int[] {distDiff, newRow, newCol} );
                   }
            }
        }

        return -1;
    }
}
