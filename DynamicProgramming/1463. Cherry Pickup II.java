/*
1463. Cherry Pickup II

You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you
can collect from the (i, j) cell.

You have two robots that can collect cherries for you:

Robot #1 is located at the top-left corner (0, 0), and
Robot #2 is located at the top-right corner (0, cols - 1).
Return the maximum number of cherries collection using both robots by following the rules below:

From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
When both robots stay in the same cell, only one takes the cherries.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in grid.
 

Example 1:
Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.

Example 2:
Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.

*/

------------

// Approach 1  - T:  O(n * m^2), S: O(m * m)
// DP tabulation with space optimisation
  

class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length; // Number of rows in the grid
        int m = grid[0].length; // Number of columns in the grid

        // stores sum of prev Row for all j1*j2 position combinations for a row
        // indexes are [posJ1][posJ2]
        int[][] prevSum = new int[m][m];

        // initialize j1*j2 prevSum for last row
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                prevSum[j1][j2] = (j1 == j2) ? grid[n-1][j1] : grid[n-1][j1] + grid[n-1][j2];
            }
        }


        // Iterate through the second last row to 1st row
        for (int i = n-2; i >= 0; i--) {
            // store sum of curr Row for all possible j1*j2 positions for that row
            int[][] currRowSum = new int[m][m];

            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    currRowSum[j1][j2] = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];

                    int max = 0;
                    // Iterate through possible 9 moves in prev Row(actually the (i+1)th row since we are iterating upwards) for both robots and take the max sum and add it to currSum[j1][j2]
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int J1 = j1 + dj1;
                            int J2 = j2 + dj2;

                            // Check if the new positions are within bounds of the grid
                            if (J1 >= 0 && J1 < m && J2 >= 0 && J2 < m) {
                                max = Math.max(max, prevSum[J1][J2]);
                            }
                        }
                    }
                    currRowSum[j1][j2] += max;
                }
            }
            prevSum = currRowSum;
        }
        
        return prevSum[0][m-1];
    }
}





------------

// Similar to above but wrong approach, explained why it wont work when we move from top to bottom
// DP tabulation with sapce optimisation

  
class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length; // Number of rows in the grid
        int m = grid[0].length; // Number of columns in the grid

        // stores sum of prev Row for all j1*j2 position combinations for that row
        // indexes are [posJ1][posJ2]
        int[][] prevSum = new int[m][m];

        // initialize
        // initially j1 is in 0th and j2 is at (m-1)th position.
        // Store this sum in prevSum
        prevSum[0][m-1] = (0 == m-1)? grid[0][0] : grid[0][0] + grid[0][m-1];


        // Iterate through the 2nd row onwards
        for (int i = 1; i < n; i++) {
            // store sum of curr Row for all possible j1*j2 positions for that row
            int[][] currRowSum = new int[m][m];

            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    currRowSum[j1][j2] = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];

                    int max = 0;
                    // Iterate through possible 9 moves in prev Row for both robots and take the max sum and add it to currSum[j1][j2]
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int J1 = j1 + dj1;
                            int J2 = j2 + dj2;

                            // Check if the new positions are within bounds of the grid
                            if (J1 >= 0 && J1 < m && J2 >= 0 && J2 < m) {
                                max = Math.max(max, prevSum[J1][J2]);
                            }
                        }
                    }
                    currRowSum[j1][j2] += max;
                }
            }
            prevSum = currRowSum;
        }

        // return max sum
        int max=0;
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                max = Math.max(max, prevSum[j1][j2]);
            }
        }
        return max;
    }
}


// will not satisfy all cases as we are considering extra cases too, since we consider all j1*j2 positions even if the robots could not reach there.
// Only solving it from last row to top snd then returning the one position where our robots started will give us the accurate answer
// as even though there would be greater sums when we reach to top row, we only consider the valid paths the robots can actually take
// since we know robots stared at top left and right corners. The extra cases are ommitted hence


---------------

// Approach 2  - T: O(n * m^2), S: O(n * m * m)
// DP tabulation
  

class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length; // Number of rows in the grid
        int m = grid[0].length; // Number of columns in the grid

        // Create a 3D array to store the maximum cherries picked at each position for both robots.
        int[][][] dp = new int[n][m][m];

        // Iterate through the rows of the grid in reverse order.
        for (int i = n - 1; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int cherries = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
                    dp[i][j1][j2] = cherries;

                    if (i < n - 1) {
                        int max = 0;
                        // Iterate through possible moves for both robots
                        for (int dj1 = -1; dj1 <= 1; dj1++) {
                            for (int dj2 = -1; dj2 <= 1; dj2++) {
                                int newJ1 = j1 + dj1;
                                int newJ2 = j2 + dj2;

                                // Check if the new positions are within bounds of the grid
                                if (newJ1 >= 0 && newJ1 < m && newJ2 >= 0 && newJ2 < m) {
                                    max = Math.max(max, dp[i + 1][newJ1][newJ2]);
                                }
                            }
                        }
                        dp[i][j1][j2] += max;
                    }
                }
            }
        }

        // The result is stored in the top-left corner of the dp array.
        return dp[0][0][m - 1];
    }
}


--------------

// Approach 3 - T: O(9^(n * m)), S: O(n * m * m)
// Recursion with memoization
  

class Solution {
    public int cherryPickup(int[][] grid) {
        // Initialize a 3D DP array to store subproblem results
        int[][][] dp = new int[grid.length][grid[0].length][grid[0].length];
        
        // Initialize the DP array with -1 values
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        


        // Start the recursive traversal with the two people at positions (0, 0) and (0, n-1)
        return travel(grid, 0, 0, grid[0].length - 1, dp);
    }
    
    private int travel(int[][] grid, int i, int j1, int j2, int[][][] dp) {
        // Check for invalid positions
        if (j1 < 0 || j1 >= grid[0].length || j2 < 0 || j2 >= grid[0].length)
            return Integer.MIN_VALUE;
        
        // Base case: If all rows are traversed, return 0 cherries
        if (i == grid.length)
            return 0;
        
        // If the result for the current subproblem is already calculated, return it
        if (dp[i][j1][j2] != -1)
            return dp[i][j1][j2];

        int max = Integer.MIN_VALUE;
        // Iterate over possible moves for both people in the next row
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                // Update the maximum cherries by considering the maximum result
                max = Math.max(max, travel(grid, i + 1, j1 + dj1, j2 + dj2, dp));
            }
        }
        
        // Update the result for the current subproblem
        max += (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
        return dp[i][j1][j2] = max;
    }
}
