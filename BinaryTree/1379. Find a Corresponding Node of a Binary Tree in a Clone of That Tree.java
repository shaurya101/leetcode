/*
1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree

Given two binary trees original and cloned and given a reference to a node target in the original tree.
The cloned tree is a copy of the original tree.
Return a reference to the same node in the cloned tree.
Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.

*/

-------------


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original == null)
            return null;
        if(original == target)
            return cloned;
     
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        TreeNode right = getTargetCopy(original.right, cloned.right, target);
        if(left != null)
            return left;
        return right;
    }
}

// If original is null, we return null. At this time, cloned is also null so we can also return null
// If original is target, we can return cloned again, as it pointing to is corresponding target node in cloned tree
// Other case:
// Else we will search on both side till we find the target.

// we check for left and right together for both trees. Left will be null if target is not there in the subtree of left(including left). Hence if left is not null, we return right. Note that left and right both will be null for subtrees that do not have target.
// We can also put condition in right instead of left -
// if(right != null) return right;
// return left;
