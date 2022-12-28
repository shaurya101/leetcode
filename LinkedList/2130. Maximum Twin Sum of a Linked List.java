/*
2130. Maximum Twin Sum of a Linked List

In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.
Given the head of a linked list with even length, return the maximum twin sum of the linked list.

Example 1:
Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6. 

*/

==========

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int pairSum(ListNode head) {
        Stack<Integer> s1 = new Stack<>();

        // pointer to reach the (middle+1) of array
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            s1.push(slow.val);
            slow = slow.next;
        }

        int maxTwin = 0;
        while(slow != null) {
            int twinSum = s1.pop() + slow.val;
            slow = slow.next;
            if(twinSum > maxTwin)
                maxTwin = twinSum;
        }
        return maxTwin;
    }
}

// Approach
// We will traverse till the mid+1 of the array, and put the integers in a stack.
// We will then traverse the rest of the array and now for every node we will pop out an element and check the twinSum and compare with maxTwin
