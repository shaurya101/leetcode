/*
433. Minimum Genetic Mutation

A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined
as one single character changed in the gene string.

For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.

Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate
from startGene to endGene. If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.
 

Example 1:
Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1

Example 2:
Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2

*/


-------------

// Approach: T: O(N * L^2), S: O(N)
// where N be the length of the bank (number of genes) and L be the length of each gene (fixed at 8


class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        // Create a HashSet from the bank array for faster lookup
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // If the endGene is not in the bank, it's impossible to reach it
        if (!bankSet.contains(endGene))
            return -1;

        // Create a queue to perform BFS
        Queue<String> q1 = new ArrayDeque<>();
        q1.offer(startGene); // Start with the initial gene sequence
        int steps = 0; // Initialize the steps

        // Perform BFS
        while (!q1.isEmpty()) {
            int size = q1.size();
            for (int k = 0; k < size; k++) {
                char[] currGene = q1.poll().toCharArray(); // Convert the gene string to a character array

                // Try mutating each character in the gene sequence
                for (int i = 0; i < 8; i++) {
                    char originalChar = currGene[i];
                    char[] possibleChar = {'A', 'C', 'G', 'T'};

                    for (char c : possibleChar) {
                        if (c == originalChar)
                            continue;

                        // Mutate the character
                        currGene[i] = c;
                        String newGene = String.valueOf(currGene);

                        // Check if the mutated gene is in the bank
                        if (bankSet.contains(newGene)) {
                            q1.offer(newGene); // Add the mutated gene to the queue
                            bankSet.remove(newGene); // Remove it from the bank to avoid duplicates
                        }

                        // If the mutated gene matches the endGene, return the number of steps
                        if (newGene.equals(endGene))
                            return steps + 1;
                    }

                    // Restore the original character
                    currGene[i] = originalChar;
                }
            }
            steps++; // Increment the step count after processing each level
        }

        // If no valid mutation sequence is found, return -1
        return -1;
    }
}
