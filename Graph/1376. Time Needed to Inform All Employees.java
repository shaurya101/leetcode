/* 1376. Time Needed to Inform All Employees

A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.
Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree structure.
The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.
The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).

Return the number of minutes needed to inform all the employees about the urgent news.

Example 1:
Input: n = 1, headID = 0, manager = [-1], informTime = [0]
Output: 0
Explanation: The head of the company is the only employee in the company.

*/


-----------------

// Approach - T: O(n), S: O(n)

class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++)
            adjList.add(new ArrayList<>());
        for(int i=0; i<n; i++)
            if (manager[i] != -1) 
                adjList.get(manager[i]).add(i);

        return DFS(adjList, informTime, manager, headID);
    }

    private int DFS(List<List<Integer>> adjList, int[] informTime, int[] manager, int curr) {
        int maxTime = 0;
        for(int neighbor : adjList.get(curr)) {
            if(neighbor != manager[curr]) {
                int time = DFS(adjList, informTime, manager, neighbor);
                if(time > maxTime)
                    maxTime = time;
            }
        }
        return maxTime+informTime[curr];
    }
}

// T: O(n) - In the worst case, the DFS will visit every employee exactly once. Since there are 'n' employees in the organization, 
// the time complexity of your DFS traversal will be O(n) and adjacency list takes O(n) time, as you're looping through the 'n' employees 
// and adding their IDs to the adjacency list. Overall, the time complexity of your numOfMinutes function is O(n) for the DFS traversal 
// and O(n) for creating the adjacency list. Thus, the total time complexity remains O(n).

// S: O(n) - In the worst case, if the organization is a straight chain (linear hierarchy), the maximum depth of the call stack would 
// be 'n'. Hence, the space complexity due to the call stack is O(n).

// why we dont need a visited array - the way you're traversing the hierarchy inherently ensure that you won't encounter any cycles
// while traversing from the head of the organization to the employees. The implementation of traversal is inherently cycle-free.
