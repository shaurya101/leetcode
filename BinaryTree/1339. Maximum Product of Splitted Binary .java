/*
1339. Maximum Product of Splitted Binary 

Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
Note that you need to maximize the answer before taking the mod and not after taking it.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

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
    long maxProd = Integer.MIN_VALUE; // max product
    long totalSum = Integer.MIN_VALUE; // sum of entire tree
    public int maxProduct(TreeNode root) {
        totalSum = dfsSum(root);
        // System.out.println(totalSum);
        dfsSum(root);
        int prod =  (int)(maxProd % (Math.pow(10, 9)+7));
        return prod;
    }
   
    private int dfsSum(TreeNode root) {
        if(root == null)
            return 0;
        int sum = root.val + dfsSum(root.left) + dfsSum(root.right); // correct way
        
        /* incorrect - as one node will be added multiple times recursively 
        sum += root.val;
        sum += dfs(root.left, sum);
        sum += dfs(root.right, sum);
        */

        maxProd = Math.max(maxProd, (totalSum - sum)*sum); //when finding totalSum, ie in the first run of this function maxProd value does not matter. In the second run it will get upfated correctly
        return sum;
    }
}

// find the sum of entire tree - totalSum
// then traverse the tree for all nodes and calc the sum of the subtree of that node. The sum of rest of the tree would be 'totalSum-subtreeSum'.
// calc product and update maxProduct if product is max
