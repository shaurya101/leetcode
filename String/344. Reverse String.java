/*
Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
*/


---------------------------------------------------

class Solution {
    public void reverseString(char[] s) {
        for(int i=0; i<s.length/2; i++){
            char temp = s[s.length-i-1];
            s[s.length-i-1] = s[i];
            s[i] = temp;
        }
    }
}

// Iterate from 0 to < length/2. 
// Incase it is an even length, we dont want to run for length/2 as it will swap twice. And for odd we dont need to run for length/2 as it will swap itself. So for all cases we have to rum from 0 to length/2.
