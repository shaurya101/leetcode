/*
155. Min Stack

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.

Example 1:
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
*/
--------------------
  
class MinStack {
    private Node top;

    public MinStack() {
    }
    
    public void push(int val) {
        if(top == null)
            top = new Node(val, val, null);
        else
        {
            int min = top.val<val?top.val:val;
            Node p = new Node(val, min, top);
            top = p;
        }
    }
    
    public void pop() {
        top=top.next;
    }
    
    public int top() {
        return top.val;
    }
    
    public int getMin() {
        return top.min;
    }
    
    /* We can also include Node class here
    private class Node {
        int val;
        int min;
        Node next;
            
        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
    */
}

public class Node{
    int val;
    int min;
    Node next;
    
    public Node(int val, int min, Node next){
        this.val = val;
        this.min = min;
        this.next = next;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

// Approach 
// We keep two values in one Node, the actual value and the min value of the stack.
// Before adding any new Node in stack we compare it with the min value so supposing a min value node is added, from that node onwards we will have that min value in the upcoming nodes. And before that node, we will have the prev min value in the Node's min.
