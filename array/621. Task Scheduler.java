/*
621. Task Scheduler

Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
Return the least number of units of times that the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.

Example 2:
Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.

*/

-----------------

// Approach - T:  O(tasks.length), S: O(1)

import java.util.Arrays;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        
        Arrays.sort(freq);
        
        int maxFreq = freq[25];
        int jobsWithMaxFreq = 0;
        for (int frequency : freq) {
            if (frequency == maxFreq) {
                jobsWithMaxFreq++;
            }
        }
        
        int intervalsNeeded = (maxFreq - 1) * (n + 1) + jobsWithMaxFreq;
        return Math.max(intervalsNeeded, tasks.length);
    }
}


// https://medium.com/@satyem77/task-scheduler-leetcode-39d579f3440

// Time complexity
// Counting the frequency of each task: O(t), where t is the number of tasks.
// Sorting the frequency array: O(26 * log 26) = O(1), since there are a constant number of characters (26).
// Iterating through the frequency array to count jobs with max frequency: O(26) = O(1), constant time.
// Calculating intervals needed: O(1), constant time.
// Overall, the time complexity is O(t + 1 + 1 + 1) = O(t).

// Logic
/*
The trick here is that scheduler doesn’t run the same job again until n units have passed since it ran the same job. So, if we think which job has the most impact of this restriction, we will naturally get the answer that most frequent job will have most impact.
Now let’s see what it will take to schedule most frequent job and forget about others. Let’s take a sample: [A, A, A, B, B, C] and cool down time is n = 2. The way to schedule just A would be below, where blank space is idle unit.
A _ _ A _ _ A. We need 2 idle units and 1 unit for A for each (f-1) A, where f is frequency of A + another 1 unit for last A. So, we found a formula to find number of units needed for most frequent job: (n+1)* (f-1) + 1.
Now let’s say there are two elements which are most frequent [A, A, A, B, B, B, C] and n=2. Now we have 3 Bs as well. As you can see that we can just fit the B in existing empty space. And the only extra unit will be needed for last B which is placed at the end next to last A. So, if there are two most frequent element the answer would be: (n+1)*(f-1) + 2.
Generalizing this if we have X number of most frequent jobs then answer would be (n+1)*(f-1) + X.
So, these are the number of units needed for most frequent jobs, how about the other jobs that need to be scheduled which are not most frequent. We have to schedule those jobs as well, right. Since, we have some empty space in in each block, we can just place the new jobs in those spaces. And since there frequency is less than they will fit in empty space and won’t go beyond last space.
Take an example: [A, A, A, B, B, C] can be scheduled as below (Bold ones are the job that is put on empty spaces) A B C A B__ A.
In above example we are able to schedule other job in the same empty space and didn’t need a new unit. What if we have one more C. That can be fit in another empty space.
But what if we have two other jobs D and E. Yes, this needs a bit of thinking. If after scheduling most frequent ones and then filling the empty space with other jobs, there are jobs left then we can always extend the block with extra space. Let’s see for example [A, A, A, B, B, C, C, D, E]
After scheduling most frequent one and then filling B and C, scheduling will look like A B C A B C A. To put D and E we will extend it like below.
A B C _ A B C_ A
And put D and E on those empty space. A B C D A B C E A
Which means that either after scheduling we have some empty spaces left OR all empty spaces are filled and some other empty spaces are created and filled. So how many units are needed for later case: Easy. As many units as there are jobs because there are no empty spaces.
So final answer is: max ( (n+1)*(f-1) + X, TotalJobs)
*/
