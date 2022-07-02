/*
844. Backspace String Compare

Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
Note that after backspacing an empty text, the text will continue empty.

Example 1:
Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".

Example 2:
Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".

Example 3:
Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".

*/
-----------------

class Solution {
    public boolean backspaceCompare(String s, String t) {
        Stack s1 = Stackify(s); 
        Stack t1 = Stackify(t);
        
        // if stack sizes are not equal after Stackify, it means strings are different
        if(s1.size() != t1.size())
            return false;
            
 
        int size = s1.size();
        // NOTE: please do not use s1.size() as test condition in for loop as size will decrease due to popping at every iteration and entire stack will not get traversed
        // pop out char from stack and check if the popped chars are equal
        for(int i=0; i<size; i++){
            if(s1.pop() != t1.pop())
                return false;
        }
        
        return true;
    }
    
    public Stack Stackify(String a){
        Stack stack = new Stack();
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) == '#'){ //NOTE: use braces otherwise the below else will be considered 'else' for the second 'if'
                if(!stack.isEmpty())
                    stack.pop();
            }
            else
                stack.push(a.charAt(i));
        }// string traversed now
        return stack;
    }
    
    
    // Code implementing Stack
    class Node{
        char val;
        Node next;
        public Node(char val){
            this.val = val;
        }
    }
    class Stack{
        Node head;
        int size;
        
        public Stack(){
            this.head=null;
            this.size=0;
        }
        public boolean isEmpty(){
            if(head == null)
                return true;
            return false;
        }
        public void push(char c){
            size++;
            Node ptr = new Node(c);
            ptr.next = head;
            head = ptr;
        }
        public char pop(){
            size--;
            char c = head.val;
            head=head.next;
            return c;
        }
        public int size(){
            return size;
        }
    }
}

// Approach
// Use two stacks and push the chars of the two strings in them
// pop out previous char if current char is '#' and array is not empty
// adjust sizes when pushing and popping
// when both strings are traversed and stacks are made, check if they have same size, if yes then pop them and see if the popped chars are same.
