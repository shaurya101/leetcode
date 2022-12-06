/*
1305. All Elements in Two Binary Search Trees

Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.

Example 1:
Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]

*/

---------

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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        // storing root1 in list1 in ascending order
        inOrder(root1, list1);
        // storing root2 in list2 in ascending order
        inOrder(root2, list2);
        // merging the two sorted lists
        return Merge(list1, list2);
    }
    private void inOrder(TreeNode root, List<Integer> list) {
        if(root == null)
            return;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
    private List<Integer> Merge(List<Integer> list1, List<Integer> list2) {
        int size1 = list1.size();
        int size2 = list2.size();
        int i=0,j=0;

        List<Integer> ans = new ArrayList<>();

        while(i<size1 && j<size2) {
            if(list1.get(i) < list2.get(j))
                ans.add(list1.get(i++));
            else
                ans.add(list2.get(j++));
        }
        while(i<size1)
            ans.add(list1.get(i++));
        while(j<size2)
            ans.add(list2.get(j++));
        return ans;
    }
}
