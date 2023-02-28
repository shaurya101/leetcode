/*
652. Find Duplicate Subtrees

Given the root of a binary tree, return all duplicate subtrees.
For each kind of duplicate subtrees, you only need to return the root node of any one of them.
Two trees are duplicate if they have the same structure with the same node values.

Example 1:
Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:
Input: root = [2,1,1]
Output: [[1]]
*/

--------------

// Approach, T: O(n), S: O(n)
// We will use a map to store subtrees as Strings, along with their frequency of occurence
// For every root we will create a string, which will have the left and right subtree and the root. If this string is already in the map once, we will add the root to the answer list
// We run a recursive loop and check from the leaf nodes upwards
// Output will be a list of nodes whose subtree has duplicates

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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        duplicates(root, ans, map);
        return ans;
    }
    private String duplicates(TreeNode root, List<TreeNode> ans, HashMap<String, Integer> map) {
        if(root == null)
            return "N";
        String subtree = duplicates(root.left, ans, map)+"$"+duplicates(root.right, ans, map)+"$"+root.val;
        if(map.getOrDefault(subtree, 0) == 1)
            ans.add(root);
        map.put(subtree, map.getOrDefault(subtree, 0)+1);
        return subtree;
    }
}
