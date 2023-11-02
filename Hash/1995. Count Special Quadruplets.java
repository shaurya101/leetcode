/*
1995. Count Special Quadruplets

Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:

nums[a] + nums[b] + nums[c] == nums[d], and
a < b < c < d
 

Example 1:

Input: nums = [1,2,3,6]
Output: 1
Explanation: The only quadruplet that satisfies the requirement is (0, 1, 2, 3) because 1 + 2 + 3 == 6.

Example 2:
Input: nums = [3,3,6,4,5]
Output: 0
Explanation: There are no such quadruplets in [3,3,6,4,5].

*/


-----------

// Approach 1 - T: O(n^2), S: O(n)


class Solution {
    public int countQuadruplets(int[] nums) {
        int count = 0;
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Calculate the sum of elements at indices i and j and increment its count in the map.
                mp.put(nums[i] + nums[j], mp.getOrDefault(nums[i] + nums[j], 0) + 1);
            }

            for (int k = i + 2; k < n; k++) {
                // Calculate the difference between elements at indices k and (i+1) and check its count in the map.
                count += mp.getOrDefault(nums[k] - nums[i + 1], 0);
            }
        }

        return count;
    }
}




-----------

// Approach 2 - T: O(n^3), S: O(n)


class Solution {
    public int countQuadruplets(int[] nums) {
        int count = 0;
        int n = nums.length;
        // <nums[d], frequency>
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(nums[n-1], 1); // adding last element in map

        // Loop through the array in reverse order, starting from second last element
        for (int c = n - 2; c >= 2; c--) {
            for (int b = c - 1; b >= 1; b--) {
                for (int a = b - 1; a >= 0; a--) {
                    int sum = nums[c] + nums[b] + nums[a];
                    // Check if the current sum is already in the map, indicating a valid quadruplet.
                    if (mp.containsKey(sum)) {
                        // Increment the count by the number of times this sum has been encountered before.
                        count += mp.get(sum);
                    }
                }
            }
            
            // Update the map to store the current element's value and its frequency.
            mp.put(nums[c], mp.getOrDefault(nums[c], 0) + 1);
        }
        
        return count;
    }
}


-----------

// Approach 3 - T: O(n^4), S: O(n)


class Solution {
    public int countQuadruplets(int[] nums) {
        int count=0;
        int n = nums.length;
        for(int i=0; i<=n-4; i++)
            for(int j=i+1; j<=n-3; j++)
                for(int k=j+1; k<=n-2; k++)
                    for(int l=k+1; l<=n-1; l++)
                        if(nums[i]+nums[j]+nums[k] == nums[l])
                            count++;
        return count;
    }
}
