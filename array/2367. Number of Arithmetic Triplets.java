/*
2367. Number of Arithmetic Triplets

You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff. A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:
i < j < k,
nums[j] - nums[i] == diff, and
nums[k] - nums[j] == diff.
Return the number of unique arithmetic triplets.

Example 1:
Input: nums = [0,1,4,6,7,10], diff = 3
Output: 2
Explanation:
(1, 2, 4) is an arithmetic triplet because both 7 - 4 == 3 and 4 - 1 == 3.
(2, 4, 5) is an arithmetic triplet because both 10 - 7 == 3 and 7 - 4 == 3.

*/

============

class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        HashSet<Integer> set = new HashSet<>();

        for(Integer num : nums) {
            set.add(num);
        }

        int count=0;
        for(Integer num : nums) {
            if(set.contains(num+diff) && set.contains(num+diff+diff))
                count++;
        }
        return count;
    }
}

// Approach
// We will first add all elements in a HashSet
// Then  again we will traverse the array and check for every 'num' if (num+diff) and (num+2*diff) is present in set. Increase the count if so.
