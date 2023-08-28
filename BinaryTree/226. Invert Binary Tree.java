/*
226. Invert Binary Tree

Given the root of a binary tree, invert the tree, and return its root.

Example 1:
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

Example 2:
Input: root = [2,1,3]
Output: [2,3,1]

Example 3:
Input: root = []
Output: []

*/


------------

// Approach
// T: O(N)
// S: O(N)

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}


/*

Time Complexity:
The function invertTree visits each node in the binary tree exactly once. At each node, it performs constant-time operations
(swapping left and right subtrees) and then recursively calls itself on the left and right subtrees.
Therefore, the time complexity of this solution is O(n), where 'n' is the number of nodes in the binary tree.

Space Complexity:
The space complexity is determined by the depth of the recursion stack. In the worst case, when the tree is completely unbalanced
and degenerates into a linked list, the maximum depth of the recursion stack will be 'n', which leads to a space complexity of O(n).
However, in a balanced binary tree, the space complexity can be closer to O(log n), where 'n' is the number of nodes.

*/
