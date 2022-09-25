/*

20. Valid Parentheses

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false
*/

---------------

class Solution {
    public boolean isValid(String s) {
        localStack s1 = new localStack();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '[')
                s1.push(c);
            else if(c == ')' && s1.peek() == '(' ||
                    c == '}' && s1.peek() == '{' ||
                    c == ']' && s1.peek() == '[')
                s1.pop();
            else
                return false;
        }

        if(s1.isEmpty())
            return true;
        return false;   
    }
    
    class Node {
        char val;
        Node next;
        
        public Node(char val) {
            this.val = val;
            this.next = null;
        }
    }
    class localStack {
        Node top;
        
        public localStack() {
            this.top = null;
        }
        
        boolean isEmpty() {
            if(top == null)
                return true;
            return false;
        }
        boolean isFull() {
            Node ptr = new Node('-');
            if(ptr == null)
                return true;
            return false;
        }
        void push(char val) {
            if(!isFull()) {
                Node ptr = new Node(val);
                if(!(top == null))
                    ptr.next = top;
                top = ptr;
            }
        }
        char peek() {
            if(!isEmpty())
                return top.val;
            return '-';
        }
        char pop() {
            if(!isEmpty()) {
                char c = peek();
                top=top.next;
                return c;
            }
            return '-';
        }
    }
}
