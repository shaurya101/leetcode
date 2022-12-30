/*
797. All Paths From Source to Target

Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

Example 1:
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

*/

============


class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList();
        dfs(ans, new ArrayList(), graph, 0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> path, int[][] graph, int currentNode) {
        path.add(currentNode);

        if(currentNode == graph.length-1)
            ans.add(new ArrayList(path)); // we add a copy of path, as path may get altered in some other recursive cycle
        else {
            for(int neighbour : graph[currentNode])
                dfs(ans, path, graph, neighbour);
        }
        path.remove(path.size()-1); // we remove currentNode we added in the function start. This removal will be done for backtracking to prevent extra nodes in paths. When we backtrack the end to some node, the repective nodes will be deleted and recursive call will explore a new path
    }
}

// Approach
// We have a list of paths, and a path
// We use recursion and backtracking to add copies of path we validated to the answer list.
// In dfs, we first add the current node we are on. Then we see if it is the last node. If yes, we have found a valid path.
// If no, then we search for the neighbours of the current node. After they are also traversed recursively and loop reaches back here, we will remove the current node from the path to avoid extras, incase there are more paths from the bifurcating previous nodes.
