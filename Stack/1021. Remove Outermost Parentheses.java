/*
1021. Remove Outermost Parentheses

A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.
For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into s = A + B, with A and B nonempty valid parentheses strings.
Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.
Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.

Example 1:
Input: s = "(()())(())"
Output: "()()()"
Explanation: 
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".

Example 2:
Input: s = "(()())(())(()(()))"
Output: "()()()()(())"
Explanation: 
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".

Example 3:
Input: s = "()()"
Output: ""
Explanation: 
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".

*/
-------------

class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int counter=0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                if(counter > 0) // if it is not first '('
                    sb.append('(');
                counter++;
            }
            if(s.charAt(i) == ')'){
                counter--;
                if(counter > 0) // if it is not last ')'
                    sb.append(')');
            }
        }
        return sb.toString();
    }
}

// Approach
// Since it is a valid paranthesis string as mentioned in constraints, we do not need a stack. If it was not given, this would require a stack.
// we can simply keep a track of the sum of () closed, assuming +1 for '(' and -1 for ')'
// if it is 0 and we are entering '(', it means it is the first '(' 
// if it is 1 and we are deleting ')', it is the last ')'
