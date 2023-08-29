/*
2483. Minimum Penalty for a Shop

You are given the customer visit log of a shop represented by a 0-indexed string customers consisting only of characters 'N' and 'Y':

if the ith character is 'Y', it means that customers come at the ith hour
whereas 'N' indicates that no customers come at the ith hour.
If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:

For every hour when the shop is open and no customers come, the penalty increases by 1.
For every hour when the shop is closed and customers come, the penalty increases by 1.
Return the earliest hour at which the shop must be closed to incur a minimum penalty.

Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.

Example 1:
Input: customers = "YYNY"
Output: 2
Explanation: 
- Closing the shop at the 0th hour incurs in 1+1+0+1 = 3 penalty.
- Closing the shop at the 1st hour incurs in 0+1+0+1 = 2 penalty.
- Closing the shop at the 2nd hour incurs in 0+0+0+1 = 1 penalty.
- Closing the shop at the 3rd hour incurs in 0+0+1+1 = 2 penalty.
- Closing the shop at the 4th hour incurs in 0+0+1+0 = 1 penalty.
Closing the shop at 2nd or 4th hour gives a minimum penalty. Since 2 is earlier, the optimal closing time is 2.

Example 2:
Input: customers = "NNNNN"
Output: 0
Explanation: It is best to close the shop at the 0th hour as no customers arrive.

*/


---------

// Approach - T: O(N), S:O(N)


class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int[] prefix = new int[n+1]; // To store the count of 'N' characters before each position (in prefix we exclude the ith value in ith cell)
        int[] postfix = new int[n+1]; // To store the count of 'Y' characters after each position (unlike prefix, in postfix we include the ith value in ith cell)

        // Calculate prefix counts
        for(int i = 1; i <= n; i++) {
            prefix[i] = prefix[i-1]; // Initialize prefix count with the previous value
            if(customers.charAt(i-1) == 'N') // If no customer arrives at previous hour ie suming up 'N's upto i-1 (since we exclude ith value in ith cell in prefix)
                prefix[i]++; // Increment the prefix count
        }
        
        // Calculate postfix counts
        for(int i = n-1; i >= 0; i--) {
            postfix[i] = postfix[i+1]; // Initialize postfix count with the i+1th value, we are counting from back
            if(customers.charAt(i) == 'Y') // If the shop is open and there's a customer at this hour
                postfix[i]++; // Increment the postfix count
        }

        int minPenalty = Integer.MAX_VALUE; // To store the minimum penalty
        int index = -1; // To store the index of the best closing time

        // Loop through all possible closing times
        for(int i = 0; i <= n; i++) {
            // Calculate the total penalty for the current closing time
            int totalPenalty = prefix[i] + postfix[i];

            // Update minPenalty and index if the current totalPenalty is less than the current minimum
            if(totalPenalty < minPenalty) {
                minPenalty = totalPenalty;
                index = i;
            }
        }
        
        return index; // Return the index of the best closing time
    }
}
