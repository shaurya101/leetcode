/*
2331. Evaluate Boolean Binary Tree

You are given the root of a full binary tree with the following properties:

Leaf nodes have either the value 0 or 1, where 0 represents False and 1 represents True.
Non-leaf nodes have either the value 2 or 3, where 2 represents the boolean OR and 3 represents the boolean AND.
The evaluation of a node is as follows:

If the node is a leaf node, the evaluation is the value of the node, i.e. True or False.
Otherwise, evaluate the node's two children and apply the boolean operation of its value with the children's evaluations.
Return the boolean result of evaluating the root node.

A full binary tree is a binary tree where each node has either 0 or 2 children.

A leaf node is a node that has zero children.

*/

------------
// Both approaches with recurion. This one is more simplified.
    
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
    public boolean evaluateTree(TreeNode root) {
        if(root.val==0)
            return false;
        if(root.val==1)
            return true;
        
        if(root.val==2) return evaluateTree(root.left) || evaluateTree(root.right);
        return evaluateTree(root.left) && evaluateTree(root.right);
    }
}
    
------------

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
    public boolean evaluateTree(TreeNode root) {
        if(root.val == 2 || root.val == 3) {
            evaluateTree(root.left);
            evaluateTree(root.right);
        }
        
         if(root.val == 2 && (root.left.val == 1 || root.right.val == 1))
            root.val = 1;
        else if(root.val == 3 && root.left.val == 1 && root.right.val == 1)
            root.val = 1;
        else if(root.val == 0)
            return false;
        else if(root.val == 1)
            return true;
        else // if root.val =2,3 and the child nodes make it 0
            root.val = 0;

        if(root.val == 1)
            return true;
        return false;
    }
}

// if root.val = 2 or 3, we will evaluate left and right recursively so we will have left and right = 0 or 1
// once left and right are evaluated we will calculate root.val according to conditions
