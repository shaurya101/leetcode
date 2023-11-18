/*
1838. Frequency of the Most Frequent Element

The frequency of an element is the number of times it occurs in an array.

You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that
index by 1.

Return the maximum possible frequency of an element after performing at most k operations.
 

Example 1:
Input: nums = [1,2,4], k = 5
Output: 3
Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.

*/

--------------

// Approach - Sliding window
// T: O(nlogn) S: O(1)

/*
- Sort the array nums in ascending order.
- Initialize variables: maxFreq to track the maximum frequency, sum to store the sum of elements in the current window, and left pointer
  for the sliding window.
- Use a sliding window approach to iterate through the array.
- Expand the window by adding elements to the sum and shrink the window if the sum exceeds k by subtracting elements from the beginning
  of the window.
- Update maxFreq with the maximum window size encountered.
*/


class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array
        int maxFreq = 1; // Initialize the maximum frequency
        long sum = 0; // Initialize the sum of elements
        int left = 0; // Initialize the left pointer for the sliding window

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // Add the current element to the sum
            
            // Calculate the total cost to make all elements in the window equal to nums[right]
            // If the cost exceeds k, shrink the window by moving the left pointer
            while ((long) nums[right] * (right - left + 1) - sum > k) {
                sum -= nums[left]; // Subtract the left element from the sum
                left++; // Move the left pointer to the right
            }

            // Update the maximum frequency encountered so far
            maxFreq = Math.max(maxFreq, right - left + 1);
        }

        return maxFreq; // Return the maximum frequency of any element
    }
}
