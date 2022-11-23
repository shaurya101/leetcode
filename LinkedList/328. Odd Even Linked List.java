/*
328. Odd Even Linked List

Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
The first node is considered odd, and the second node is even, and so on.
Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.
*/

-----------
    
// Approach 1
    
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
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode odd = head, even = head.next, evenHead = even;
        // we will traverse the list till we know we definetly have a new odd node ahead so we will check for 'even.next != null'
        // Since loop will run only when even.next != null, and 'odd' variable will be updated only when loop runs, odd will never be null
        // 'even' might become null as technically we will update even to even.next.next
        while(even != null && even.next != null) {
            odd.next = even.next; // pointing odd's next to the next odd node after skipping even node. 'odd' cannot be null as even.next != null in while condition
            odd = odd.next;
            even.next = odd.next; // or even.next = even.next.next; Pointing even's next node to the next even node after skipping odd node
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}

-----------

// Approach 2 

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
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode list = head; // we will traverse using list
        ListNode prevOdd = head; 
        ListNode even = head.next; // used for traversing even list
        ListNode evenHead = even; // to keep safe even's head node
        
        list.next = list.next.next; // deleting second node;
        list = list.next; // list is third node now
        even.next = null; // keeping even's last null
        
        int i = 3;
        while(list != null) {
            if(i%2 != 0) {
                prevOdd.next = list; // adding list to odd list
                prevOdd = list;
                list = list.next;
            }
            else {
                even.next = list;
                list = list.next; // to not lose the next node
                even = even.next; 
                even.next = null; // keeping end of even list as null
            }
            i++;
        }
        prevOdd.next = evenHead;
        return head;    
    }
}

// if only two or less nodes are there, simply return head
// we will delete the even nodes from the original list and collect them in a seperate 'even' list. This we will concatenate to the original list which would have become the odd list. Hence there will be a complete list with odd nodes at first and then even nodes at end.
// We want the even list's head, which will be the second node. To this we can concatenate following even nodes.
// We start with i=3, as we have the odd and even heads to which nodes can be concatenated acoordingly.
