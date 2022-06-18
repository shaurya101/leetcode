/*

21. Merge Two Sorted Lists

You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

Example 1:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: list1 = [], list2 = []
Output: []

*/

------------------------


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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // if both lists are null return null
        if(list1 == null && list2 == null)
            return null;
        // if 1 list is null, simply return the other list
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        
        // Now both lists atleast have 1 element
        // We create a third list, and store the first element
        // We create a temporary second variable for the third list head, whose value we will iterate the Linkedlist. This temprary head will change and will lose its head value as we iterate.
        // We do this as we want to retain the head of list3(first variable)
        ListNode list3 = new ListNode();
        if(list1.val<list2.val)
        {
            list3.val = list1.val;
            list1 = list1.next;
        }
        else
        {
            list3.val = list2.val;
            list2 = list2.next;
        }
        ListNode current = list3; // temporaray head of new list
        
        
        while(list1 != null && list2 !=null) // while both lists have nodes
        {
            ListNode p = new ListNode(); // create new node
            
             if(list1.val<list2.val) // fill val in new node
                {
                    p.val = list1.val;
                    list1 = list1.next;
                }
             else
                {
                    p.val = list2.val;
                    list2 = list2.next;
                }
            current.next = p; // join current and new node
            current  = p; // make new node as current node for next iteration
        }
        while(list1 != null){ // if list2 does not have nodes left we simply add the list1 nodes in list3
            ListNode p = new ListNode(list1.val);
            current.next = p;
            current  = p;
            list1 = list1.next;
        }
        while(list2 != null){ // if list1 does not have nodes left we simply add the list1 nodes in list3
            ListNode p = new ListNode(list2.val);
            current.next = p;
            current  = p;
            list2 = list2.next;
        }
        return list3;
    }
}

// list1 and list2 are head of the two lists
