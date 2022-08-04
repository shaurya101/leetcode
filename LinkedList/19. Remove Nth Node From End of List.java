/*
19. Remove Nth Node From End of List

Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

*/
----------------

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        
        // moving fast n nodes forward
        while(n != 0) {
            fast = fast.next;
            n--;
        }
        
        // checking if fast = null, or if head node has to be deleted
        if(fast == null)
            return head.next;
        
        // moving slow and fast ahead till fast reaches end of list
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // deleting node ahead of slow
        slow.next = slow.next.next;
        return head;
    }
}

// Approach
// we use two pointers. The fast pointer will be moved n nodes ahead from head while the slow pointer is still points to head.
// We will check if fast==null after this loop, which will be the case when the first node has to be deleted.
// if fast != null, we will use a loop and check for fast.next != null, incrementing fast and slow by one node each loop.
// When the condition meets, the node ahead of slow has to be deleted
