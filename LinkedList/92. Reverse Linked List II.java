/*
Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

Example 2:
Input: head = [5], left = 1, right = 1
Output: [5]

*/

-----------------
  
// Approach 1 - LinkedList traversed once O(N)
  
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left == right)
            return head;
        
        // In this logic we want to have a node before 'left' so we create a dummmy node incase left is the first node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        // taking prev to its correct position before left
        for(int i=0; i<left-1; i++)
            prev = prev.next;
        
        ListNode tail = prev.next; // tail will point to left, tail will become the tail of the reversed list
        
        // reversing
        for(int i=0; i<right-left; i++) {
            ListNode temp = prev.next; 
            prev.next = tail.next;
            tail.next = tail.next.next;
            prev.next.next = temp;
        }
        // storing prev.next in temp
        // prev's next node will be tail's next node
        // tail should point to node after tail's next to next node
        // the new prev.next node will point to temp
        return dummy.next;
    }
}
  
-----------------
  
// Approach 2 - reversed part is traversed twice still O(N)

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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right)
            return head;
        
        ListNode prev = null; // node before 'left'
        ListNode current = head; 
        int count = 1;
        while(count != left) {
            prev = current;
            current = current.next;
            count++;
        }
        
        ListNode start = current;
        while(count != right) {
            current = current.next;
            count++;
        }
        ListNode rightNext = current.next;
        
        // Now we will reverse the list from start till current
        current.next = null;
        ListNode reverseHead = reverse(null, start);
        if(prev != null)
            prev.next = reverseHead;
        
        current = reverseHead;
        while(current.next != null)
            current = current.next;
        current.next = rightNext;
        
        if(left == 1)
            return reverseHead;
        else
            return head;
    }
    
    private ListNode reverse(ListNode prev, ListNode current) {
        if(current == null)
            return prev;
        
        ListNode next = current.next;
        current.next = prev;
        return reverse(current, next);
    }
}

// we will keep a prev that will point to node before 'left' or null incase 'left' is the starting node ie left=1
// we will traverse the list till 'right' and store the node after right in a variable(say rightNext)
// Now, from left till right we will reverse the list the return the new reverseHead and attach it to prev.next (if it is not null).
// we will again traverse the attached reversed list till null (the last node will point to null) and then we will attach the rightNext to the last reversedNode
// we will return reversehead if left was 1 else we return head
