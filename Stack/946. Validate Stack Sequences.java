/*
946. Validate Stack Sequences

Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result of a sequence of push and pop operations on an initially empty stack, or false otherwise.

Example 1:
Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4),
pop() -> 4,
push(5),
pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

Example 2:
Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
Output: false
Explanation: 1 cannot be popped before 2.

*/

--------------------


// Approach

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s1 = new Stack<>();

        int i=0, j=0;
        // Iterate through the pushed array
        while(i<pushed.length) {
            s1.push(pushed[i++]); // Push the current element onto the stack
            // While the stack is not empty and the top element of the stack is equal to the current popped element
            // from the popped array, pop the element from the stack and increment the pointer in the popped array
            while(!s1.isEmpty() && s1.peek() == popped[j]) { // if top element in stack is same as in popped array
                s1.pop();
                j++;
            }
        }
        // If all elements from the pushed array have been pushed and popped correctly, the stack should be empty
        return s1.isEmpty();
    }
}
