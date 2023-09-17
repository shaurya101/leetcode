/*
785. Is Graph Bipartite?

There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.

 

Example 1:
Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in
the other.

Example 2:
Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.

*/


-------------

// Approach 1 - BFS
// T: O(V + E)
// S: O(V)


class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !bfs(graph, i, colors)) {
                return false;
            }
        }
        
        return true;
    }
    private boolean bfs(int[][] graph, int start, int[] colors) {
        Queue<Integer> q1 = new ArrayDeque<>();
        q1.offer(start);
        colors[start] = 1;

        while(!q1.isEmpty()) {
            int curr = q1.poll();

            for(int neighbor : graph[curr]) {
                if(colors[neighbor] == colors[curr])
                    return false;
                if (colors[neighbor] == 0) {
                    colors[neighbor] = -colors[curr]; 
                    q1.offer(neighbor);
                }
            }
        }
        return true;
    }
}


----------------

// Approach 2 - DFS


class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // To store the color of each node

        for (int i = 0; i < n; i++) {
            // If the node is uncolored and not part of a valid bipartite component, color it
            if (colors[i] == 0 && !dfs(graph, colors, 1, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }

        colors[node] = color;

        for (int neighbor : graph[node]) {
            if (!dfs(graph, colors, -color, neighbor)) {
                return false;
            }
        }

        return true;
    }
}

