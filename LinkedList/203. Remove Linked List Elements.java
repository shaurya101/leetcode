/*
203. Remove Linked List Elements

Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

Example 1:
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]

Example 2:
Input: head = [], val = 1
Output: []

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
    public ListNode removeElements(ListNode head, int val) {
   
        while(head!=null && head.val == val)
            head = head.next;
        // Now our first node will not have value = val
        
        if(head == null)
            return null;
        
        // Now as head.val != val, we start with prev=head and current=head.next
        ListNode prev=head; // head.val is not equal to val because of previous checks
        ListNode current = head.next;
        
        while(current != null){
            if(current.val == val)
                prev.next = current.next;
            else
                prev = current; // we increment prev only if current node was not deleted
            current = current.next;
        }
        
        return head;
    }
}
