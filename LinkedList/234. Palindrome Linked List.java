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
  
// Approach 2 - w/o caring about if list is odd or even
  
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
        ListNode mid = getMid(head);
        ListNode reverseHead = reverse(null, mid);
        
        while(head != null && reverseHead != null) {
            if(head.val != reverseHead.val)
                return false;
            head = head.next;
            reverseHead = reverseHead.next;
        }
        return true;
    }
    
    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
            return slow; 
    }
    private ListNode reverse(ListNode prev, ListNode current) {
        if(current == null)
            return prev;
        ListNode next = current.next;
        current.next = prev;
        return reverse(current, next);
    }
}

// In this approach we do not care about odd or even
// If there is an LL - 1 2 3 2 1, it will become into twp lists like - 1 2 3 null, and 1 2 3 null after 3 2 1 nullis reversed
// In even also for a list like 1 2 2 1, it will become like 1 2 2 null, and 1 2 null, our while loop will run only for 1 2 null so we can find out for palindrome

  
-----------------

// Approach 3 - Inefficient and slow 
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

