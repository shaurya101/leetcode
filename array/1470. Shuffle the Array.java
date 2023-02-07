/*
1470. Shuffle the Array

Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
Return the array in the form [x1,y1,x2,y2,...,xn,yn].

Example 1:
Input: nums = [2,5,1,3,4,7], n = 3
Output: [2,3,5,4,1,7] 
Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].

Example 2:
Input: nums = [1,2,3,4,4,3,2,1], n = 4
Output: [1,4,2,3,3,2,4,1]

*/

----------

// Time -> O(n)
// Space -> O(1)

// Approach - In place hashing (https://www.youtube.com/watch?v=sSW_MZZXIDY)

// We will iterate the array once to encode and one more time to decode
// In first iteration we want to store two things as we move through array; the present number and the future number
// We are storing the current and present number of a cell, because we will need those values to calculate the present and future values of ahead cells.
// In place hashing, we need a way to store both present and future values in a single number somehow. Now, let's say we have two numbers we want to keep track of -> 3 and 5. These are two seperate values and both cant be stored in one cell. But we can store either 35 or 53 in the cell and both required values will still be accessible using '/' and '%' (35/10 = 3 and 35%10 = 5 or 53/10=5 and 53%10=3)
// In our case we will keep present number accessible through modulus, and future number will be accessible using divide.
// As our range in the question is 1000, we will use 10,000 as base to divide or modulus with. 
// Also, we observe that in even indexes we store the first half of array elements (x1, x2...x3) and in odd indexes we store the elements from the second half (y1, y2..yn)


class Solution {
    public int[] shuffle(int[] nums, int n) {
        int first = 0, second = n, max = 10000;
        for(int i=0; i<2*n; i++) {
            if(i%2 == 0)
                nums[i] = (nums[first++]%max)*max + nums[i]; // future*max + present number
            else
                nums[i] = (nums[second++]%max)*max + nums[i]; // future*max + present number
        }
        for(int i=0; i<2*n; i++)
            nums[i]/=10000;
        return nums;
    }
}
