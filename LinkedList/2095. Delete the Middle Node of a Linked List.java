/*
2095. Delete the Middle Node of a Linked List

You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.
For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.

Example 1:
Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation:
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node.

Example 2:
Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation:
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.

Example 3:
Input: head = [2,1]
Output: [2]
Explanation:
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.

*/
---------------

// Approach 1 - without third pointer as in approach 2

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
    public ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null) // if only one node is there return null
            return null;
        
        ListNode fast = head.next.next;
        ListNode slow = head;
        
        while(fast != null && fast.next != null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        slow.next=slow.next.next;
        return head;
    }
}

// Approach
// If only 0 or 1 node is present, return null as it will be deleted
// Else, take two pointers fast and slow
// slow=head
// fast=head.next.next, to save an iteration, as after while loop our slow pointer will point to the node before middle node and we can directly delete middle node w/o using third pointer 'prev'
// Move slow one node forward and fast two nodes in one loop until fast reaches end of list or becomes null.
// Our slow will be the node before middle node which we can now delete.
// slow.next=slow.next.next;
    
---------------

// Approach 2 - using third pointer

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
    public ListNode deleteMiddle(ListNode head) {
        if(head.next == null) // if only one node is there return null
            return null;
        
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        
        while(fast != null && fast.next != null) {
            prev=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        prev.next=slow.next;
        return head;
    }
}

// Approach
// If only 1 node is present, return null as it will be deleted
// Else, take two pointers fast and slow and 1 more pointer prev to store previous slow's node
// make prev = slow and move slow one node forward and fast two nodes in one loop until fast reaches end of list or becomes null.
// Our slow will be the middle which needs to be deleted
// prev.next=slow.next;
