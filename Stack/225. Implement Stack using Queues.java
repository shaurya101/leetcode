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

// Approach 1 - pop and top costly O(n) while push is O(1)
// We can push as usual but when we pop, we will first pop out all elements except last from q1 to q2.
// we store the last element in a variable.
// we copy back q2 to q1.

// Approach 2 - only push will be costly o(n)
// we will first push q1 to q2. Then we will enter new element in q1. 
// Now, we will push q2 into q1. This way the stack order will be maintained in q1.
// poll and top will be working as usual with O(1).

// Approach 2 is more i=efficient than approach 1, as only one costly operation in Approach 1.

  
// Approach 1 - pop and top are inefficient
class MyStack {
   
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    public MyStack() {
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

