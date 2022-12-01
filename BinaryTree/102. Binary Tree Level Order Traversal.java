/*
102. Binary Tree Level Order Traversal

Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

*/

--------------

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList();
        
        if(root == null)
            return list;
        
        Queue<TreeNode> q1 = new LinkedList();
        q1.add(root);
        
        while(!q1.isEmpty()) {
            List<Integer> list1 = new LinkedList();
            
            int size = q1.size(); // we will process one level at a time instead of just one node
            for(int i=0; i<size; i++) {
                TreeNode temp = q1.poll();
                list1.add(temp.val);
                if(temp.left != null)
                    q1.add(temp.left);
                if(temp.right != null)
                    q1.add(temp.right);
            }
            list.add(list1);
        }
        return list;
    }
}
