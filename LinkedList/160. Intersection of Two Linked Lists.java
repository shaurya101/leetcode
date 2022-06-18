/*
160. Intersection of Two Linked Lists

Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
The test cases are generated such that there are no cycles anywhere in the entire linked structure.
Note that the linked lists must retain their original structure after the function returns.

*/
----------------------------

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        
        while(headA != null || headB != null){ // while either is not null keep running the loop. Common mistake to use &&(AND) in this loop instead of using ||(OR)
            if(headA != null){ // avoid nullPointerException
                if(set.contains(headA))
                    return headA;
                set.add(headA);
                headA = headA.next;
            }
            if(headB != null){ // avoid nullPointerException
                if(set.contains(headB))
                    return headB;
                set.add(headB);
                headB = headB.next;   
            }
        }
        return null;
    }
}
