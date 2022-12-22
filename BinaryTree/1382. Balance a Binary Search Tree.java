/*
1382. Balance a Binary Search Tree

Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.
A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

Example 1:
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.

*/

=================
// concept - creating balanced binary tree from unbalanced binary tree

// Approach
// we store the nodes of the tree in a array list in inorder fashion(inorder traversal of binary search tree will be of increasing order)
// we will then create a tree out of this list in the way we do binary search
// That way each time the list will be divided with equal nodes on either side or one node more on one side; so we can be sure that depth will either be same or just one level more. 

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
    // List to store nodes of tree in Inorder fashion
    List<TreeNode> list = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        if(root == null)
            return null;
        inOrderList(root); // store nodes of tree in list
        return buildTree(0, list.size()-1); // return root of new tree
    }

    private void inOrderList(TreeNode root) {
        if(root == null)
            return;
        inOrderList(root.left);
        list.add(root);
        inOrderList(root.right);
    }
    private TreeNode buildTree(int low, int high) {
        if(low>high)
            return null;
        // mid index of relevant (part of) list for this recursive call
        int mid = high-(high-low)/2;
        // creating root
        TreeNode root = list.get(mid);
        // calling function for its left and right child
        root.left = buildTree(low, mid-1);
        root.right = buildTree(mid+1, high);
        return root;
    }
}
