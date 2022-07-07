/*
2073. Time Needed to Buy Tickets

There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.
You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].
Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.
Return the time taken for the person at position k (0-indexed) to finish buying tickets.

Example 1:
Input: tickets = [2,3,2], k = 2
Output: 6
Explanation: 
- In the first pass, everyone in the line buys a ticket and the line becomes [1, 2, 1].
- In the second pass, everyone in the line buys a ticket and the line becomes [0, 1, 0].
The person at position 2 has successfully bought 2 tickets and it took 3 + 3 = 6 seconds.

Example 2:
Input: tickets = [5,1,1,1], k = 0
Output: 8
Explanation:
- In the first pass, everyone in the line buys a ticket and the line becomes [4, 0, 0, 0].
- In the next 4 passes, only the person in position 0 is buying tickets.
The person at position 0 has successfully bought 5 tickets and it took 4 + 1 + 1 + 1 + 1 = 8 seconds.
*/

-----------------
  
class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        localQueue q1 = new localQueue();
        
        for(int i=0; i<tickets.length; i++)
            q1.add(tickets[i], i);
        
        int counter=0; //counter of time
        while(!q1.isEmpty()){
            Node temp = q1.poll();
            if(temp.index == k && temp.val == 1)
                return counter+1;
            if(temp.val > 1) // if val == 1, it would become 0 and not needed to be added, hence we take condition val>1
                q1.add(temp.val-1, temp.index);
            counter++;
        }
        return 0;
    }
}
// List implementation
class Node{
    int val;
    int index;
    Node next;
    
    public Node(int val, int index){
        this.val = val;
        this.index = index;
        this.next = null;
    }
}
class localQueue{
    Node front;
    Node back;
    
    public localQueue(){
        this.front = null;
        this.back = null;
    }
    
    public void add(int val, int index){
        Node ptr = new Node(val, index);
        if(isEmpty()){. // if list is empty then front and back both are ptr
            front = ptr;
            back = ptr;
        }
        else{ // else point the back.next to ptr and change back to ptr
            back.next = ptr;
            back = ptr;
        }
    }
    public Node peek(){
        if(!isEmpty())
            return front;
        return null;
    }
    public Node poll(){
        if(!isEmpty()){
            Node ptr = front;
            front = front.next;
            return ptr;
        }
        return null;
    }
    public boolean isEmpty(){
        if(front == null)
            return true;
        return false;
    }
}
