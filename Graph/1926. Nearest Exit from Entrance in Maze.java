/*
1926. Nearest Exit from Entrance in Maze

You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). 
You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column
of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall,
and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. 
An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

Example 1:
Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.

Example 2:
Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.

*/

------------

  
class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<int[]> q1 = new LinkedList<>();
        q1.offer(new int[] {entrance[0], entrance[1]});
        maze[entrance[0]][entrance[1]] = '+'; // Mark the entrance as visited

        int steps = 0;

        int[][] dels = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible directions

        while (!q1.isEmpty()) {
            int size = q1.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q1.poll(); // Dequeue the current cell
                int row = curr[0];
                int col = curr[1];

                // Check if the cell is at the maze's edge and not the entrance
                if ((row != entrance[0] || col != entrance[1]) &&
                    (row == 0 || row == maze.length - 1 || col == 0 || col == maze[0].length - 1)) {
                    if (steps != 0) // Skip the entrance cell
                        return steps;
                }

                for (int[] del : dels) {
                    int newRow = row + del[0];
                    int newCol = col + del[1];

                    // Check if the new cell is within bounds and unvisited
                    if (newRow >= 0 && newRow < maze.length && newCol >= 0 && newCol < maze[0].length && maze[newRow][newCol] == '.') {
                        q1.offer(new int[] {newRow, newCol}); // Enqueue the new cell
                        maze[newRow][newCol] = '+'; // Mark the new cell as visited
                    }
                }
            }
            steps++; // Move to the next step
        }

        return -1; // No valid exit found
    }
}
