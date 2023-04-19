/*
1372. Longest ZigZag Path in a Binary Tree

You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
Change the direction from right to left or from left to right.
Repeat the second and third steps until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.

Example 1:
Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

*/


-------------------

// Approach T: O(N), S:(h) ; h is height of tree but in worst case S: O(N)
  

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
    private int maxLen;
    
    public int longestZigZag(TreeNode root) {
        maxLen = 0;
        dfs(root.left, 1, true);
        dfs(root.right, 1, false);
        return maxLen;
    }
    private void dfs(TreeNode root, int len, boolean isLeft) {
        if(root == null)
            return;
        
        maxLen = Math.max(maxLen, len); // update maxLen at each node

        if(isLeft) { // If we came from a left child, go right
            dfs(root.right, len+1, false);
            dfs(root.left, 1, true); // Start a new path from left child
        }
        else {  // If we came from a right child, go left
            dfs(root.left, len+1, true);
            dfs(root.right, 1, false); // Start a new path from right child
        }
    }
}
