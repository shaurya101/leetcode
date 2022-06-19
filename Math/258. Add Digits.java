/*
258. Add Digits

Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

Example 1:
Input: num = 38
Output: 2
Explanation: The process is
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2 
Since 2 has only one digit, return it.

Example 2:
Input: num = 0
Output: 0
*/

--------------

class Solution {
    public int addDigits(int num) {
        int sum=num;
        
        while(sum/10 != 0) // if sum is not single digit
            sum=getSum(sum);
        return sum;
    }
    private static int getSum(int sum){
        int newSum=0;
        while(sum != 0){
            newSum+=sum%10;
            sum/=10;
        }
        return newSum;
    }
}

---------------
    
// Approach 2 - FASTER BOG O of 1 using Digital root
    
class Solution {
    public int addDigits(int num) {
         // using digital root concept. We iteratively sum the digits of a number until we get a single digit number. This is equal to num%9.
        // Just be careful of the case when number=0, as we will get 0 for other numbers divisible by 9 as well in which case we need to return 9 not 0.
        if ( num == 0 ) // if number 0 return 0
            return 0;
	    return num%9 == 0? 9: num%9;
    }
}

// O(1)
