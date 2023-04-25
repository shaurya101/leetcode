/*
2336. Smallest Number in Infinite Set

You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].

Implement the SmallestInfiniteSet class:

SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
int popSmallest() Removes and returns the smallest integer contained in the infinite set.
void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
 

Example 1:

Input
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
Output
[null, null, 1, 2, 3, null, 1, 4, 5]

Explanation
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                   // is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.


*/


------------
  
// Approach 1 : Truly infinite approach T: O(log N) as treeset add and poll operations have log N, S: O(N)
  
class SmallestInfiniteSet {
    private TreeSet<Integer> set; // to store the positive integers. TreeSet is used as a combo of HashSet and PriorityQueue here
    private int next; // it is the next number to be popped if set is empty. 
    // When set is empty, it will imply all integers from 1 to (next-1) has been popped and now integers from next to infinity are remaining to be popped.
    // Note that the set is designed so it will only contain integers < next. 
    // So if set has values, first they will be popped and the set will become empty and then integers from 'next' to infinity can be popped
    // Using this concept of next, we are able to truly follow an infinite set approach without the constraint of 1000

    public SmallestInfiniteSet() {
         set = new TreeSet<>();
         next = 1; // initializing next to 1, as we assume we have numbers from next to infinity, ie 1 to infinity in the beginning.
    }
    
    public int popSmallest() {
        // if set is empty, return next and increment it else return the poll() of set
        return set.isEmpty() ? next++ : set.pollFirst();
    }
    
    public void addBack(int num) {
        // if num is less than next, we add it in set so when pop happens the smaller number from set will be popped
        if (num < next)
            set.add(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
  


------------

// Approach 2
  
class SmallestInfiniteSet {
    TreeSet<Integer> set; // to store the positive integers. TreeSet is used as a combo of HashSet and PriorityQueue here
    
    public SmallestInfiniteSet() {
         set = new TreeSet<>();
         // storing 1000 numbers as per constraints
         for(int i=1000; i>=1; i--)
            set.add(i);
    }
    
    public int popSmallest() {
        return set.pollFirst();
    }
    
    public void addBack(int num) {
        set.add(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */


------------


// Approach 3 T: O(Nlog N) for initialization of heap and O(log N) for poll() and add() in heap. S: O(N)


class SmallestInfiniteSet {
    PriorityQueue<Integer> minHeap; // to store the positive integers
    HashSet<Integer> set; // to store the integers removed from heap
    public SmallestInfiniteSet() {
         minHeap = new PriorityQueue<>();
         // storing 1000 numbers as per constraints
         for(int i=1000; i>=1; i--)
            minHeap.add(i);
        // set will be empty for now. Elements will be added when we poll() form heap
        set = new HashSet<>();
    }
    
    public int popSmallest() {
        int temp = minHeap.poll();
        set.add(temp); // adding the number in set from where we can verify if it was removed from heap
        return temp;
    }
    
    public void addBack(int num) {
        if(!set.contains(num))
            return;
        minHeap.add(num);
        set.remove(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
