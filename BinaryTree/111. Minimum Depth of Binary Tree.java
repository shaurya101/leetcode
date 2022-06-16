/*

111. Minimum Depth of Binary Tree

Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5

*/


----------------------


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
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
         int depth=1; // if root != 0, depth = 1 atleast
        return count(root, depth);
    }
    private int count(TreeNode root, int depth){
        if(root.left == null && root.right == null) // check if children nodes of root are leaf nodes. If both nodes are leaf node, return depth 
            // we do not return depth if only single child is leaf node as the tree does not end. Even if one child is non leaf the tree continues. 
            return depth;
        //else increase depth by 1
        depth++;
        // recursively check for left and right tree depths and store them in x and y. Later we will choose the lesser depth amongst x and y and return that as depth
        // assume x and y to be greatest integer as we are looking for less value; 
        int x=Integer.MAX_VALUE,y=Integer.MAX_VALUE;
        if(root.left != null)
            x = count(root.left, depth);
        if(root.right != null)
            y = count(root.right, depth);
        // return the lesser of x & y
        return x<y?x:y;
    }
}

// We use recursion to check for depth.
// End of tree means both children nodes are null. In this case we can return depth. 
// But if both children are not null, then we compare the left and right subtree's depth. We check for the depth of these two subtrees and return the lesser depth among the two.

