/*

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

 
Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

*/



class Solution {
    public boolean isPalindrome(String s) {
        if(s.equals("")) // s=="" will be false as this checks memory location
            return true;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
                sb.append(s.charAt(i));
            if(s.charAt(i) >= 97 && s.charAt(i) <= 122)
                sb.append(s.charAt(i)); // using a normal string and doing normal string concatenation would be inefficient as it would produce lots of String objects needlessly. Here StringBuilder would be more efficient and we can use append.
            if(s.charAt(i) >= 65 && s.charAt(i) <= 90)
                sb.append((char)(s.charAt(i)+32));
        }
        int i=0;
        int j = sb.length()-1;
        while(i<=j){
            if(sb.charAt(i) != sb.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}

// if empty string return true
// travserse the string and store only letters (convert to lowercase if case is uppercase)
// check if string palin == string.reverse().  
