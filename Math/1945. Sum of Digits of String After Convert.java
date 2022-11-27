/*
1945. Sum of Digits of String After Convert

You are given a string s consisting of lowercase English letters, and an integer k.
First, convert s into an integer by replacing each letter with its position in the alphabet (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26). Then, transform the integer by replacing it with the sum of its digits. Repeat the transform operation k times in total.
For example, if s = "zbax" and k = 2, then the resulting integer would be 8 by the following operations:
Convert: "zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
Transform #1: 262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
Transform #2: 17 ➝ 1 + 7 ➝ 8
Return the resulting integer after performing the operations described above.

Example 1:
Input: s = "iiii", k = 1
Output: 36
Explanation: The operations are as follows:
- Convert: "iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999
- Transform #1: 9999 ➝ 9 + 9 + 9 + 9 ➝ 36
Thus the resulting integer is 36.

*/

---------------

class Solution {
    public int getLucky(String s, int k) {
        int n = 0;
        
        // we do not directly convert from string to int as that might be out pf range for int
        // so we get the first sum of digits directly, note 1 transform cycle is used out of k
    for(char ch : s.toCharArray()) {
        n += digitSum(ch - 'a' + 1);
    }
        
    while(k-->1) {
        n = digitSum(n);
    }
    return n;
    }
    
    private int digitSum(int n) {
        int sum = 0;
        while(n != 0) {
            sum += n%10;
            n/=10;
        }
        return sum;
    }
}

// we store the forst sum of digits in an int from the string
// we run k-1 transformations, as 1 cycle is used in converting string to int
// we do not directly convert string to digits but as sum of digits, to keep number in int's range
