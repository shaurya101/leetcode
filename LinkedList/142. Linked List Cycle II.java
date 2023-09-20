/*
142. Linked List Cycle II

Given the head of a linked 2list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.
 

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

*/


------------------

// Approach - T: O(N), S: O(1)

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null; // If the linked list is empty, there's no cycle.
        }
        
        // Initialize two pointers, slow and fast, both starting from the head of the list.
        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect the cycle (if it exists) using Floyd's Tortoise and Hare algorithm.
        do {
            if (fast == null || fast.next == null) {
                return null; // If fast reaches the end, there's no cycle.
            }
            slow = slow.next; // Move slow one step.
            fast = fast.next.next; // Move fast two steps.
        } while (slow != fast);

        // Phase 2: Find the starting node of the cycle.
        slow = head; // Reset the slow pointer to the head.
        while (slow != fast) {
            slow = slow.next; // Move slow one step.
            fast = fast.next; // Move fast one step.
        }

        return slow; // Return the starting node of the cycle (or null if no cycle).
    }
}
