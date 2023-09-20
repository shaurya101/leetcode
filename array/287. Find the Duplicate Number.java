/*
287. Find the Duplicate Number

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.


Example 1:
Input: nums = [1,3,4,2,2]
Output: 2

Example 2:
Input: nums = [3,1,3,4,2]
Output: 3

*/

----------


/*
Explanation - 
Given is that a unique array of numbers from 1 to n+1 where only 1 number is repeated (1 number can be repeated more than once)

Now,
We will traverse the array like -> 
from arr[0] to arr[arr[0]]. The value of our current cell will be the index of our next cell. Since values are given to be unique, if we
can find a way to mark the previous visited cells so when we see such a cell we know the index of that cell is repeated value (Approach 2)

A unique value in array will always point to an index of a cell which will be unvisited surely. And if we only had unique values and no
repetions we will keep on going to unvisited cells endlessly meaning there is no cycle.
Only when we reach a cell which has been visited before, we will have found our repeated value(index of this cell visited again) and the 
start of a cycle (which if we traverse will again lead to the repeated value which points to the index of the cell visited again twice now
and this can go on forming a cycle).
Also note, if we assume our array as linked list, the repeated value index cell will be the only cell with > 1 edges.
One edge will be from when it was first visited and this will be before the cycle. And then we our at the repeated value it will point us tho
this index again forming a cycle.

https://www.youtube.com/watch?v=iAe0GgyFrg4
https://www.youtube.com/watch?v=SyfdOzZttXA

*/

// Approach - Floyd's cycle detection
// T:O(N), S:O(1)


class Solution {
    public int findDuplicate(int[] nums) {
        // Initialize the tortoise and hare pointers
        int tortoise = nums[0];
        int hare = nums[0];
        
        // Phase 1: Find the intersection point of the two pointers
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);
        
        // Phase 2: Find the entrance to the cycle (duplicate number)
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        
        return tortoise;
    }
}

------------------

// Approach 2 - Modifying array
// T:O(N), S:O(1)


class Solution {
    public int findDuplicate(int[] nums) {
        for(int num : nums) {
            // Take the absolute value of the current number to avoid array index out of bounds
            num = Math.abs(num);
            
            // Check if the number at the absolute value of 'num' is negative
            // If it is, that means 'num' has been encountered before, and it is the duplicate
            if(nums[num] < 0)
                return num;
            
            // Mark the number at the absolute value of 'num' as negative to indicate it has been encountered
            nums[num] *= -1;
        }
        
        // If no duplicate is found, return -1
        return -1;
    }
}
