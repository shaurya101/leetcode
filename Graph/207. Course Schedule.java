/*
207. Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

*/

------------

// Approach : T: O()V+E, S : O(V+E)

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the directed graph
        List<List<Integer>> adjList = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            adjList.add(new ArrayList<>());

        // Create an array to track the in-degrees of each course
        int[] inDegree = new int[numCourses];
        
        // Populate the adjacency list and in-degree array based on prerequisites
        for (int[] curr : prerequisites) {
            adjList.get(curr[1]).add(curr[0]); // Edge from prerequisite to course
            inDegree[curr[0]]++; // Increment in-degree of the course
        }

        // Create a queue for topological sorting
        Queue<Integer> q1 = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                q1.offer(i); // Enqueue courses with in-degree 0
        }
        
        int count = 0; // Counter to track the number of courses processed
        
        // Perform topological sorting using BFS
        while (!q1.isEmpty()) {
            int curr = q1.poll(); // Dequeue a course
            count++;

            // Reduce the in-degree of neighbors and enqueue them if in-degree becomes 0
            for (int neighbor : adjList.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0)
                    q1.offer(neighbor);
            }
        }

        // If all courses have been processed, it's possible to finish them
        if (count == numCourses)
            return true;
        return false;
    }
}
