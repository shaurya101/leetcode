/*
1038. Binary Search Tree to Greater Sum Tree

Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

Example 2:
Input: root = [0,null,1]
Output: [1,null,1]

*/

--------
// Time complexity - O(n)
// Space complexity - O(h)
  
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
    int prevSum = 0;
    public TreeNode bstToGst(TreeNode root) {
        return revInOrder(root);
    }
    private TreeNode revInOrder(TreeNode root) {
        if(root == null)
            return root;
        revInOrder(root.right);
        root.val += prevSum;
        prevSum = root.val;
        revInOrder(root.left);
        return root;
    }
}

// we will traverse the tree in reverse Inorder manner as in a BST thr rightmost node or key is the largst in the tree and the leftmost is the smallest
// we use a variable preSum to keep track of sum till previous node, which will also be the updated value of the previous key
// we then add prevSum to current key
