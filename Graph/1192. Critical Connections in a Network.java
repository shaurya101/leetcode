/*
1192. Critical Connections in a Network

There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network
where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or 
indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.


Example 1:
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.

Example 2:
Input: n = 2, connections = [[0,1]]
Output: [[0,1]]

*/

-----------

// Approach - T: O(V+E), S: O(V+E)
// Tarjan's algorithm

class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adjList = new ArrayList<>();

        // Step 1: Create an adjacency list representation of the graph
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());

        for (List<Integer> connection : connections) {
            adjList.get(connection.get(0)).add(connection.get(1));
            adjList.get(connection.get(1)).add(connection.get(0));
        }

        // Arrays to store information during DFS traversal
        boolean[] vis = new boolean[n]; // visited nodes
        int[] low = new int[n]; // lowest reachable node
       /*
low[] - Firstly, it is the lowest of low[adjacent nodes], ie lowest of a node’s adjacent node except its parent. 
Basically, it is the lowest node(ie node discovered earlier) which our current node can reach upto excluding the parent node.
Eg if low[8] = 5, ie our current node is 8 and it has a path to reach back to node 5 apart from its parent. 
Since node 8 has a way to reach node 5, the edges b/w node 6-8 or 7-8 won’t be bridges
        */
        int[] toi = new int[n]; // time of insertion

        List<List<Integer>> bridges = new ArrayList<>(); // to store critical connections

        // Start DFS traversal from the first node (0)
        dfs(0, -1, 0, adjList, vis, low, toi, bridges);

        return bridges;
    }

    private void dfs(int curr, int parent, int time, List<List<Integer>> adjList, boolean[] vis, int[] low, int[] toi, List<List<Integer>> bridges) {
        vis[curr] = true;
        toi[curr] = low[curr] = time;

        // Traverse through neighbors of the current node
        for (int neighbor : adjList.get(curr)) {
            if (neighbor == parent)
                continue;

            if (!vis[neighbor]) {
                // Recursively explore unvisited neighbors
                dfs(neighbor, curr, time + 1, adjList, vis, low, toi, bridges);
                
                // Update the lowest reachable node for the current node
                low[curr] = Math.min(low[neighbor], low[curr]);

                // Check if the edge is a critical connection (bridge)
                if (low[neighbor] > toi[curr])
                    bridges.add(Arrays.asList(curr, neighbor));
            } else {
                // Update the lowest reachable node for the current node
                low[curr] = Math.min(low[neighbor], low[curr]);
            }
        }
    }
}
