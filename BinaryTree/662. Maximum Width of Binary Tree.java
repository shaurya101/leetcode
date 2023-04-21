/*
662. Maximum Width of Binary Tree

Given the root of a binary tree, return the maximum width of the given tree.
The maximum width of a tree is the maximum width among all levels.
The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
It is guaranteed that the answer will in the range of a 32-bit signed integer.

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).

*/


-----------------------

  
// Approach - T: O(N), S: O(N)

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
    public int widthOfBinaryTree(TreeNode root) {
        // store the node and its indexes, similar to a manner in heaps
        Queue<Pair<TreeNode, Integer>> q1 = new LinkedList<>();
        q1.add(new Pair<>(root, 1));
        int maxWidth = 0;

        while(!q1.isEmpty()) {
            int size = q1.size(); // # of nodes in a level
            int leftIndex = q1.peek().getValue(); // starting index
            int rightIndex=leftIndex; // end index

            for(int i=0; i<size; i++) {
                Pair<TreeNode, Integer> pair = q1.poll();
                TreeNode node = pair.getKey();
                rightIndex = pair.getValue(); // we keep updating rightIndex till the end node

                if(node.left != null)
                    q1.add(new Pair<>(node.left, 2*rightIndex));
                if(node.right != null)
                    q1.add(new Pair<>(node.right, 2*rightIndex+1));
            }
            maxWidth = Math.max(maxWidth, rightIndex-leftIndex+1);
        }
        return maxWidth;
    }
}
