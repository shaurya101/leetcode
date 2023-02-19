/*
783. Minimum Distance Between BST Nodes

Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.

Example 1:
Input: root = [4,2,6,1,3]
Output: 1

*/

-----------------

// Approach - T: O(n), S: O(n)
// Inorder traversal of a BST gives a sorted increasing array
// Now we check the difference of the two adjacent values
// Return the least


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
    List<Integer> list = new ArrayList<>();
    public int minDiffInBST(TreeNode root) {
        inorder(root);
        int minDiff=Integer.MAX_VALUE;
        int i=0;
        while(i<list.size()-1) {
            int diff = Math.abs(list.get(i)-list.get(i+1));
            if(diff < minDiff)
                minDiff = diff;
            i++;
        }
        return minDiff;
    }
    private void inorder(TreeNode root) {
        if(root == null)
            return;
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }
}
