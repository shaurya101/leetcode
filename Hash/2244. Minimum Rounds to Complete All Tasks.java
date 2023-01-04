/*
2244. Minimum Rounds to Complete All Tasks

You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task. In each round, you can complete either 2 or 3 tasks of the same difficulty level.
Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.

Example 1:
Input: tasks = [2,2,3,3,2,4,4,4,4,4]
Output: 4
Explanation: To complete all the tasks, a possible plan is:
- In the first round, you complete 3 tasks of difficulty level 2. 
- In the second round, you complete 2 tasks of difficulty level 3. 
- In the third round, you complete 3 tasks of difficulty level 4. 
- In the fourth round, you complete 2 tasks of difficulty level 4.  
It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.

Example 2:
Input: tasks = [2,3,3]
Output: -1
Explanation: There is only 1 task of difficulty level 2, but in each round, you can only complete either 2 or 3 tasks of the same difficulty level. Hence, you cannot complete all the tasks, and the answer is -1.

*/

=============


class Solution {
    public int minimumRounds(int[] tasks) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // storing tasks and their count of tasks in map
        for(int t : tasks) 
            map.put(t, map.getOrDefault(t, 0)+1);

        int count = 0; // number of steps
        for(int taskCount : map.values()) {
            if(taskCount == 1)
                return -1;
            count += (taskCount+2)/3; // to get ceil value, we do (taskCount+2) and then divide by 3
        }
        return count;
    }
}

// Approach
// If tasks count are the following, our number of round are as follows if tasks can be completed in steps of 2 or 3 only
// no of tasks -> steps added
// 1 -> 0*      *return -1
// 2 -> 1 (2)
// 3 -> 1 (3)
// 4 -> 2 (2,2)
// 5 -> 2 (2,3)
// 6 -> 2 (3,3)
// 7 -> 3 (2,2,3)
// 8 -> 3 (2,3,3)
// 9 -> 3 (3,3,3)

// Here we observe we can complete the rounds unless only 1 task exists
// We see that the number of steps to complete a task which occurs 'x' times is ceil(x/3)
// We get the ceil value for x/3 by doing (x+2)/3
