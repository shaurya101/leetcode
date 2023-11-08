/*
2849. Determine if a Cell Is Reachable at a Given Time

You are given four integers sx, sy, fx, fy, and a non-negative integer t.
In an infinite 2D grid, you start at the cell (sx, sy). Each second, you must move to any of its adjacent cells.

Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.

A cell's adjacent cells are the 8 cells around it that share at least one corner with it. You can visit the same cell several times.

 

Example 1:
Input: sx = 2, sy = 4, fx = 7, fy = 7, t = 6
Output: true
Explanation: Starting at cell (2, 4), we can reach cell (7, 7) in exactly 6 seconds by going through the cells depicted in the picture above. 

*/

---------

// Approach 1 - T: O(1), S: O(1)

class Solution {
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        // If the start and finish positions are the same and time is exactly 1, return false
        if (sx == fx && sy == fy && t == 1)
            return false;

        // Calculate the minimum time it takes to reach the destination (either x or y distance)
        int minTime = Math.max(Math.abs(sx - fx), Math.abs(sy - fy));

        // If the available time is less than the minimum time required to reach the destination, return false
        if (t < minTime)
            return false;

        // Otherwise, it's reachable within the given time, so return true
        return true;
    }
}
