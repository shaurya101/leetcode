/*
841. Keys and Rooms

There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms.
However, you cannot enter a locked room without having its key.
When you visit a room, you may find a set of distinct keys in it.
Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.
 
Example 1:
Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation: 
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.

Example 2:
Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.

*/

----------------

// Approach
// T: O(N + E), where N is the number of rooms (vertices) and E is the total number of keys (edges) in the graph.
// S: O(N), vis array and recursive stack

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] vis = new boolean[rooms.size()];
        int visitedCount = dfs(rooms, vis, 0, 0); // Start the DFS from room 0
        return visitedCount == rooms.size();
    }

    private int dfs(List<List<Integer>> rooms, boolean[] vis, int visitedCount, int currRoom) {
        vis[currRoom] = true;
        visitedCount++;
        for(int neighbor: rooms.get(currRoom)) {
            if(!vis[neighbor])
                visitedCount = dfs(rooms, vis, visitedCount, neighbor);
        }
        return visitedCount;
    }
}

// We assume ith room is connected to all rooms in rooms[i] as rooms[i] are keys to rooms which ith room has
// We dont consider the case of connected components or islands when running dfs or bfs as that will count rooms which we didnt have keys for. So we start with dfs of room 0 as that room is unlocked.
