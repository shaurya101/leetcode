/*
94. Binary Tree Inorder Traversal

Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:
Input: root = []
Output: []

*/

-----------------------------
  
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
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> l1 = new LinkedList<>(); // LinkedList
        inorder(root, l1);
        return l1;
    }
    
    // recursive approach
    private static void inorder(TreeNode root, LinkedList<Integer> l1)
    {
        if(root == null) 
            return;
        inorder(root.left, l1); // go to the leftmost node possible
        l1.add(root.val); // add the leftmost node to l1
        inorder(root.right, l1); // check the right subtree of the above leftmost node
    }
}

// Inorder is left-root-right
