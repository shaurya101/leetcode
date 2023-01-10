/*
101. Symmetric Tree

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false

*/

=============

// Approach - traversing same tree in two ways simlutaneously
// Basically, since we need to check the three is symetric around the root node on top, we need a way to check if the tree is same if we travese it recursively from root-left-right or root-right-left. But we cannot use a stack or array to store values and then compare the stack as structure might be different for same values also.
// Hence we need to check if it is same simultaneously in one traversal.
// We take two pointers to the root, and for one pointer we traverse root-left-right and for the other we go root-right-left. If this traversal is same then the trees are symmetric.

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
    public boolean isSymmetric(TreeNode root) {
        return same(root, root);
    }
    private boolean same(TreeNode root, TreeNode root1) {
        if(root == null && root1 == null)
            return true;
        if(root == null)
            return false;
        if(root1 == null)
            return false;
        if(root.val != root1.val)
            return false;
        return same(root.left, root1.right) && same(root.right, root1.left);
    }
}
