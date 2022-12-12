/*
701. Insert into a Binary Search Tree

You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

Example 1:
Input: root = [4,2,7,1,3], val = 5
Output: [4,2,7,1,3,5]

*/

--------

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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // return single node with value = val if tree doesnt exist
        if(root == null)
            return new TreeNode(val);

        // prev to keep track of where to insert node.
        TreeNode prev = null;
        // temp pointer to traverse tree
        TreeNode temp = root;

        // search insert position
        while(temp != null) {
            prev = temp;
            if(val < temp.val)
                temp = temp.left;
            else // else if not needed as val is certainly not in the tree as given in question
                temp = temp.right;
        }

        // insert node accordingly
        if(val < prev.val)
            prev.left = new TreeNode(val);
        else prev.right = new TreeNode(val);

        return root;
    }
}
