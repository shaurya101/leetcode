/*
1008. Construct Binary Search Tree from Preorder Traversal

Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.

Example 1:
Input: preorder = [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

Example 2:
Input: preorder = [1,3]
Output: [1,null,3]

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
    int index = 0; // array iterator
    public TreeNode bstFromPreorder(int[] preorder) {     
        TreeNode root = makeTree(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE); // we have the root
        return root;
    }

    private TreeNode makeTree(int[] preorder, int low, int high) {
        // check if index is OutOfBounds or if array element is not in the eligible range for this position in the tree
        if(index > preorder.length-1 || preorder[index]<low || preorder[index]>high)
            return null;
        
        TreeNode root = new TreeNode(preorder[index]);
        index++;
        // we dont need to check if the next array element is will be on left or right as we are passing range and it will be placed correctly where it satisfies the range condition. 
        // A mistake would be to check if element is smaller or larger and then only call recursive func for either root.left or root.right. But then either root.left or root.right will be null and a sort of linked list will be formed as for all nodes we will have only one child.
        root.left = makeTree(preorder, low, root.val);
        root.right = makeTree(preorder, root.val, high);
        return root;
    }
}

// Approach
// This is similar to validating if a tree is a BST or not.
// To every position of a tree, we will pass down an eligible range and only create a node in that position if the current array element in inside that range.
// If yes, we create and then call the func for root.left and root.right with the respective eligible ranges. 
