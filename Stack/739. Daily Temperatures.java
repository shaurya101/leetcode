/*
739. Daily Temperatures

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Example 2:
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]

Example 3:
Input: temperatures = [30,60,90]
Output: [1,1,0]
*/
----------------

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // We are finding the next greater number in temperatures[]
        int[] answer = new int[temperatures.length];
        Stack s1 = new Stack(); // we will store the index of the greater numbers in the stack, as we have to keep track of index to find number of days
        
        // Approach for finding next greater element
        for(int i=temperatures.length-1; i>=0; i--){
            while(!s1.isEmpty() && temperatures[i]>=temperatures[s1.peek()]) // >= since same value will not be considered warmer day
                s1.pop();
            if(s1.isEmpty())
                answer[i] = 0;
            else
                answer[i] = s1.peek()-i;
            s1.push(i);
        }
        return answer;
    }
    
    // implementing own stack brings runtime down from 192ms to 14ms.
    class Stack{
        Node head = null;
        class Node{
            int data;
            Node next;
            public Node(int data){
                this.data = data;
                next=null;
            }
        }
        public Stack(){}
        public void push(int data){
            Node ptr = new Node(data);
            ptr.next = head;
            head = ptr;
        }
        public int peek(){
            return head.data;
        }
        public void pop(){
            head = head.next; 
        }
        public boolean isEmpty(){
            if(head == null)
                return true;
            return false;
        }
    }
}


// Approach 2
/*
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        final int m = temperatures.length;
        final int[] ans = new int[m];
        final Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < m; i++) {
            while (!stack.empty() && temperatures[stack.peek()] < temperatures[i]) {
                ans[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }
}
*/
