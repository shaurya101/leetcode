/*
1721. Swapping Nodes in a Linked List

You are given the head of a linked list, and an integer k.
Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]

Example 2:
Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]

*/
-------------

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
    public ListNode swapNodes(ListNode head, int k) {
        ListNode fast=head;
        ListNode slow=head;
        
        ListNode point1; // kth node from start
        ListNode point2; // kth node from end
        
        for(int i=1; i<k; i++) {
            fast=fast.next;
        }
        
        point1 = fast; // kth node from start
        
        while(fast.next != null) {
            slow=slow.next;
            fast=fast.next;
        }
        
        point2=slow; // kth node from end
        
        int temp = point1.val;
        point1.val = point2.val;
        point2.val = temp;
        
        // point1.val = point2.val+point1.val;
        // point2.val = point1.val-point2.val;
        // point1.val = point1.val-point2.val;
        // This above method does not run for single node. O/p is always 0 as we substract the node val from itself. eg of failure i/p - [1], 1
        
        return head;    
    }
}

// Approach
// Use two pointers. The slow pointer will move after a delay, ie when the fast pointer has already moved k steps
// We will mark kth node as point1
// Then w will move slow and fast forward till fast reaches last node ie fast.next==null. At this point, our slow pointer will be the kth node from end. And it will be our point2
// Interchange val of point1 and point2
// use a temp variable. (If we try without temp, by adding and substracting, our value will become 0 every time if only one node is there)
