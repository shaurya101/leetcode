/*
725. Split Linked List in Parts

Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.
The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.
Return an array of the k parts.

Example 1:
Input: head = [1,2,3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but its string representation as a ListNode is [].

*/

-----------

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
    public ListNode[] splitListToParts(ListNode head, int k) {
        // finding length of ListNode head
        ListNode current = head;
        int length = 0;
        while(current != null) {
            current = current.next;
            length++;
        }

        // finding width of one part
        // If there are N nodes in the linked list, then there are N/k items in each part, plus the first N%k parts have an extra item.
        int width = length/k;
        int remaining = length%k;

        // we will fill the k sublists in this list
        current = head;
        ListNode[] list = new ListNode[k];
        for(int i=0; i<k; i++) {
            ListNode subHead = current;

            // this loop will not run if length<k as (0 < 0 is false)
            for(int j=0; j<width + (i<remaining ? 1 : 0) - 1; j++) {
                current = current.next;
            }
            // we will store last node of sublist in prev and make it point to null. But to not lose the next subHead, we will first increment current to current.next;
            if(current != null) {
                ListNode prev = current;
                current = current.next; //points to head of next sublist
                prev.next = null;
            }
            list[i] = subHead;
        }
        return list;    
    }
}

// Instead of creating new lists, we will split the input list directly and return a list of pointers to nodes in the original list as appropriate.
// For a part of size L = width + (i < remainder ? 1 : 0), instead of stepping L times, we will step L-1 times, and our final time will also sever the link between the last node from the previous part and the first node from the next part.
// Time Complexity: O(N+k), where NNN is the number of nodes in the given list. If kkk is large, it could still require creating many new empty lists.
// Space Complexity: O(k), the additional space used in writing the answer.
