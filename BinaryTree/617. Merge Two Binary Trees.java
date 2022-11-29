/*
617. Merge Two Binary Trees

You are given two binary trees root1 and root2.
Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.
Return the merged tree.
Note: The merging process must start from the root nodes of both trees.

Example 1:
Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
Output: [3,4,5,5,4,null,7]

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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode root3 = merge(root1, root2);
        return root3;
    }
    private TreeNode merge(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null)
            return null;
        else if(root1 == null)
            return root2; // if root1 is null and root2 is not, then the subtree of root3 will be equal to subtree of this corresponfing root2. Hence we return root2.
        else if(root2 == null)
            return root1;
        else { // if neithet is null
            TreeNode root3 = new TreeNode(root1.val + root2.val);
            root3.left = merge(root1.left, root2.left);
            root3.right = merge(root1.right, root2.right);
            return root3;
        }
    }
}

// if at any point in tree/subtree, both roots are null we return null.
// If root1 is null only at any point of tree traversal, we return root2 and vice versa.
// if neither root is null, we first create a root3 whose val is root1+root2's val. Then we have to find root3.left and root3.right. After finding left and rights(the recursive call will merge all subsequent subtrees), we return root3.
