/*
101. Symmetric Tree

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false

*/

=============

// Approach - Recursive - traversing same tree in two ways simlutaneously
// Basically, since we need to check the three is symetric around the root node on top, we need a way to check if the tree is same if we travese it recursively from root-left-right or root-right-left. But we cannot use a stack or array to store values and then compare the stack as structure might be different for same values also.
// Hence we need to check if it is same simultaneously in one traversal.
// We take two pointers to the root, and for one pointer we traverse root-left-right and for the other we go root-right-left. If this traversal is same then the trees are symmetric.

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
    public boolean isSymmetric(TreeNode root) {
        return same(root, root);
    }
    private boolean same(TreeNode root, TreeNode root1) {
        if(root == null && root1 == null)
            return true;
        if(root == null)
            return false;
        if(root1 == null)
            return false;
        if(root.val != root1.val)
            return false;
        return same(root.left, root1.right) && same(root.right, root1.left);
    }
}

===============
    
// Approach 2 - Iterative approach
// To have tree symmeteric, it should be same have same values and same structure if traversed in root-left-right or root-right-left manner
// We use two queus to traverse the trees in the above two manners and make sure the tree is same for both traversals
    
    
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
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();

        q1.add(root);
        q2.add(root);

        while(!q1.isEmpty() || !q2.isEmpty()) {
            if(q1.isEmpty()) // only one should not be empty, both should be empty. As what if the tree is symmetric till the end but right side has more nodes below the symmetric part
                return false;
            if(q2.isEmpty())
                return false;

            TreeNode temp1 = q1.poll();
            TreeNode temp2 = q2.poll();
            // we will check for same values while popping, and check for same structure while pushing
            // checking for value
            if(temp1.val != temp2.val)
                return false;

            // checking for structure - so that only one of the nodes(at symmetric ends) is not null
            if((temp1.left == null && temp2.right != null) || (temp1.left != null && temp2.right == null))
            return false;
             if((temp1.right == null && temp2.left != null) || (temp1.right != null && temp2.left == null))
            return false;

            // pushing nodes into queue
            if(temp1.left != null && temp2.right != null) {
                q1.add(temp1.left);
                q2.add(temp2.right);
            }
            if(temp1.right != null && temp2.left != null) {
                q1.add(temp1.right);
                q2.add(temp2.left);
            }
        }
        return true;
    }
}
