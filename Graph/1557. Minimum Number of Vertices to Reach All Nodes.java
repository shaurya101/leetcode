/*
1557. Minimum Number of Vertices to Reach All Nodes

Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] 
represents a directed edge from node fromi to node toi.

Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.

Notice that you can return the vertices in any order.


Example 1:
Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
Output: [0,3]
Explanation: It's not possible to reach all the nodes from a single vertex. From 0 we can reach [0,1,2,5]. From 3 we can reach [3,4,2,5]. So we output [0,3].

*/


------------

// Approach - T: O(N), S: O(N)

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // Create an array to store the in-degrees of vertices.
        int[] indegree = new int[n];
        
        // Calculate in-degrees by iterating through the edges.
        for (List<Integer> edge : edges) {
            int destination = edge.get(1); // Get the destination vertex of the edge.
            indegree[destination]++; // Increment the in-degree of the destination vertex.
        }
        
        // Create a list to store the vertices with in-degree 0.
        List<Integer> ans = new ArrayList<>();
        
        // Iterate through all vertices and add those with in-degree 0 to the list.
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                ans.add(i); // Vertex with in-degree 0 is added to the result list.
            }
        }
        
        return ans; // Return the list of vertices with in-degree 0.
    }
}
