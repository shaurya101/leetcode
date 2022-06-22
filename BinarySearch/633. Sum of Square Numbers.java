/*
633. Sum of Square Numbers

Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5

Example 2:
Input: c = 3
Output: false
*/
------------------

class Solution {
    public boolean judgeSquareSum(int c) {
        if(c==0)
            return true;
        if(isPerfectSquare(c)) // since a*a + 0*0 = c
            // optimisation incase c is a perfect square we dont need to iterate and check it from 1 to root(c) in while loop.
            return true;
        int a=1; 
        while(a<=c/a){ // NOTE: a cannot be 0 as c/0 would give error
            int b = c-a*a;
            if(isPerfectSquare(b))
                return true;
            a++;
        }
        return false;
    }
    private static boolean isPerfectSquare(int b){
        /*
        if(b==0) since a*a + 0*0 = c is valid; no need to cheack for 0 as we are checking for perfect square before iterating. If we remove the perfect square check then we will need this check
            return true; 
        */
        int low=1;
        int high=b;
        
        while(low<=high){
            int mid = low+(high-low)/2;
            
            if(mid==b/mid && b%mid==0) //if perfect square
                return true;
            if(mid<=b/mid)
                low=mid+1;
            else
                high=mid-1;
        }
        return false;
    }
}

// Given : c=a*a + b*b;
// Our iterator i will be a so
// c = i*i + b*b
// b*b = c - i*i
// we will iterate from 1(i) and subtract i*i from c; If the result is a perfect square then we found our b 
// Iterate from 1 to value where c-i*i<0 or i*i>c
// to avoid index overflow

 
