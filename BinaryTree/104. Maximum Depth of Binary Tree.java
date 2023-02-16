/*
104. Maximum Depth of Binary Tree
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2

*/
--------------------------
    
// // Approach 1: T-O(n), S-O(n)

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
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1; // +1 is needed sonce root is level 1.
    }
}


-------------------------
    
// Approach 2: T-O(n), S-O(n)
// We add the root node in the queue
// Then we run a loop till the queue is empty
// We have to remove the nodes of current level and add their child nodes in the queue. This, way we add all the nodes of the next level in the queue which will be emptied in the next run
// In every addition of the nodes in current level, we increment the depth by one
// Return maxDepth when queue is emptied and all levels have been considered
    
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
    public int maxDepth(TreeNode root) {
        int maxDepth=0;
        if(root == null)
            return maxDepth;
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.add(root);
        while(!q1.isEmpty()) {
            int size = q1.size();
            for(int i=0; i<size; i++) {
                TreeNode node = q1.poll();
                if(node.left != null)
                    q1.add(node.left);
                if(node.right != null)
                    q1.add(node.right);
            }
            maxDepth++;
        }
        return maxDepth;
    }
}


-----------------------------
