/*
152. Maximum Product Subarray

Given an integer array nums, find a subarray that has the largest product, and return the product.
The test cases are generated so that the answer will fit in a 32-bit integer.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

*/

--------------

// Approach 1
// If the number of negative numbers are even then the entire array will be the max product
// If odd negative numbers present then, apart from one all of them will cancel out to become positive product. SO only that negative integer will be left in the array
// the max product could be from the left side or the right side on either side of this negative integer.
// So we need to calculate product from left side and then from right side to find the max product.
// https://www.youtube.com/watch?v=GbJh0MjUa5U


class Solution {
    public int maxProduct(int[] nums) {
        int maxProd = Integer.MIN_VALUE;
        int currProd = 1;

        // traversing from left
        for(int i : nums) {
            currProd *= i;
            maxProd = Math.max(currProd, maxProd);

            if(i == 0)
                currProd = 1;
        }

        // traversing from right
        currProd = 1;
        for(int i=nums.length-1; i>=0; i--) {
            currProd *= nums[i];
            maxProd = Math.max(currProd, maxProd);

            if(nums[i] == 0)
                currProd = 1;
        }
        return maxProd;
    }
}


----------------

// Approach 2
// multiplying by a negative makes big number smaller, small number bigger; so we track max and min ie the extremums
// At one time we would update both but in real we only want to track either max or min whichever is more extreme. The other variable would be overwritten with some value which does not matter
// Whenever we traverse through a negative, we will sort of switch lanes and in real track the other variable

class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];

        for(int i = 1; i<nums.length; i++) {
            // to find max and min for nums[i], we will use max and min of nums[i-1]
            int max_copy = Math.max(nums[i], Math.max(max*nums[i], min*nums[i]));
            min = Math.min(nums[i], Math.min(max*nums[i], min*nums[i]));
            max = max_copy;

            // max could be overwritten with some other value lesser than previous max when min is getting tracked (Only one of max and min is getting updated correctly and trcaked in actual)
            result = Math.max(max, result);
        }
        return result;
    }
}
