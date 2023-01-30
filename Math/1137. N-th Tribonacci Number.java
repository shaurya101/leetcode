/*
1137. N-th Tribonacci Number

The Tribonacci sequence Tn is defined as follows: 
T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.

Example 1:
Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4

*/

===============

class Solution {
    public int tribonacci(int n) {
        if(n==0)
            return 0;
        if(n==1 || n==2)
            return 1;

        int a=0, b=1, c=1; // T0=0, T1=1, T2=1
        int sum = 2; // T3
        int count = 4;

        while(count <= n) {
            a = b;
            b = c;
            c = sum;
            sum = a+b+c;
            count++;
        }
        return sum;
    }
}
