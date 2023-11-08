/*
1992. Find All Groups of Farmland

You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of
farmland.

To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. These rectangular
areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another
farmland in a different group.

land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is
(m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland. A group of farmland with a top left
corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].

Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no groups of farmland,
return an empty array. You may return the answer in any order.


Example 1:
Input: land = [[1,0,0],[0,1,1],[0,1,1]]
Output: [[0,0,0,0],[1,1,2,2]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].

Example 2:
Input: land = [[1,1],[1,1]]
Output: [[0,0,1,1]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].

*/

--------------

// Approach - T: O(m*n), S: O(1)

class Solution {
    public int[][] findFarmland(int[][] land) {
        List<int[]> result = new ArrayList<>();
        int m = land.length;
        int n = land[0].length;

        for (int r1 = 0; r1 < m; r1++) {
            for (int c1 = 0; c1 < n; c1++) {
                if (land[r1][c1] == 1) {
                    int r2 = r1, c2 = c1;

                    // Find the boundaries of the farmland
                    // Acc to ques, since no adjacent groups are there, if land[r1+1][c1] and land[r1][c1+1] is 1 then land[r1+1][c1+1] would also be 1
                    while (r2 < m && land[r2][c1] == 1) {
                        r2++;
                    }
                    while (c2 < n && land[r1][c2] == 1) {
                        c2++;
                    }

                    // Mark the farmland as visited
                    for (int i = r1; i < r2; i++) {
                        for (int j = c1; j < c2; j++) {
                            land[i][j] = 2;
                        }
                    }

                    result.add(new int[]{r1, c1, r2 - 1, c2 - 1});
                }
            }
        }

        return result.toArray(new int[result.size()][4]);
    }
}

