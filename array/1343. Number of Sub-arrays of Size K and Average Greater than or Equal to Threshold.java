/*
1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k and average greater than or equal to threshold.

Example 1:
Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
Output: 3
Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of size 3 have averages less than 4 (the threshold).

Example 2:
Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
Output: 6
Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.

*/

=============

class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int start = 0, end = 0, count = 0, sum = 0;

        while(end < arr.length) {
            sum += arr[end];
            // if window size = k, so we dont need to increment end to k size first seperately in the beginning.
            if(end-start+1 == k) { 
                if(sum/k >= threshold)
                    count++;
                sum -= arr[start];
                start++;
            }
            end++;
        }
        return count;
    }
}

// Approach - Sliding window
// we take two pointers and calculate the sum for the window
// If window size equals k, we check if the average >= threshold, and increment count 
