/*
496. Next Greater Element I

The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
*/
---------------

// Approach 1 : O(n)

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> s1 = new Stack<>();
        
        // Finding greatest element to the right Approach A
        for(Integer nums : nums2){
            while(!s1.isEmpty() && nums>s1.peek())
                map.put(s1.pop(), nums);
            s1.push(nums);
        }
        // This is not a O(n^2) solution as it will not run n*n times. 
        // Every element is pushed and popped only once at worst case. So essentially it is O(n+n) ie O(n).
        //In worst case, which would be when elements are in decreasing order and the last element is greatest element. Here the while loop will not run and elements are just pushed onto stack. For the last element, the while loop will run n times again, So total runtime is n+n ie O(n).
        
        // we will store the answers in nums1 itself.
        for(int i=0; i<nums1.length; i++)
            nums1[i] = map.getOrDefault(nums1[i], -1);
        // get value of nums1[i] if key exist, else get -1.
        return nums1;
    }
}

// Approach
// First we will iterate nums2 and store the next greater element in a hashmap in key value pair.
// key = num, value = next greater element
// if the next greater element does not exist for a num we will not include it in hashmap.
// then we iterate nums1 and see if the value for that nums1[i] key exist in hashmap. If it does we store the value else we will store -1 default.


// Finding greatest element to the right Approach A

// We use a stack and iterate from beginning of array.
// We will use hashMap to hold key value pairs. 
// The stack will hold the keys of the hasMap and the nums2[i] iteration element will be the values in the HashMap.
// For the first element we push it to stack and move to next element.
// Now, we check if the current num2[i] element is greater than the number in stack. If it is greater than we have found the first greater number to the right, for the key in the stack. Since we are moving from left to right, we can find the first greater element to the right of a particular element.
// We will pop the key out from the stack, And compare the next key in the stack with current nums2[i]. Hence while loop.
// The stack will be in decreasing order.
// We finally push the nums2[i] in stack as we would want to find its next greater element as well.
// If greater, we add it to the hashmap.

// Key observation:
// Suppose we have a decreasing sequence followed by a greater number
// For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence
// We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones, their next greater element is x
// For example [9, 8, 7, 3, 2, 1, 6]
// The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1 so we pop 1 2 3 whose next greater element should be 6


// Finding greatest element to the right Approach B

// We use a atck and hashmap.
// This time, we iterate nums2 from back and we store the value in stack not the key.
// We will push the last element in the stack and move to previous element in nums2.
// Now we will compare if nums2[i]<stack top. If it is then we have found the greater element to he right for nums2[i] and we store it in ans[i]. If nums2>stack top, we pop out the stack top and now compare nums2[i] with the new stack top. If the new stack top is greater than we store it in ans[i], else we pop that too. When we find the greater element we can push nums[i] in stack and do i--. Incase we do not find greatre element for nums[i] ie our stack becomes empty due to popping. we will simply store -1 for ans[i] push nums[i] in stack, then i--.
// We see that the stack will contain elements in increasing order. The bottom of stack will be largest and the top will the smallest for the stack. Also, the stack top will be left in the nums2 than the stack bottom in nums2.

// Approach 2 code - 
/*
for(int i = nums2.length-1; i>=0; i--){
    while(!s1.isEmpty() && s1.peek()<nums[i])
        s1.pop();
     if(s1.isEmpty())
        ans[i]=-1;
    else
        ans[i]= s1.peek();
    s1.push(nums[i]);
}
*/
  
----------------
  
// Approach 2 brute force O(n^2)
  
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        
        for(int i=0; i<nums1.length;i++){
            int a =nums1[i];
            boolean found = false; // to check if nums1[i] found in nums2
            boolean bigFound = false; // to check if biggger number found in nums2
            for(int j=0; j<nums2.length;j++){
                if(nums2[j] == a && found == false){
                    found = true;
                    continue;
                }
                if(nums2[j]>a && found==true){
                    bigFound = true;
                    ans[i] = nums2[j];
                    break;
                }
            }
            if(bigFound == false)
                ans[i]=-1;
        }
        return ans;
    }
}
