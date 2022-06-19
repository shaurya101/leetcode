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
