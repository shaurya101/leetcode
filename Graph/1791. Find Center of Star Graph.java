/*
1791. Find Center of Star Graph

There is an undirected star graph consisting of n nodes labeled from 1 to n. 
A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.
You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. 
Return the center of the given star graph. 

Example 1:
Input: edges = [[1,2],[2,3],[4,2]]
Output: 2
Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.

*/

=============

// Approach 1

class Solution {
    public int findCenter(int[][] edges) {
        HashSet<Integer> set = new HashSet<>();

        for(int i=0; i<edges.length; i++) {
            // edges[i] contains two nodes which connect node edges[i][0] and node edges[i][1]
            if(set.contains(edges[i][0])) // 'edges[i][0]' is a NODE of the edge which is connected to NODE 'edges[i][1]'
                return edges[i][0];
            else
                set.add(edges[i][0]);
            
            if(set.contains(edges[i][1])) // 'edges[i][1]' is a NODE of the edge which is connected to NODE 'edges[i][0]'
                return edges[i][1];
            else
                set.add(edges[i][1]);
        }
        return 0;
    }
}

// Approach
// We know that in a star graph, only the center node will have more than one edge.
// So we use a set and traverse the edges and see if the nodes are already present in the set
// Only the star center node will be present in the edges array multiple times as all other nodes will have just one edge
