/*
120. Triangle

Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you
may move to either index i or index i + 1 on the next row.
 

Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).


Example 2:
Input: triangle = [[-10]]
Output: -10

*/

----------------

// Approach 1 - T: O(n^2), S: O(n)
// DP tabulation with space optimization

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];

        // Initialize DP array with corresponding bottom row triangle values.
        for (int i = 0; i < triangle.size(); i++) {
            dp[i] = triangle.get(triangle.size() - 1).get(i);
        }

        // Fill in the DP array from the second-to-last row to the top.
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int down = dp[j];
                int diagRight = dp[j + 1];
                dp[j] = triangle.get(i).get(j) + Math.min(down, diagRight);
            }
        }

        // The result is stored at the 1st cell of DP array.
        return dp[0];
    }
}

/*

Time Complexity: O(n^2), where N is the number of rows in the triangle.
In this solution, you iterate through all rows of the triangle.
For each row, you iterate through a maximum of N elements where N is the number of rows.
Within the inner loop, you perform constant time operations (calculating down and diagRight and updating dp[j]).

Space Complexity: O(n)
The space complexity is determined by the DP array dp, which is of size N.
You only store a single 1D array to keep track of the minimum path sum for each row.

*/

----------------

// Approach 2 - T: O(n^2), S: O(n^2)
// DP tabulation

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>> dp = new ArrayList<>();

        // Initialize last row of DP table with corresponding triangle values
        for (int i = 0; i < triangle.size(); i++) {
            dp.add(new ArrayList<Integer>());
            for (int j = 0; j <= i; j++) {
                dp.get(i).add(triangle.get(i).get(j));
            }
        }

        // Fill in the DP table from the second-to-last row to the top.
        for (int i = triangle.size() - 2; i >= 0; i--) {
            // we can traverse j from i to 0 as well
            for (int j = 0; j <= i; j++) {
                int down = dp.get(i + 1).get(j);
                int diagRight = dp.get(i + 1).get(j + 1);
                dp.get(i).set(j, triangle.get(i).get(j) + Math.min(down, diagRight));
            }
        }

        // The result is stored at the top of the DP table.
        return dp.get(0).get(0);
    }
}

/*

Time Complexity: O(n^2)
The outer loop iterates through each row of the triangle, which requires O(N) iterations, where N is the number of rows in the triangle.
The inner loop iterates through each element in the current row, which also requires O(N) iterations in the worst case.
Within the inner loop, you perform constant-time operations, so the overall time complexity is O(N^2).

Space Complexity: O(n^2)
You're using a 2D List dp to store the dynamic programming table. This table has dimensions N x N, where N is the number of rows in
the triangle.
Therefore, the space complexity is O(N^2) due to the storage of the DP table.

*/


----------------

// Approach 3 - T: O(n^2), S: O(n^2)
// Recursion with memoization

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>> dp = new ArrayList<>();

        // Initialize a DP table with -1 values.
        for (int i = 0; i < triangle.size(); i++) {
            dp.add(new ArrayList<Integer>());
            for (int j = 0; j <= i; j++) {
                dp.get(i).add(-1);
            }
        }

        // Start the dynamic programming traversal from the top of the triangle.
        return travel(triangle, 0, 0, dp);
    }

    private int travel(List<List<Integer>> triangle, int i, int j, List<List<Integer>> dp) {
        if (i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }

        if (dp.get(i).get(j) != -1) {
            return dp.get(i).get(j);
        }

        // Calculate the minimum path sum by considering the two possible moves (down and diagonal right).
        int down = travel(triangle, i + 1, j, dp);
        int diagRight = travel(triangle, i + 1, j + 1, dp);

        // Update the DP table with the minimum path sum for the current cell.
        dp.get(i).set(j, triangle.get(i).get(j) + Math.min(down, diagRight));

        return dp.get(i).get(j);

        // returning at same line fails
        // return dp.get(i).set(j, triangle.get(i).get(j) + Math.min(down, diagRight));
    }
}

/*

Time Complexity: O(N^2)
The traversal of the triangle's cells is done in a depth-first manner, and each cell is visited only once. Therefore, the time 
complexity is linear in terms of the number of cells in the triangle, which is O(N^2), where N is the number of rows in the triangle.

Space Complexity: O(N^2)
You're using a 2D List dp to store the dynamic programming table. This table has dimensions N x N, where N is the number of rows in 
the triangle.
The recursive function uses the call stack, but since the triangle has N^2 cells, the maximum depth of the call stack is also N^2. 
So the space complexity is O(N^2) due to the storage of the DP table and the call stack.

*/
