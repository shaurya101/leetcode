/*
547. Number of Provinces

There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.
You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
Return the total number of provinces.

Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

*/


------------------
  
// Approach 1- T: O(n+e), S: O(n+e)
// Time Complexity: The time complexity of this approach is O(n + e), where n is the number of cities and e is the number of edges (connections) in the graph
// Building the adjacency list takes O(n^2) time in the worst case. The DFS traversal takes O(n + e) time, as we visit each city once and explore all its connected cities.
  
class Solution {
    public int findCircleNum(int[][] isConnected) {
         int n = isConnected.length;
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int count = 0;
        
        // Create adjacency list from the given matrix
        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    neighbors.add(j);
                }
            }
            graph.add(neighbors);
        }
        
        // Perform DFS on each unvisited city
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited);
                count++;
            }
        }
        
        return count;
    }
    
    private void dfs(List<List<Integer>> graph, int curr, boolean[] visited) {
        visited[curr] = true;
        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                dfs(graph, next, visited);
            }
        }
    }
}

------------------
  
// Approach 2- T: O(n^2) as in worst case we do dfs for each cell in matrix
//             S: O(n) + O(n); visited array + dfs recursion stack


class Solution {
    public int findCircleNum(int[][] isConnected) {
        int count=0;
        // Keep track of visited cities
        boolean[] visited = new boolean[isConnected.length];

        for(int i=0; i<isConnected.length; i++) {
            if(!visited[i]) {
                // Perform Depth-First Search on unvisited cities
                dfs(isConnected, visited, i);
                // Increment count for each connected province
                count++;
            }
        }
        return count;
    }
    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true; // Mark the current city as visited

        for(int neighbour=0; neighbour<isConnected.length; neighbour++) {
            if(!visited[neighbour] && isConnected[city][neighbour] == 1)
                // Recursive DFS call for unvisited neighbors
                dfs(isConnected, visited, neighbour);
        }
    }
}
