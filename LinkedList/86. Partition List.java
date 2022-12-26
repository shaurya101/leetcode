/*
86. Partition List

Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]

Example 2:
Input: head = [2,1], x = 2
Output: [1,2]

*/

============

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
    public ListNode partition(ListNode head, int x) {
        ListNode listLeftHead = new ListNode(0); // we will skip this dummy node, this is done so we can directly apply 'next' logic. before we couldnt do so because it would  be null
        ListNode listRightHead = new ListNode(0);

        ListNode listLeft = listLeftHead; // used to traverse left partition
        ListNode listRight = listRightHead;// used to traverse right partition

        ListNode list = head; // will use to traverse list

        while(list != null) {
            if(list.val < x) {
                listLeft.next = list;
                listLeft= listLeft.next;
                list = list.next;
                listLeft.next = null;
            }
            else {
                listRight.next = list;
                listRight = listRight.next;
                list = list.next;
                listRight.next = null;
            }
        }
        // now list is traversed and 2 partition lists are formed. Join them.
        // remember first nodes of partitions are dummy nodes
        listLeft.next = listRightHead.next;
        return listLeftHead.next;
    }
}

// We need to make two partitions.
// First partition will have elements less than x and the secind one will have elements equal to or greater than x
