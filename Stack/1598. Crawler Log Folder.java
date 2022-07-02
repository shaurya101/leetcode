/*
1598. Crawler Log Folder

The Leetcode file system keeps a log each time some user performs a change folder operation.
The operations are described below:

"../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
"./" : Remain in the same folder.
"x/" : Move to the child folder named x (This folder is guaranteed to always exist).

You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
The file system starts in the main folder, then the operations in logs are performed.
Return the minimum number of operations needed to go back to the main folder after the change folder operations.

Example 1:
Input: logs = ["d1/","d2/","../","d21/","./"]
Output: 2
Explanation: Use this change folder operation "../" 2 times and go back to the main folder.

*/

------------
// Approach 1

class Solution {
    public int minOperations(String[] logs) {
        int counter=0; // to count the number of steps away from main folder
        for(int i=0; i<logs.length; i++){
            if(logs[i].equals("../"))
                counter = counter==0? 0: counter-1;
            else if(logs[i].equals("./"))
                ;
            else{
                counter++;
            }
        }
        return counter;
    }
}

-------------

// Approach 2 ; which would allow us to trace the path

class Solution {
    public int minOperations(String[] logs) {
        Stack s1 = new Stack();
        for(int i=0; i<logs.length; i++){
            if(logs[i].equals("../"))
                s1.pop();
            else if(logs[i].equals("./"))
                ;
            else{
                String s = logs[i].substring(0, logs[i].length()-1);
                s1.push(s);
            }
        }
        return s1.size();
    }
    
    // Implementing Stack using Linkedlist
    class Node{
        String val;
        Node next;
    
        public Node(String val){
            this.val = val;
            this.next = null;
        }
    }
    class Stack{
        Node head;
        int size;
        
        public Stack(){
            head=null;
            size=0;
        }
        public boolean isEmpty(){
            if(head == null)
                return true;
            return false;
        }
        public void push(String val){
            size++;
            Node ptr = new Node(val);
            ptr.next = head;
            head = ptr;
        }
        public void pop(){
            if(!isEmpty()){
                size--;
                head = head.next;
            }
        }
        public int size(){
            return size;
        }
    }   
}

// Approach
// Use a stack and traverse logs[]
// if logs[i] = ../, then pop(). Else if logs[i] = ./, do nothing. Else push
// when logs[] is travsersed, return the size of stack as that many folders need to be popped to reach main folder
