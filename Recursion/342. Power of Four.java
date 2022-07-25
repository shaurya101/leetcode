/*
342. Power of Four

Given an integer n, return true if it is a power of four. Otherwise, return false.
An integer n is a power of four, if there exists an integer x such that n == 4x.

Example 1:
Input: n = 16
Output: true

Example 2:
Input: n = 5
Output: false

*/

------------------
  
class Solution {
    public boolean isPowerOfFour(int n) {
        if(n == 1)
            return true;
        if(n%4 != 0 || n == 0) // if n is not divisible by 4, it cannot be a power of 4
            return false;
        return isPowerOfFour(n/4);
    }
}

// remember corner case 0
