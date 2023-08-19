/*
1466. Reorder Routes to Make All Paths Lead to the City Zero

There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). 
Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.


Example 1:
Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 2:
Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

*/


------------

// Approach

// T: O(connections.length), as you visit each edge only once.

// S: O(N + connections.length), n= number of cities.
// For each city, you have a list of connections, so the space required is O(N + E), where N is the number of cities and E is the number of connections.
// Recursive Stack Space: In the worst case, when the graph is a linear chain (each city has only one connection to the next city), the depth of the recursion in the DFS can reach N, where N is the number of cities. This means that the recursive stack space used by the DFS can go up to O(N) in this case.
// Overall, the space complexity is O(N + E + N), which simplifies to O(N + E), where N is the number of cities and E is the number of connections. This is because the adjacency list storage dominates the space complexity, and the recursive stack space is generally smaller than the adjacency list storage for most graphs.

class Solution {
    public int minReorder(int n, int[][] connections) {
        // Create an adjacency list to represent the graph
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());

        // Populate the adjacency list with roads and their directions
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            
            // Road from 'from' to 'to' with direction 0 (away from 'from')
            adjList.get(from).add(new int[] {to, 0});
            
            // Road from 'to' to 'from' with direction 1 (towards 'from')
            adjList.get(to).add(new int[] {from, 1});
        }
        
        // Start DFS traversal from city 0 with initial count 0
        return DFS(adjList, 0, -1, 0);
    }
    
    private int DFS(List<List<int[]>> adjList, int curr, int parent, int count) {
        for (int[] listItem : adjList.get(curr)) {
            int neighbor = listItem[0];
            int direction = listItem[1];
            
            // Skip the road leading back to the parent city
            if (neighbor != parent) {
                // If the road is going away from the current city, increment count
                if (direction == 0)
                    count++;
                
                // Continue DFS traversal recursively for the neighboring city
                count = DFS(adjList, neighbor, curr, count);
            }
        }
        return count;
    }
}
