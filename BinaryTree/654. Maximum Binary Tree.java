/*
654. Maximum Binary Tree

You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

Create a root node whose value is the maximum value in nums.
Recursively build the left subtree on the subarray prefix to the left of the maximum value.
Recursively build the right subtree on the subarray suffix to the right of the maximum value.
Return the maximum binary tree built from nums.

Example 1:
Input: nums = [3,2,1,6,0,5]
Output: [6,3,5,null,2,0,null,null,1]
Explanation: The recursive calls are as follow:
- The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
    - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
        - Empty array, so no child.
        - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
            - Empty array, so no child.
            - Only one element, so child is a node with value 1.
    - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
        - Only one element, so child is a node with value 0.
        - Empty array, so no child.


*/

-----------

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return max(nums, 0, nums.length-1);
    }
    
    private TreeNode max(int[] nums, int startIndex, int endIndex) {
        int maxElementIndex = maxIndex(nums, startIndex, endIndex);
        if(maxElementIndex < 0) // ie no subarray b/w startIndex and endIndex
            return null;
        
        // create root with max element
        TreeNode root = new TreeNode(nums[maxElementIndex]);
        // root.left will be the max element of left subarray
        root.left = max(nums, startIndex, maxElementIndex-1);
        // root.right will be the max element of right subarray
        root.right = max(nums, maxElementIndex+1, endIndex);
        return root;
    }
    private int maxIndex(int[] nums, int startIndex, int endIndex) {
        if(startIndex > endIndex) // it means this subarray is finished.
            return -1;
        if(startIndex == endIndex)
            return startIndex;
        int max = startIndex++;
        while(startIndex <= endIndex) {
            if(nums[startIndex] > nums[max])
                max = startIndex;
            startIndex++;
        }
        return max;
    }
}

// step 1 - find index of max or greatest element in array, this will be our root
// step 2 - repeat same process for left subarray and right subarray and attach the respective consequent child roots to parent root.left and parent root.right
