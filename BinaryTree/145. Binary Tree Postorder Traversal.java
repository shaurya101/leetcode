/*

145. Binary Tree Postorder Traversal

Given the root of a binary tree, return the postorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [3,2,1]

Example 2:
Input: root = []
Output: []

*/
------------------------------------

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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> l1 = new LinkedList<>();
        postOrder(root, l1);
        return l1;
    }
    
    // recursive function
    private static void postOrder(TreeNode root, LinkedList<Integer> l1)
    {
        if(root == null)
            return;
        postOrder(root.left, l1);
        postOrder(root.right, l1);
        l1.add(root.val);
    }
}

// PostOrder is left-right-root
