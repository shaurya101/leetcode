/*
1315. Sum of Nodes with Even-Valued Grandparent

Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.
A grandparent of a node is the parent of its parent if it exists.

Example 1:
Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.

*/

----------------

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
    public int sumEvenGrandparent(TreeNode root) {
        return helper(root, 0);
    }
    
    
    private int helper(TreeNode root, int sum) {
        // if node is null
        // if node is odd, we still have to dive in it's subtrees as in the subtree they may have a even grandparent. So we do not check for odd here
        if(root == null)
            return 0;
        
        sum += helper(root.left, sum) + helper(root.right, sum); // gets sum of left subtree and right subtree
        
        // now we calc sum for this tree, ie root
        // 1 node can have 4 grandchildren.
        // We will check if a node is even. If yes, we will calculate sum of its grandchildren.
        if(root.left != null && root.val%2 == 0) {
            if(root.left.left != null)
            sum += root.left.left.val;
            if(root.left.right != null)
            sum += root.left.right.val;
        }
       
        if(root.right != null && root.val%2 == 0) {
            if(root.right.left != null)
            sum += root.right.left.val;
            if(root.right.right != null)
            sum += root.right.right.val;
        }
        
        return sum;
    }
}
