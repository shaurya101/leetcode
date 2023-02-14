/*
1523. Count Odd Numbers in an Interval Range

Given two binary strings a and b, return their sum as a binary string. 

Example 1:
Input: a = "11", b = "1"
Output: "100"

*/

===============

class Solution {
    public int countOdds(int low, int high) {
        int numCount = high-low+1;
        // if numCount is even or first number is even
        if(numCount % 2 == 0 || low %2 == 0)
            return numCount/2;

        // else if numCount is odd and first number is odd
        return numCount/2 +1;
    }
}

// Approach
// 1st case : numCount is even
// low=2, high=7; numCount = 6, so there will be 3 odd and 3 even numbers
// low=3, high=8; numCount = 6, so there will be 3 odd and 3 even numbers

// 2nd case : numCount is odd and low is even
// low=2, high=6; numCount = 5, so there will be 2 odd (3,5) and 3 even numbers(2,4,6)

// 3rd case : numCount is odd and low is odd
// low=3, high=7; numCount = 5, so there will be 3 odd (3, 5, 7) and 2 even numbers (4,6)
