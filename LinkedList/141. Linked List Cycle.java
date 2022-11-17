/*
141. Linked List Cycle

Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
*/

----------------------------
    
// Approach 1 - two pointer approach
    
    
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode walker = head; // slow iterator (goes 1 node forward)
        ListNode runner = head; // fast iterator (goes 2 nodes forward)
        
        while(runner != null && runner.next != null){ // avoid nullPointerException
            walker = walker.next;
            runner = runner.next.next;
            if(walker == runner)
                return true; // if runner = walker, it means it is a cycle is present and runner has completed the circle atleast once and reached the walker
        }
        return false;
    }
}

    
----------------------------
    
// Approach 2
    
    
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set=new HashSet<>(); // HashSet
        
        while(head != null){ // traversing the linkedList
            if(set.contains(head)) // if element is already present
                return true;
            set.add(head); // else add the element
            head=head.next;
        }
        return false; // if head reached null, list cannot be circular
    }
}
