/*
234. Palindrome Linked List

Given the head of a singly linked list, return true if it is a palindrome.

Input: head = [1,2,2,1]
Output: true

*/
-----------------

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
    public boolean isPalindrome(ListNode head) {
        ListNode sp=head, fp=head, mid=null; // slow and fast pointer
        
        // finding mid
        while(fp != null && fp.next != null){
            sp = sp.next;
            fp = fp.next.next;
        }
        
        // checking if LL odd or even
        // odd --> fp is not null; this is standard property of LL. Try to remember.
        if(fp != null) 
            mid = sp.next;
        else
            mid = sp;
        
        // reverse the second half of LL
        ListNode second = reverse(mid);
        
        // compare two lists
        while(second !=null){ // we cannot write head instead of second as head will reach null after traversing entire list, however we only want half list length to be traversed
            if(head.val != second.val)
                return false;
            head=head.next;
            second=second.next;
        }
        return true;
    }
    
    private ListNode reverse(ListNode current){
        ListNode prev=null, next=null;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}

// Approach 1 - Finding mid of linkedList, reversing the second half of linkedList, then comparing the two halves(with second half reversed)
// Standard method to find mid, slow and fast pointer. fast pointer moves two nodes at once.
// Once we find mid, we check if linkedList was odd or even. This also is standard property of LL. If even fast==null, else fast!=null (odd).
// We reverse the 2nd half of LL 
// We compare the two halves(2nd half reversed) of LL. 

-----------------

// Approach 2 - Inefficient and slow 
// use StringBuilder and iterate the list appending its values in the string
// check if string is palindrome
  
class Solution {
    public boolean isPalindrome(ListNode head) {
        StringBuilder sb = new StringBuilder();
        
        while(head != null){ // NOTE if we say head.next!=null then loop will not run for last node
            sb.append(head.val);
            head=head.next;
        }
        int i=0;
        int j=sb.length()-1;
        while(i<j){
            if(sb.charAt(i) != sb.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}

