/*
1443. Minimum Time to Collect All Apples in a Tree

Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 
Example 1:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8 
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  

Example 2:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.

*/

===============


class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        // create an adjacency list to represent a graph/tree
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++)
            adjList.add(new ArrayList<>()); // adding 'n' ArrayLists in adjList

        // edges[] contain many edge. An edge connects nodes edge[0] and edge[1].
        // As our graph is undirected, for every edge between the nodes edge[0] and edge[1], we will add edge[0] in edge[1]'s adjList and edge[1] will be added to edge[0]'s adjList
        for(int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        return minTimeToCollectApples(adjList, 0, 0, hasApple);
    }

    private int minTimeToCollectApples(List<List<Integer>> adjList, int currentNode, int parentNode, List<Boolean> hasApple) {
        // variable to keep track of total time
        int total = 0;


        // for each neighbor of current node, it is not necessarily a binary tree
        for(int neighbour : adjList.get(currentNode)) {
            // a graph will have one edge that points back to it's parent if we look it as a tree
            // Since we are looking at this graph in tree like fashion, we only want to traverse the neighbours(or child) of currentNode that aren't its parent node
            if(neighbour == parentNode)
                continue;
            total += minTimeToCollectApples(adjList, neighbour, currentNode, hasApple);
        }
        // if current node != 0 || either current node has an apple or its subtree has an apple
        if(currentNode != 0 && (hasApple.get(currentNode) || total>0))
            return total+2;
        return total;
    }
}

// Approach
// Since a tree is mentioned, our graph does not have any cycle (we do not need to keep track of visited nodes)
// First convert the edges given into a graph of nodes
