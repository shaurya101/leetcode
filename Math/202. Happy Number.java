/*
202. Happy Number

Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

Example 1:
Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

Example 2:
Input: n = 2
Output: false
*/

-------------

class Solution {
    public boolean isHappy(int n) {
        int sum = n; // common error to make sum=0. Check while loop.
        HashSet<Integer> set = new HashSet<>(); // to check if a generated sum is already generated before. If that happens then it means it has run a complete loop will continue to run in that loop of sum without giving us a sum=1
        
        while(sum != 1){
            int currentSum = sum;
            sum = 0;
            while(currentSum != 0){
                int x = currentSum%10;
                sum += Math.pow(x,2);
                currentSum/=10;
            }
            if(sum != 1 && set.contains(sum)){
                return false;}
            else
                set.add(sum);
        }
        return true;
    }
}
