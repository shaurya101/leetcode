/*
24. Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

Example 1:
Input: head = [1,2,3,4]
Output: [2,1,4,3]

Example 2:
Input: head = []
Output: []

Example 3:
Input: head = [1]
Output: [1]

*/
---------------

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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        
        ListNode p1 = head;
        ListNode p2 = head.next;
        ListNode p3 = head.next.next;
        
        head = p2; // new head
        
        // reverseing first and second node
        p2.next = p1;
        p1.next = p3;
        
        // if two nodes exist ahead
        while(p3 != null && p3.next != null) {
            ListNode p0 = p1; // chain looks like p2->p1->p3
            // we store node p1 as we are going to overwrite p1, p2, p3. We need p1 as we need it to connect it to the next reverse pair
            p1 = p3;
            p2 = p3.next;
            p3 = p2.next;
            
            p0.next = p2; // link previous reversed pair of nodes with new pair
            p2.next = p1;
            p1.next = p3;
        }
        
        // if single node exists
        while(p3 != null) {
            p1.next = p3;
            p3 = p3.next;
        }
        
        return head;
    }
}

// Approach - iterative
// We will keep track of 3 consecutive nodes p1->p2->p3
// we will make head equal to initial p2 
// we will swap in pairs and connect these pairs. To connect 1 pair with the next pair, we need to store the last node of old pair and first node of new pair
// We will swap in pairs p1 and p2, and connect p1.next=p3 so our chain becomes : p2->p1->p3
// Now we use a loop with condiiton that p3 exists(p3 != null) and p3.next exists
// Since we will overwrite values of p1, p2, p3 we will temporarily store p1 as it will need to connect to the next reversed pair. (note chain order is p2->p1->next pair) so we need p1 which we atore in p0.
// We make the next pair as p1 and p2 and p3=p2.next. We reverse p1 and p2. Connect old pair p0 with new p2. 
// this loop checks for next 2 nodes from p2, ie p3 and p3.next.
// But if towards the end only single node is there this condition will fail,, but we need to connect the old pair with the remaining node.
// return head
