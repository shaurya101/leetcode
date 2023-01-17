/*
926. Flip String to Monotone Increasing

A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).
You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.

Return the minimum number of flips to make s monotone increasing.

Example 1:
Input: s = "00110"
Output: 1
Explanation: We flip the last digit to get 00111.

Example 2:
Input: s = "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.

*/

=========

// Approach - https://www.youtube.com/watch?v=-vZ7LjWSWzY
// We will keep a track of number of ones as these are the potential flips to maintain monotonic order
// We traverse the array and if c == 1, increment num_ones
// Else if it is 0, and num_ones>0, we will increment our totalFlip and decrement num_ones by 1. Basicaly we would either have flipped a 0 into 1, or a 1 into 0 to maintain monotonic order
// We do not need to track what is replaced by what. The net effect would be that monotonic order is maintained


class Solution {
    public int minFlipsMonoIncr(String s) {
        int totalFlip = 0;
        int num_ones = 0; // number of one's which is the number of potential flips

        for(char c : s.toCharArray()) {
            if(c == '1')
                num_ones++;

            // if c != 1, then it is 0 and if num_ones>0
            else if(num_ones > 0) { 
                totalFlip++; // since num_ones>0, and our c=0, there is a 0 after a 1, so there will need to be a flip to maintain order
                num_ones--; // decrease possible flips by one
            }
        }
        return totalFlip;
    }
}
