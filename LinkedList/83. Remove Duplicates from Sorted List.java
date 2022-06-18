/*
83. Remove Duplicates from Sorted List

Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

Input: head = [1,1,2]
Output: [1,2]

Input: head = [1,1,2,3,3]
Output: [1,2,3]
*.

-------------------------------

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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head; // iterator of LinkedList
        
             // head==null check included in while loop
        while(current != null && current.next != null){ // keep in mind to avoid nullPointerException (like outOfBounds)
            // if all elements same the current will nr null and current.next will be null.next which will give an error
            while(current.next!=null && current.val == current.next.val)
            {
                current.next = current.next.next;
            }
            current=current.next;
        }
        return head;
    }
}
