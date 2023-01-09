/*

144. Binary Tree Preorder Traversal

Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]

Example 2:
Input: root = []
Output: []

*/
-------------------------
  
  // Preorder is root-left-right

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
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> l1 = new LinkedList<>();
        preOrder(root, l1);
        return l1;
    }
    
    // recursive approach
    private static void preOrder(TreeNode root, LinkedList<Integer> l1)
    {
        if(root == null)
            return;
        l1.add(root.val); // add root to list
        preOrder(root.left, l1);
        preOrder(root.right, l1);
    }
}


=====================
  
// Approach - Iterative

// Create an empty stack and an empty list to store the traversal.
// Push the root node to the stack.
// While the stack is not empty; Pop the top element from the stack.
// Add the popped element's value to the traversal list.
// Push the right child of the popped element to the stack.
// Push the left child of the popped element to the stack.
// Return the traversal list.

// The reason why the right child is pushed to the stack before the left child is that we want to process the left subtree before the right subtree (since this is a pre-order traversal). Pushing the right child before the left child ensures that the left child will be processed first when it is popped from the stack.


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
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> s1 = new Stack();

        s1.push(root);
        while(!s1.isEmpty()) {
            TreeNode current = s1.pop();
            list.add(current.val);
            if(current.right != null)
                s1.add(current.right);
            if(current.left != null)
                s1.add(current.left);
        }
        return list;
    }
}
