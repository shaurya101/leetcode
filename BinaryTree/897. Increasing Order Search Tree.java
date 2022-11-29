/*
897. Increasing Order Search Tree

Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only one right child.

Example 1:
Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

*/

----------

// Approach 1 - Recursion and using inbuilt OS recursive stack
 
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
    public TreeNode increasingBST(TreeNode root) {
        return reverseInOrder(root);
    }
    private TreeNode reverseInOrder(TreeNode root) {
        if(root == null)
            return null;
        root.right = reverseInOrder(root.right); // returns new root of right subtree which will be on right of root's node
        TreeNode newRoot = reverseInOrder(root.left); // returns new head of left subtree which will be new head of this tree.
        // But also, we need to attach the root to the tail of the left subtree (ie attach the root to the rightmost node of left subtree)
        // Before this we also make root.left=null to avoid any cycles
        root.left = null; 
        if(newRoot == null)
            return root;
        TreeNode tail = rightMost(newRoot);
        tail.right = root;
        return newRoot;
    }
    private TreeNode rightMost(TreeNode root) {
        TreeNode prev = null;
        while(root != null) {
            prev = root;
            root = root.right;
        }
        return prev;
    }
}   
 
----------

// Approach 2 - recursion and queue
    
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
    Queue<TreeNode> q1 = new LinkedList<>();
    public TreeNode increasingBST(TreeNode root) {
        if(root == null)
            return null;
        
        inorder(root);
        root = q1.poll();
        TreeNode rootNew = root;
        while(!q1.isEmpty()) {
            root.left = null; // else root.left will be pointing to some node. Cycles may be created if root.left is not made null
            root.right = q1.poll();
            root = root.right;
        }
        root.left = null; // last node's left should be null as well
        return rootNew;
    }
    private void inorder(TreeNode root) {
        if(root == null)
            return;
        inorder(root.left);
        q1.add(root);
        inorder(root.right);
    }
}
