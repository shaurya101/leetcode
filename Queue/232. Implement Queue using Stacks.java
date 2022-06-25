/*
232. Implement Queue using Stacks

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
*/

---------------------
    
 
// Approach 1 - Two times stack gives a queue as order is reversed twice gives initial order
// There is one input stack, onto which we push the incoming elements, and one output stack, from which we peek/pop. 
// We move elements from input stack to output stack when needed, i.e., when I need to peek/pop but the output stack is empty. When that happens, I move all elements from input to output stack, thereby reversing the order so it's the correct order for peek/pop.
// The loop in peek does the moving from input to output stack. Each element only ever gets moved like that once, though, and only after we already spent time pushing it, so the overall amortized cost for each operation is O(1).
    
    
 class MyQueue {
    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();

    public MyQueue() {
    }
    
    public void push(int x) {
        input.push(x);
    }
    
    public int pop() {
        peek(); // NOTE- 3 things to clear. This is not a syntax error.
        // 1. It calls the class method. 
        // 2. We may think there is a return statement in peek() but here we do not store the returned value in a variable. But it works. It is not necessary to 'catch' the returned value from the called function.
        // 3. Also class method peek() is not static so does it not need an object? Or is it because we are calling it from inside class we dont need to mention object?
        return output.pop(); // stack method pop 
    }
    
    public int peek() {
        if(output.isEmpty())
            while(!input.isEmpty())
                output.push(input.pop());
        return output.peek(); // stack method peek
    }
    
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}
    
    
---------------------

class MyQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public MyQueue() {
    }
    
    public void push(int x) {
        int n=s1.size();
        // moving s1 to s2
        while(n>0){
            s2.push(s1.pop());
            n--;
        }
        // adding the new element in s1
        s1.push(x);
        // moving s2 back to s1
        while(!s2.isEmpty())
            s1.push(s2.pop());
    }
    
    public int pop() {
        return s1.pop();
    }
    
    public int peek() {
        return s1.peek();
    }
    
    public boolean empty() {
        return s1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

// Approach 2 - Using costly push operation.
// We will first copy our elements from s1 to s2.
// We will push the new element in s1.
// Then we will push s2 elements into s1.

// Approach 3 - Using costly pop and top operations
// Push operation will be as usual
// in pop; we will first pop all the elements except last from s1 to s2
// we will store this last element in x, and meanwhile we will copy all elements of s2 back to s1. And return x.
// In top; We will copy all elements from s1 to s2. Store last element of s2 in x.
// Move back elements from s2 to s1. Return x.

// Approach 2 and 3 are inefficient.
