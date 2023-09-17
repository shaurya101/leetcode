/*
997. Find the Town Judge

In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
If a trust relationship does not exist in trust array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.


Example 1:
Input: n = 2, trust = [[1,2]]
Output: 2

Example 2:
Input: n = 3, trust = [[1,3],[2,3]]
Output: 3

*/


----------------

// Approach - T: O(E), S: O(N), E is number of trust relationships, n is number of people

class Solution {
    public int findJudge(int n, int[][] trust) {
        // Create an array to represent the trust level of each person.
        int[] trustMeter = new int[n];

        // Iterate through the trust relationships.
        for (int[] trustEdge : trust) {
            // Decrease the trust level of the person making the trust (trustEdge[0] - 1).
            // Increase the trust level of the person being trusted (trustEdge[1] - 1).
            trustMeter[trustEdge[0] - 1]--; // else it will fail such cases where judge doesnt exists [[1,3],[2,3],[3,1]]
            trustMeter[trustEdge[1] - 1]++;
        }

        // Iterate through the trustMeter array to find a person who is trusted by exactly n - 1 others.
        for (int i = 0; i < n; i++) {
            if (trustMeter[i] == n - 1) {
                // Return the person (add 1 to get the actual person number).
                return i + 1;
            }
        }

        // If no judge is found, return -1.
        return -1;
    }
}
