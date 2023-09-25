/*
1129. Shortest Path with Alternating Colors

You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.

Example 1:
Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]

Example 2:
Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]

*/


--------------

// Approach
// T : O(V+E), where V is the number of nodes (vertices) in the graph, and E is the total number of edges. 
// The BFS traversal visits each node and its neighboring edges exactly once, resulting in a linear time complexity with respect to the number of nodes and edges in the graph.
// S : O(V+E), adjList, q1, vis[][]

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        answer[0]=0;

        List<List<int[]>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++)
            adjList.add(new ArrayList<>());
        
        // prep adjacency list
        for(int[] red : redEdges)
            adjList.get(red[0]).add(new int[]{red[1], 0}); // {targetNode, color}
        for(int[] blue : blueEdges)
            adjList.get(blue[0]).add(new int[]{blue[1],1});

        Queue<int[]> q1 = new LinkedList<>();
        boolean[][] visited = new boolean[n][2]; // visited[node, color]
        q1.add(new int[]{0, 0, -1}); // {currentPos, distance, color}
        while(!q1.isEmpty()) {
            int[] curr = q1.poll();
            List<int[]> neighbours = adjList.get(curr[0]); // storing neighbours of curr node
            for(int[] neighbor : neighbours) {
                // if not visited and colors are different
                if(!visited[neighbor[0]][neighbor[1]] && neighbor[1]!=curr[2]) {
                    // if previously not visited else it will overwrite shortest distance
                    if(answer[neighbor[0]] == -1) {
                        answer[neighbor[0]] = curr[1]+1;
                    }
                    visited[neighbor[0]][neighbor[1]] = true;
                    q1.add(new int[] {neighbor[0], curr[1]+1, neighbor[1]});
                }
            }
        }
        return answer;
    }
}

------------------------

// Approach 2 - T: O(V+E), S: O(V+E)
// Deleting visited edges of particular color instead of using a visited array


class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // Create adjacency lists for red and blue edges
        List<List<Integer>> redAdjList = new ArrayList<>();
        List<List<Integer>> blueAdjList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            redAdjList.add(new ArrayList<>());
            blueAdjList.add(new ArrayList<>());
        }
        
        // Populate adjacency lists with edges
        for(int[] red : redEdges)
            redAdjList.get(red[0]).add(red[1]);
        for(int[] blue : blueEdges)
            blueAdjList.get(blue[0]).add(blue[1]);

        // Initialize the answer array with -1
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;

        // Create a queue for BFS
        Queue<int[]> q1 = new ArrayDeque<>();
        q1.offer(new int[] {0, 1, -1}); // Start with red color
        q1.offer(new int[] {0, 1, 1});  // Start with blue color
        
        while(!q1.isEmpty()) {
            int[] curr = q1.poll();
            int steps = curr[1];
            int color = curr[2];
            List<Integer> neighbors;
            
            if(color == -1) { // Red color, so we need blue neighbors
                neighbors = blueAdjList.get(curr[0]);
            } else { // Blue color, so we need red neighbors
                neighbors = redAdjList.get(curr[0]);
            }
            
            for(int neighbor : neighbors) {
                if(ans[neighbor] == -1) {
                    ans[neighbor] = steps;
                }
                q1.offer(new int[] {neighbor, steps + 1, color * (-1)});
            }
            
            neighbors.clear(); // Deleting already visited edges of a particular color to avoid TLE
        }
        
        return ans;
    } 
}
