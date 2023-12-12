/*
1464. Maximum Product of Two Elements in an Array

Given the array of integers nums, you will choose two different indices i and j of that array. Return the maximum value of (nums[i]-1)*(nums[j]-1).
 
Example 1:
Input: nums = [3,4,5,2]
Output: 12 
Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12. 

*/

==============

// Approach - T: O(n), S: O(1)
 
 class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int max2 = 0;

        for(int i=1; i<nums.length; i++) {
            if(nums[i] >= max) {
                max2 = max;
                max = nums[i];
            }
            else if(nums[i] > max2) // for case when nums[i] < max but greater than max2 like in nums = [10,2,5,2, max will nevre be updated but since it is already 10 but we still need to select max2
                max2 = nums[i];
        }
        return (max-1)*(max2-1);
    }
}


 
==============

 // Approach - T: O(nlogn), S: O(n)

class Solution {
    public int maxProduct(int[] nums) {
        // using max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
        // since there are no negative values in nums[i], no need to add product of two numbers. We can get answer by multiplying the max two numbers.
        // If negative values were there in nums, this approach would not work. eg [-20, -20, 2, 10]. Max product will be from -20 * -20 = 400 in this case
        for(int n : nums) 
            maxHeap.add(n-1);

        return maxHeap.remove() * maxHeap.remove();
    }
}
