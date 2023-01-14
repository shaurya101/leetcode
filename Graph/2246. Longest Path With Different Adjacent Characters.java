/*
2246. Longest Path With Different Adjacent Characters

You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
You are also given a string s of length n, where s[i] is the character assigned to node i.
Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.

Example 1:
Input: parent = [-1,0,0,1,1,2], s = "abacbe"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
It can be proven that there is no longer path that satisfies the conditions.

Example 2:
Input: parent = [-1,0,0,0], s = "aabc"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.

*/

============

class Solution {
    public int longestPath(int[] parent, String s) {
        // creating an adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<parent.length; i++)
            adjList.add(new ArrayList<>());

        // adding nodes in the adjList
        for(int i=1; i<parent.length; i++) { // parent[0] = -1; so we start with i=1
            adjList.get(i).add(parent[i]);
            adjList.get(parent[i]).add(i);
        }

        return maxPathLength(adjList, -1, 0, s).getMax();
    }

    private NodeInfo maxPathLength(List<List<Integer>> adjList, int parent, int currentNode, String s) {
        int maxThroughChild = 0; // maxThroughChildSubtreeTillChild
        int secondMaxThroughChild = 0; // seccondMaxThroughChildSubtreeTillChild
        int overallMax = 1; // max length, starting from length one for one node (currentNode)
        
        // find the max path length of currentNode's childrens(ie neighbours except parent)
        // once getting that after for loop, we will see if including current Node and maxThroughChild and secondMaxThroughChild, we can get a longer path
        for(int neighbour : adjList.get(currentNode)) {
            if(neighbour == parent)
                continue;
            NodeInfo childNodeInfo =  maxPathLength(adjList, currentNode, neighbour, s);
            // storing the overall max path length of children till child node's
            overallMax = Math.max(overallMax, childNodeInfo.getMax());
            // if the currentNode's and child's node val is not same, then we can update and later combine maxThroughChild and currentNode and secondMaxThroughChild to compare with overallMax
            if(s.charAt(currentNode) != s.charAt(neighbour)) {
                if(childNodeInfo.getMaxThroughRoot() > maxThroughChild) {
                    secondMaxThroughChild = maxThroughChild;
                    maxThroughChild = childNodeInfo.getMaxThroughRoot();
                }
                else if(childNodeInfo.getMaxThroughRoot()>secondMaxThroughChild )
                    secondMaxThroughChild = childNodeInfo.getMaxThroughRoot();
            }
        }// for
        overallMax = Math.max(overallMax, maxThroughChild+secondMaxThroughChild+1);
            int maxThroughRoot = 1+maxThroughChild; // we do not go downwards into secondMaxThroughChild as we want to go up to currentNode

        return new NodeInfo(overallMax, maxThroughRoot);
    }

    // we want our maxPathLength() to return max and the maxThroughRoot so we return an object of this class from that function
    private static class NodeInfo {
        int max;
        int maxThroughRoot;

        NodeInfo(int max, int maxThroughRoot) {
            this.max = max;
            this.maxThroughRoot = maxThroughRoot;
        }
        int getMax() {
            return max;
        }
        int getMaxThroughRoot() {
            return maxThroughRoot;
        }
    }
}

// Approach - https://www.youtube.com/watch?v=8u-7JxwYldc
// Note edge cases that max path could involve moving up from left subtree to a root and then going down right subtree
// And max path may not even involve root node, could be in a subtree
// We first convert the parent[] into an adjacency List
// Then we use dfs to find max path length

// Then we have three variables - 
// int overallMax - max length, this could be anywhere in the tree. Doesn't necessarily has to include the current node as well; as when the max length could be present in the subtree. Note overall length could be from a subtree till a node and then downwards into its other subtree.
// int maxThroughChild = 0; (read maxThroughChildSubtreeTillChild); max path length from subtree of child of currentNode till child of currentNode. Example if left subtree of child has max length through root, we do not traverse again back downwards into the right subtree of child even if it has different values, as we want to move it upwards to currentNode. 
// int secondMaxThroughChild = 0; seccond max path length from subtree of child of currentNode till child of currentNode
// Purpose of these two variables is combining maxThroughChild and currentNode and secondMaxThroughChild
