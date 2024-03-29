/*
1491. Average Salary Excluding the Minimum and Maximum Salary

You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input: salary = [4000,3000,1000,2000]
Output: 2500.00000
Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500

*/

-------------

// Approach ;  T: O(N), S: O(1)


class Solution {
    public double average(int[] salary) {
        int min = salary[0]; // store min salary
        int max = salary[0]; // store max salary
        int sum = 0;

        for(int pay : salary) {
            sum += pay;
            if(pay < min)
                min = pay;
            if(pay > max)
                max = pay;
        }

        sum -= (min+max); // subtract min and max salary
        return (double) sum/(salary.length-2);
    }
}
