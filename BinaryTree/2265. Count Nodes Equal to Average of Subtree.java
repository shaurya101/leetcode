/*
2265. Count Nodes Equal to Average of Subtree

Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

Note:
The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.

Example 1:
Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation: 
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.

*/

-------------

// Approach - T: O(N), S: O(h), ie height ranging from O(log N) to O(N)


class Solution {
    public int averageOfSubtree(TreeNode root) {
        return fn(root)[0];
    }

    private int[] fn(TreeNode root) {
        if (root.left == null && root.right == null) {
            // Base case: If the current node is a leaf, return its count as 1, sum value(root.val itself) and subtree count as 1.
            return new int[] { 1, root.val, 1 };
        }

        int count = 0;
        int sumSubtree = root.val;
        int countSubtree = 1;

        if (root.left != null) {
            // Recursively process the left subtree.
            int[] left = fn(root.left);
            count += left[0];
            sumSubtree += left[1];
            countSubtree += left[2];
        }
        if (root.right != null) {
            // Recursively process the right subtree.
            int[] right = fn(root.right);
            count += right[0];
            sumSubtree += right[1];
            countSubtree += right[2];
        }

        if (sumSubtree / countSubtree == root.val) {
            // Check if the average of the subtree equals the current node's value. We round down in case of decimal values
            count++;
        }

        // Return an array with the count of times the condition is true, the sum of the subtree, and the subtree count.
        return new int[] { count, sumSubtree, countSubtree };
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
    public int averageOfSubtree(TreeNode root) {
        int[] stats = average(root); // gets int[]{sum, numberOfNodes, countOfAverage}.
        return stats[2]; // returns 
    }
    private int[] average(TreeNode root) {
        if(root == null)
            return new int[]{0,0,0}; // int[]{sum, numberOfNodes, countOfAverage}. We do not need to pass this array in parameters as through recursion the function will come to this point first and create this array, and we will update this array when we move up the subtrees in backtracking recursion
        int[] leftStats = average(root.left); // gets stats of left subtree
        int[] rightStats = average(root.right);// gets stats of right subtree
        int sum = leftStats[0] + rightStats[0] + root.val;
        int count = leftStats[1] + rightStats[1] + 1; // adding 1 to number of nodes as root is added
        
        if(sum/count == root.val)
            return new int[]{sum, count, leftStats[2] + rightStats[2]+1}; // adding one to averageCount if condition satisfied 
        return new int[]{sum, count, leftStats[2] + rightStats[2]};
    }
}
