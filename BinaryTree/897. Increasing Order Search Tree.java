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
        return newRoot(root);
    }
 
    private TreeNode newRoot(TreeNode root) {
        if(root == null)
            return null; 
        
        root.right = newRoot(root.right); // returns new root of right subtree which will be on right of root's node
        TreeNode newLeftRoot = newRoot(root.left); // returns new head of left subtree which will be new head of this tree.
        // But also, we need to attach the root to the tail of the left subtree (ie attach the root to the rightmost node of left subtree)
        // Before this we also make root.left=null to avoid any cycles
        root.left = null; 
        if(newLeftRoot == null)
            return root;
        TreeNode tail = rightMost(newLeftRoot);
        tail.right = root;
        return newLeftRoot;
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

// breakdown of recursive function
// For a root, we have to find the newRoot of its right subtree and the newRoot of its left subtree. Also, for the left subtree we have to find its tail(ie the rightmost node in the subtree when traversing in InOrder). This tail will point to the root. The linking will be done and the new root of the tree will be newRoot of the left subtree.
// in a tree, we have to change the pointers such that the root will point to its right subtree's 'newRoot'(which we will find by running the newRoot(root.right)) and the left subtree's tail(rightmost node in inOrder traversal) will point to root and the newRoot of this tree will be the newRoot of left subtree.


// Though process
// In a Binary tree
// When thinking of recursion, consider the entire tree as three nodes only. The root, the left subtree and the right subtree. Now solve the problem for this three nodes. 
// Only now, whatever we had to do with the left node we will be doing with the left subtree by calling the same function again(hence recursion) for left subtree where it will have its own root and left and right subtrees. 
// Similarly for the right subtree.
// This way we breakdown a huge tree into three nodes where we can easily think of the recursive conditions or requirements of the question.
 
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
