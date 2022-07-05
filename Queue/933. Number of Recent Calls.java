/*
933. Number of Recent Calls

You have a RecentCounter class which counts the number of recent requests within a certain time frame.
Implement the RecentCounter class:
RecentCounter() Initializes the counter with zero recent requests.
int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.

Example 1:
Input
["RecentCounter", "ping", "ping", "ping", "ping"]
[[], [1], [100], [3001], [3002]]
Output
[null, 1, 2, 3, 3]

Explanation
RecentCounter recentCounter = new RecentCounter();
recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
 

Constraints:

1 <= t <= 109
Each test case will call ping with strictly increasing values of t.
At most 104 calls will be made to ping.

*/
-------------------

// Approach
// We will use a queue and compare the latest input. If diffrence in > 3000 we will pop the queue element and check subsequent queue elements.
// We will push t into queue and return size of queue as that many elements would have time difference less than 3000.

class RecentCounter {
    localQueue q1;

    public RecentCounter() {
        q1 = new localQueue();
    }
    
    public int ping(int t) {
        while(t-q1.peekHead()>3000 && q1.peekHead() != 0) // 2nd check is required as if queue is empty it will be an infinite loop
            q1.pop();
        q1.push(t);
        return q1.size();
    }
}

// Implementing Queue
class Node{
    int val;
    Node next;
    
    public Node(int val){
        this.val = val;
    }
}
class localQueue{
    Node head;
    Node top;
    int size;
    
    public localQueue(){
        this.head = null;
        this.top = null;
        this.size = 0;
    }
    
    public void push(int val){
        Node ptr = new Node(val);
        if(!isEmpty())
        {
            top.next = ptr;
            top = ptr;
        }
        else{
            head = ptr;
            top = ptr;
        }
        size++;
    }
    public int peekHead(){
        if(!isEmpty())
            return head.val;
        return 0;
    }
    public int pop(){
        if(isEmpty())
            return 0;
        size--;
        int x = head.val;
        head = head.next;
        return x;
    }
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        if(head == null)
            return true;
        return false;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
