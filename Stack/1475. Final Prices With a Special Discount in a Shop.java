/*
1475. Final Prices With a Special Discount in a Shop

Given the array prices where prices[i] is the price of the ith item in a shop. There is a special discount for items in the shop, if you buy the ith item, then you will receive a discount equivalent to prices[j] where j is the minimum index such that j > i and prices[j] <= prices[i], otherwise, you will not receive any discount at all.
Return an array where the ith element is the final price you will pay for the ith item of the shop considering the special discount.

Example 1:
Input: prices = [8,4,6,2,3]
Output: [4,2,4,2,3]
Explanation: 
For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4. 
For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2. 
For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4. 
For items 3 and 4 you will not receive any discount at all.

*/
---------------

class Solution {
    public int[] finalPrices(int[] prices) {
        localStack s1 = new localStack();
        int[] ans = new int[prices.length];
        
        for(int i=prices.length-1; i>=0; i--){
            while(!s1.isEmpty() && s1.peek()>prices[i])
                s1.pop();
            
            if(s1.isEmpty())
                ans[i] = prices[i];
            else
                ans[i] = prices[i] - s1.peek();
            
            s1.push(prices[i]);
        }
        return ans;
    }
}
class Node{
    int val;
    Node next;
    
    public Node(int val){
        this.val = val;
    }
}
class localStack{
    Node head;
    
    public localStack(){
        head=null;
    }
    public void push(int val){
        Node ptr = new Node(val);
        ptr.next = head;
        head = ptr;
    }
    public int peek(){
        return head.val;
    }
    public int pop(){
        int x = head.val;
        head=head.next;
        return x;
    }
    public boolean isEmpty(){
        if(head == null)
            return true;
        return false;
    }
}

// Approach
// essentially we are finding next smaller element to the right
// we use a stack and traverse from end of prices[]
// Since we are finding smaller elements, our stack will be in descending order
// we will pop s1.peek() if it is greater than price[i], since now our next smaller elements of subsequent elements would be price[i].
// we store the price[i] if it is the smallest element, else we store the difference of price[i] and s1.peek() to get the discounted price.
