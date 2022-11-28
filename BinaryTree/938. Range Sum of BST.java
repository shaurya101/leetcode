/*
938. Range Sum of BST

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

Example 1:

Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.

*/

-------


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
    int sum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null)
            return sum;
  
        if(root.val >= low && root.val <= high)
            sum += root.val;
        
        if(root.val > low) // to optimize
            rangeSumBST(root.left, low, high);
        if(root.val < high) // to optimize
            rangeSumBST(root.right, low, high);
        return sum;
    }
}

// we have travelled the tree in inorder manner
// however to optimize, we do not need to go to the leaf nodes always, if our node is smaller than low then we need not check nodes on its left. And if our node is greater than right, then we nooed not check nodes on its right
