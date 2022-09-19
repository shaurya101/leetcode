/*

13. Roman to Integer

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.

*/

-------------------



class Solution {
    public int romanToInt(String s) {
         // roman numeral digits is written from largest to smallest
        // except in cases where subtraction occurs
        // using this to form the number via extracting a digit one by one from left and finding the sum of all
        
        int sum=0;
        int prevDigit=0;
        
        for(int i=0; i<s.length(); i++) {
            int digit = convert(s.charAt(i));
            
            if(digit>prevDigit) {
                 // subtracting it out from sum as it must have been added in else step in previous run of loop
                sum-=prevDigit;
                // forming the proper num to add
                sum = sum + digit - prevDigit;  
            }
            else {
                sum += digit;
            }
            prevDigit = digit;
        }
        return sum;
        
    }
    
    int convert(char c) {
        switch(c) {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
        }
        throw new Error("Illegal"); // or return 0; this return statement will never run for the contraints of the question
    }
}
