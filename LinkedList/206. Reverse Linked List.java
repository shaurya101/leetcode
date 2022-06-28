/*
206. Reverse Linked List

Given the head of a singly linked list, reverse the list, and return the reversed list.

Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

*/
-------------------

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
    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode current = head;
        ListNode nextNode;
        
        while(current != null){
            nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }
}

// Iterative approach
// We keep 3 nodes with us; prev, current and the nextNode.
// We will make change to the link of current Node. We change the current.next to point at prev Node. or we take the link pointing at nextNode and point it at current Node.
// After that we will shift our 3 nodes forward by 1 step.
// At the end current will be null and our prev will be the last node  or the new head.
