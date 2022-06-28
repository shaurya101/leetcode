/*
9. Palindrome Number

Given an integer x, return true if x is palindrome integer.
An integer is a palindrome when it reads the same backward as forward.
For example, 121 is a palindrome while 123 is not.

Example 1:
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.

Example 2:
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

*/
----------------

class Solution {
    public boolean isPalindrome(int x) {
        /*if(x == 0) redundant case in code
            return true;
        */
        
        // as mentioned in question; negative numbers will not be palindrome
        // numbers ending with 0 will not be palindrome except 0 itself
        if(x < 0 || x!=0 && x%10==0) 
            return false;
        
        int palindrome = 0; 
        
        // while x has more or equal digits than palindrome
        while(x > palindrome){ 
            palindrome = palindrome*10 + x%10;
            x /= 10;
        }
        // either x==palindrome if even digits eg 5555, 1221, 1001
        // or x==palindrome/10 for odd digits eg 121, 555
        // In odd cases the middle element is extra and so one extra digit will be there in our variable palindrome
        return x==palindrome || x==palindrome/10;
    }
}
