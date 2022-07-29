/*
977. Squares of a Sorted Array

Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

Example 1:
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].

Example 2:
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]

*/
------------------

class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] squares = new int[nums.length];
        int front=0; // iterates from beginning of nums[]
        int back=nums.length-1; // iterates backwards from end of nums[]
        int k=nums.length-1; // iterates backwards from end of squares[]

        while(front<=back)
        {
            if(nums[front]*nums[front]>nums[back]*nums[back])
                squares[k--] =  nums[front]*nums[front++];
            else
                squares[k--] =  nums[back]*nums[back--];
        }
        return squares;
    }
}
// the squares of elements in beginning and end of nums[] will be greater than the middle of nums[]
// we will compare the front and back of nums[] to find the greater number's square and fill in end of squares[]
// if we find the greater number in beginning we move the front iterator forward
// if we find the greater number in end we move the back iterator backwards
// we will fill squares[] from end as when we check 
// we iterate till front <= back
