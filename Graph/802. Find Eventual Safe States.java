/*
802. Find Eventual Safe States

There is a directed graph of n nodes with each node labeled from 0 to n - 1. 
The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i,
meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order. 

Example 1:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

Example 2:
Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.

*/

--------------

// Approach :  T: O(V+E), S: O(V); V=no of nodes, E=edges

import java.util.*;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n]; // Array to keep track of visited nodes
        List<Integer> ans = new ArrayList<>(); // List to store eventual safe nodes

        // Loop through each node in the graph
        for (int i = 0; i < n; i++) {
            // Check if the current node is eventually safe.
            // We will call dfs irrespective of its vis[i] status to get the safe node status
            if (dfs(graph, vis, i)) {
                ans.add(i); // If safe, add it to the result list
            }
        }

        return ans;
    }

    private boolean dfs(int[][] graph, int[] vis, int curr) {
        // If the node has already been visited, return true if it's safe, false if not
        if (vis[curr] != 0) {
            return vis[curr] == 1; // return true if no cycle found
        }

        vis[curr] = 2; // Mark the current node as being visited

        for (int neighbor : graph[curr]) {
            // If any neighbor is not safe, the current node is not safe
            if (!dfs(graph, vis, neighbor)) {
                return false;
            }
        }

        vis[curr] = 1; // Mark the current node as eventually safe
        return true; // Return true as the current node is eventually safe
    }
}


--------------

// Approach - T: O(V+E), S: O(V); V=no of nodes, E=edges


class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int[] vis = new int[graph.length];
        List<Integer> safeNodes = new ArrayList<>();

        for(int i = 0; i < graph.length; i++) {
            if(vis[i] == 0) {
                isInCycle(graph, vis, i);
            }
        }

        for(int i=0; i<graph.length; i++)
            if(vis[i] != 2)
                safeNodes.add(i);
        return safeNodes;
    }
    private boolean isInCycle(int[][] graph, int[] vis, int curr)  {
        vis[curr] = 2; // in current path, assuming this will form a cycle
        for(int neighbor : graph[curr]) {
            if(vis[neighbor] == 2)
                return true; // IMP THING TO NOTE
             // if cycle detected, then return true. There may be more neighbors and they will be not visited yet. 
             // The loop above (~line 6)where we call dfs for unvisited nodes will cover them and check for cycles in the remaining neighbors
            else if(vis[neighbor] == 0) {
                if(isInCycle(graph, vis, neighbor))
                    return true;
            }
        }
        vis[curr] = 1;
        return false;
    }
}


/* 

Time Complexity:
The main function iterates over all nodes in the graph: O(n)
For each node, the isInCycle function traverses its neighbors. However, each node is visited at most once,
so the total number of traversals across all calls is bounded by the total number of edges 'm'.
Overall time complexity: O(n + m), where 'n' is the number of nodes and 'm' is the number of edges.

Space Complexity:
Integer array vis: O(n), where 'n' is the number of nodes.
List safeNodes: O(n), as the worst case could be all nodes being safe.
Recursive call stack for isInCycle: O(n), as each node could be visited during recursion.
Overall space complexity: O(n)

*/
