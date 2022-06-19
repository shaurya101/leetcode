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

------------
    
// Approach 2 - IMPORTANT!
    
class Solution {
    public boolean isHappy(int n) {
        // Floyd Cycle detection algorithm
        int slowSum = n; // slow pointer. Takes one step
        int fastSum = n; // fast pointer. takes two steps
        
        while(fastSum !=1 && slowSum != 1){
            slowSum = getSum(slowSum); // get next sum
            fastSum = getSum(getSum(fastSum));// get next to next sum
            // IMPORTANT - We were able to call next to next function result by the above statement. It is correct syntax for java as well. The inner getSum call runs first and returns a value which is the parameter for the outer getSum(). 
            // We may think if what if the inner getSum returns 1 and since that value is passed to the outer getSum directly without checking if it was equal to 1; we could be miss out the csae when sum=1 and end up with incorrect solution. However if inner getSum return 1 then outer getSum will also return 1 since 1*1=1; so we are fine. But if we were checking for something else than 1, we would need to check the inner getSum result too!
        
            if(fastSum !=1 && slowSum != 1 && fastSum == slowSum)
                return false;
            // if slowSum and fastSum != 1 and they ar equal then it means there is a cycle and sum=1 will not occur.
         }
        return true;
    }
    private static int getSum(int number){
        int sum=0;
         while(number != 0){
                int x = number%10;
                sum += Math.pow(x,2);
                number/=10;
            }
        return sum;
    } 
}

// Two pointer approach is faster than HashSet approach.
