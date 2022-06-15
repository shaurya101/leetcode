/*
100. Same Tree

Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Input: p = [1,2,3], q = [1,2,3]
Output: true

Input: p = [1,2], q = [1,null,2]
Output: false
*/

----------------------------------------------
  
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) //if both null then return true
            return true;
        // at this point we know both are not null otherwise the previous if loop would return. So now if either one is null, return false as the other cannot be false and they will be different trees
        if(p == null || q == null) 
            return false;
        // Now we will check in inorder fashion. We will check if the left subtree is same or not.
        if(!isSameTree(p.left, q.left)) 
            return false;
        // If left subtree was same we check if root values are equal
        if(p.val != q.val)
            return false;
        // check if right subtree is same
        if(!isSameTree(p.right, q.right))
            return false;
        return true;
    }
}

// it is not possible to only do a inorder traversal (or any other DFS, BFS) and store the values in array and at the end compare them for two trees as even with same values the structure might be different. So we have to check simultanueosly while traversing.

// we check two things before we begin the inorder traversal.
// Firstly, if both p and q are null then return true since both are same and no further children will be there. Includes the case in which no tree exists and initial calling is with both roots=null 
// If tree exists, it will move ahead to do further checks.
// Secondly, if only one is null then we return false as tree will be different in that case.

// Now, we have done the initial checks on our currect root. Now we can proceed with inorder traversal for our currect root.
// Using recursion, this way we will check for every root simultaneously if they are same for both tree or not.
// We have put - isSameTree(p.left, q.left) ie the left subtree in if condition since if the left subtree is not same we do not need to check the rest of tree as the tree cannot be same.

----------------------
 //  2nd approach 
  
public boolean isSameTree(TreeNode p, TreeNode q) {
    // recurision method
    if (p == null && q == null) return true;
    if (p == null && q != null || p != null && q == null) return false;
    if (p.val != q.val) return false;
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}


