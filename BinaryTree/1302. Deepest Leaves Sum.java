/*
1302. Deepest Leaves Sum

Given the root of a binary tree, return the sum of values of its deepest leaves.

Example 1:
Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15

Example 2:
Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 19

*/

---------------

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
    public int deepestLeavesSum(TreeNode root) {
        if(root == null)
            return 0;
        
        Queue<TreeNode> q1 = new LinkedList();
        q1.add(root);
        
        int level_sum=0; // sum of a level
        while(!q1.isEmpty()) {
            level_sum = 0;
            
            int size = q1.size();
            for(int i=0; i<size; i++) {
                TreeNode currentNode = q1.poll();
                level_sum += currentNode.val;
                if(currentNode.left != null)
                    q1.add(currentNode.left);
                if(currentNode.right != null)
                    q1.add(currentNode.right);
            }
        }
        return level_sum;
    }
}

// we calculate the sum of each level using BFS
// we modify the BFS traversal so we don't just process one node at a time but the entire level's node. We achieve this through a for loop.
// note we are not calculating the leaf nodes sum, but the sum of the deepest or the last level leaf nodes only.
