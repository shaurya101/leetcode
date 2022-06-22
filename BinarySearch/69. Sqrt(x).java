/*
69. Sqrt(x)

Given a non-negative integer x, compute and return the square root of x.
Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.

Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.

Example 1:
Input: x = 4
Output: 2

Example 2:
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
*/

-----

class Solution {
    public int mySqrt(int x) {
        if(x == 0)
            return 0;
        
        int root=1;
        int low=1;
        int high = x;
        
        while(low<=high){ // include the = as even if one node remaining ie low=high it should be checked. Otherwise x=5 would fail for example
            int mid = low+(high-low)/2; // avoid integer overflow
            if(mid == x/mid) // avoid integer overflow. If equal number found then return as it will be the root
                return mid;
            if(mid <= x/mid){ 
                root = mid;
                low = mid+1;
            }
            else // if mid*mid>x then update high.
                high = mid-1;
        }
        return root;
        
    }
}

// we are checking for the largest mid which satisfies mid*mid<=x. To avoid integer overflow we write it as mid<=x/mid. But it is the same thing essentially.
// we store the root in a variable 'root'. We update root for the values of mid which satisfy the condition mid*mid<=x.
// if mid*mid>x; it cannot be root.
// if mid*mid==x, we return that value as it is a perfect root. eg 3 for x=9
// If we have mid*mid<=x, we update the root and change the low to mid+1. Now in the next loop, our new mid will be greater than old loop and we will check if new mid satisfies the condition. If yes update root since we are trying to find the largest value of mid such that mid*mid<=x.
// Latest root is retruned at end of while loop.
