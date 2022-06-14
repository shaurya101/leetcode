/*

144. Binary Tree Preorder Traversal

Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]

Example 2:
Input: root = []
Output: []

*/
-------------------------

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
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> l1 = new LinkedList<>();
        preOrder(root, l1);
        return l1;
    }
    
    // recursive approach
    private static void preOrder(TreeNode root, LinkedList<Integer> l1)
    {
        if(root == null)
            return;
        l1.add(root.val); // add root to list
        preOrder(root.left, l1);
        preOrder(root.right, l1);
    }
}

// Preorder is root-left-right
