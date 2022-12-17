/*
150. Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
Note that division between two integers should truncate toward zero.
It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.

Example 1:
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6

*/

-----------

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s1 = new Stack();
        for(int i=0; i<tokens.length; i++) {
            if(tokens[i].equals("+")) {  
                int x = s1.pop() + s1.pop();
                s1.push(x); // we compute the ans and push it back into stack
            }
            else if(tokens[i].equals("-")) {
                int x = -s1.pop() + s1.pop();
                s1.push(x);
            }
            else if(tokens[i].equals("*")) {
                int x = s1.pop() * s1.pop();
                s1.push(x); 
            }
            else if(tokens[i].equals("/")) {
                int a = s1.pop();
                int x = s1.pop()/a;
                s1.push(x); 
            }
            else
                s1.push(Integer.parseInt(tokens[i]));
        }
        return s1.pop(); // return final answer
    }
}
// we will push the numbers in the stack
// if we encounter any operators(+,-,*,/) we will pop two elements and compute and push the result back into stack
// Continue till the array is traversed
// In the end the last element in the stack will the final answer
