/*
110. Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true

*/

-----------------

// Approach - T: O(N), S: O(N)

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
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    // height returns 0 for null node, -1 when height of the subtrees of that node has a difference > 1, and height of the node if the difference was < 1 of the subtrees of this node
    public int height(TreeNode root) {
        if(root == null)
            return 0;
        
        // if left or right subtree height had diff>1, return -1
        int leftHeight = height(root.left);
        if(leftHeight == -1)
            return -1;
        int rightHeight = height(root.right);
        if(rightHeight == -1)
            return -1;

        // check if left and right subtree height of our node had diff>1, return -1
        if(Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        // return height of node
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

/*
Approach
We start by recursively calling height() on the left subtree and then on the right subtree. If either of the subtrees is not balanced, we return -1. Otherwise, we check if the difference between the heights of the subtrees is greater than 1, and if it is, we return -1. If the tree is balanced, we return the maximum height of the subtrees plus 1. The isBalanced() method just checks if the root node has a height of -1, which indicates that the tree is not balanced.
*/
