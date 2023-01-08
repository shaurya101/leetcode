/*
1004. Max Consecutive Ones III

Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

*/

============

// Approach - T: O(N), S: O(1)
// We will traverse the nums with two pointers and keep substring so that it has at most k zeroes.
// We will increase the zero count, if we come across a zero.
// If zero_count>k, we will shift the begin_substring to the right, until we cross a zero and decrease the count_zero by one making it under or equal to k
// At each traversal we will calculate the max substring length

class Solution {
    public int longestOnes(int[] nums, int k) {
        int begin_substring = 0;
        int cur_index = 0; // to traverse nums
        int count_zeroes = 0; // we know that at max our substring can have a count of k zeroes
        int max = 0;

        while(cur_index < nums.length) {
            if(nums[cur_index] == 0)
                count_zeroes++;
            
            while(count_zeroes > k) {
                if(nums[begin_substring] == 0)
                    count_zeroes--;
                begin_substring++;
            }

            max = Math.max(max, cur_index-begin_substring+1);
            cur_index++;
        }
        return max;
    }
}
