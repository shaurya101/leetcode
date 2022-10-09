/*
225. Implement Stack using Queues

Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.

*/
-----------------
  
  /**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

// Approach 0 - using one queue
  
class MyStack {

    // using one queue
    Queue<Integer> q1;
    
    public MyStack() {
        q1 = new LinkedList<>();
    }
    
    // we will push the element in end of queue and then pop and pull all the elements before it and push it again into queue, so queue will be similar to stack now
    public void push(int x) {
        q1.add(x);
        
        for(int i=1; i<q1.size(); i++)
            q1.add(q1.poll());
    }
    
    public int pop() {
        return q1.poll();
    }
    
    public int top() {
        return q1.peek();
    }
    
    public boolean empty() {
        if(q1.size() == 0)
            return true;
        return false;
    }
}

------
  
  
// Approach 1 - pop and top costly O(n) while push is O(1)
// We can push as usual but when we pop, we will first pop out all elements except last from q1 to q2.
// we store the last element in a variable.
// we copy back q2 to q1.

// Approach 2 - only push will be costly o(n)
// we will first push q1 to q2. Then we will enter new element in q1. 
// Now, we will push q2 into q1. This way the stack order will be maintained in q1.
// poll and top will be working as usual with O(1).

// Approach 2 is more i=efficient than approach 1, as only one costly operation in Approach 1.

  
// Approach 1 - pop and top are inefficient O(n), push is efficient O(1)
  
class MyStack {
   
   Queue<Integer> q1; // declare here to keep q1 and q2 in scope in methods
   Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>(); // initialize in constructor
        q2 = new LinkedList<>();
    }
    
    public void push(int x) {
        q1.add(x);    
    }
    
    public int pop() {
        int n=q1.size();
        while(n>1){ // dequeue elements till only 1 is left
            q2.add(q1.poll());
            n--;
        }
        int x = q1.poll();
        
        while(!q2.isEmpty()) // dequeue entire q2
            q1.add(q2.poll());
        return x;
    }
    
    public int top() {
        int n=q1.size();
        while(n>1){ // dequeue till 1 element left
            q2.add(q1.poll());
            n--;
        }
        int x = q1.poll(); // store last element in x
        while(!q2.isEmpty())
            q1.add(q2.poll());
        q1.add(x); // add x into q1 since it is a top operation not poll
        return x;
    }
    
    public boolean empty() {
        return q1.isEmpty();
    }
}

--------
  
// Approach 2 - push is inefficient O(n), poll and top efficient O(1)
  
class MyStack {
    
    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    public void push(int x) {
        int n = q1.size(); // copy q1 to q2
        while(n>0){
            q2.add(q1.poll());
            n--;
        }
        q1.add(x); // add new element to q1
        // Since always the new element is stored in head when we do poll, our queue will act as stack
        while(!q2.isEmpty()) // add q2 in q1
            q1.add(q2.poll());
    }
    
    public int pop() {
        return q1.poll();
    }
    
    public int top() {
        return q1.peek();
    }
    
    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
