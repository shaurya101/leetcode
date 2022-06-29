/*
1047. Remove All Adjacent Duplicates In String

You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
We repeatedly make duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.

Example 1:
Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".

Example 2:
Input: s = "azxxzy"
Output: "ay"
*/
---------------------

class Solution {
    public String removeDuplicates(String s) {
        int length = s.length();
        if(length == 0)
            return "";
        
        Stack s1 = new Stack();
        int i = 0;
        while(i<length){
            char c = s.charAt(i);
            if(s1.head == null){ // NOTE - head can become null in the mid of the spring too and not just for the first node. eg for string abbaca, head will become null before it pushes 'c'. 
                 s1.push(s.charAt(i));
                // System.out.println(s1.head + " " + s1.head.next+" "+s1.head.val); // used for deebugging
            }
            else if(c == s1.peek())
                s1.pop();
            else
                s1.push(c);
          //  System.out.println(s1.head + " " + s1.head.next+" "+s1.head.val); // used for deebugging
            i++;
        }
        StringBuilder sb = new StringBuilder();
        while(s1.head != null){
            sb.append(s1.head.val);
            s1.head = s1.head.next;
        }
        return sb.reverse().toString();   // reversing a StringBuilder O(n) is better then inserting at 0 index which takes O(n^2)
    }
    
    // implementing own stack
    public class Node{
        char val;
        Node next;
        
        public Node(char val){
            this.val=val;
        }
    }
    public class Stack{
        Node head;
        
        public Stack(){ // when stack class is instantiated head=null
            head=null;
        }
        public void push(char c){
            Node ptr = new Node(c);
            ptr.next = head;
            head = ptr;
        }
        public char peek(){
            return head.val;
        }
        public char pop(){
            char x = peek();
            head = head.next;
            return x;
        }
    }
}

// Approach 1
// use a stack and we will push characters of string 1 by 1.
// If two consecutive characters are same, pop them both out.
// return the final string.
