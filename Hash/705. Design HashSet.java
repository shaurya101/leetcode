/*
705. Design HashSet

Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:
void add(key) Inserts the value key into the HashSet.
bool contains(key) Returns whether the value key exists in the HashSet or not.
void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 

Example 1:
Input
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]
Output
[null, null, null, true, false, null, true, null, false]

Explanation
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // return True
myHashSet.contains(3); // return False, (not found)
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // return True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // return False, (already removed)

*/


---------------
 
// Approach 1 - IMPORTANT many concepts! Arraylist of Arraylist, Integer.valueOf(x) vs int x in remove() of Arraylist
	
	
class MyHashSet {
    // Initialize an ArrayList of ArrayLists with a size of 1000
    private ArrayList<ArrayList<Integer>> list = new ArrayList<>(1000);

    public MyHashSet() {
        // Create a new ArrayList for each index in the outer ArrayList
        for(int i=0; i<1000; i++) {
            list.add(new ArrayList<Integer>());
        }
    }
    
    public void add(int key) {
        if(!contains(key)) {
             // Use modulus operator to get the correct index in the outer ArrayList to add the key into
            list.get(key%1000).add(key);
        }
    }
    
    public void remove(int key) {
        if(contains(key)) {
            // Use the valueOf() method to convert the int key to an Integer object to avoid ambiguity with the remove() method
            list.get(key%1000).remove(Integer.valueOf(key)); /* explained at last */
        }
    }
    
    public boolean contains(int key) {
         // Iterate over the ArrayList at the corresponding index and check if the key is present
        for(int i=0; i<list.get(key%1000).size(); i++) {
            if(list.get(key%1000).get(i) == key)
                return true;
        }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */


 /* 
 Why we take 'list.get(key%1000).remove(Integer.valueOf(key));' and not just 'list.get(key%1000).remove(key);'
 lets say we enter 2 and 22. So in index 2 of outer arraylist we will have a inner list with 2 values ie 2 and 22 with indexes 0 and 1.
 Now,
 if we do 'list.get(22%1000).remove(22);', the inner arraylist will throw an IndexOutOfBound exception since we do not have index 22 in inner arraylist. There is only index 0 and index 1!
It would work if the remove(int x) read x as the value and not as index number.
To read x as a value we use the overloaded verion of remove(), which is remove(Object o)
Since Object o is read as the value, so 22 is read as value and now it will not search for index 22 but for value 22 and remove it!
*/

/* 
Theory on overloaded function remove() in ArrayList
In Java, the ArrayList class has two overloaded remove methods:

remove(int index): Removes the element at the specified index in this list.
remove(Object o): Removes the first occurrence of the specified element from this list, if it is present.
In the remove method of the MyHashSet class, we want to remove the key from the ArrayList at the index key%1000. The key is of type int, which means that if we use remove(key), the ArrayList would try to remove the element at the index key instead of the element with value key.

To avoid this problem, we use Integer.valueOf(key) to create an Integer object with the same value as key. By using remove(Integer.valueOf(key)), we tell the ArrayList to remove the element with the value key instead of the element at the index key.

So, list.get(key%1000).remove(Integer.valueOf(key)) removes the first occurrence of key from the ArrayList at the index key%1000.
*/
	
/*
TIME and SPACE COMPLEXITY

The time complexity of the add, remove, and contains operations in this implementation of a hash set is O(1) in the average case.

The space complexity of the implementation is O(n + k) where n is the number of keys in the hash set and k is the size of the ArrayList used to store the buckets. 
In this implementation, we have k = 1000, so the space complexity is O(n + 1000). Since the value of n is not bounded, the worst-case space complexity of the implementation can be considered to be O(n).
*/


---------------

// Approach 2
 
class MyHashSet {
    int num[];
    public MyHashSet() {
        num = new int[31251];
    }
	
	// set the bit if that element is present
    public void add(int key) {
        num[getIdx(key)]|=getMask(key);
    }
	
	//unset the bit if a key is not present
    public void remove(int key) {
        num[getIdx(key)] &= (~getMask(key));
    }
	
	//check if bit corresponding to the key is set or not
    public boolean contains(int key) {
        return (num[getIdx(key)]&getMask(key))!=0;
    }
	
	// idx of num[] to which this key belongs
	// for key = 37, it will give 1
    private int getIdx(int key)
    {
        return (key/32);
    }
	
	// get mask representing the bit inside num[idx] that corresponds to given key.
	// for key = 37, it will give 00000000000000000000000000100000
    private int getMask(int key)
    {
        key%=32;
        return (1<<key);
    }
}

/*
Explanation:
Maximum possible value to be stored = 1000000
Considering each bit of a number reprsents a number
Number of bits in one number(Numbers each number in the array can represent) = 32
Total numbers required = 1000000/32 = 31250
For example:
number 0 can be reprsented by idx = 0, bit = 0 i.e. num[0] = 00000000000000000000000000000001
number 37 can be represented by idx = 1(37/32), bit = 5(37%32) ie. num[1] = 00000000000000000000000000100000
*/

---------------

// Approach 3

class MyHashSet {
    private boolean[] set;

    // Initialize your data structure here
    public MyHashSet() {
        set = new boolean[1000001];
    }
    
    public void add(int key) {
        set[key] = true;
    }
    
    public void remove(int key) {
        set[key] = false;
    }
    // Returns true if this set contains the specified element
    public boolean contains(int key) {
        return set[key];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */

