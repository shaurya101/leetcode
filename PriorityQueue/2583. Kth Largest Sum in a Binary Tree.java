/*
2583. Kth Largest Sum in a Binary Tree

You are given the root of a binary tree and a positive integer k.
The level sum in the tree is the sum of the values of the nodes that are on the same level.
Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.
Note that two nodes are on the same level if they have the same distance from the root.

Example 1:


Input: root = [5,8,9,2,1,3,7,4,6], k = 2
Output: 13
Explanation: The level sums are the following:
- Level 1: 5.
- Level 2: 8 + 9 = 17.
- Level 3: 2 + 1 + 3 + 7 = 13.
- Level 4: 4 + 6 = 10.
The 2nd largest level sum is 13.

*/

--------------------------

// Approach 1 - Using Priority Queue; T: O(N), S: O(N)
// We traverse the tree levelwise and store the levelSum in maxHeap
// If maxHeap.siz() < k, return -1
// Else take out k-1 elements from maxHeap and then return the maxHeap element which will be the kth largest sum

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
    public long kthLargestLevelSum(TreeNode root, int k) {
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.add(root);
        
        while(!q1.isEmpty()) {
            int qSize = q1.size();
            long levelSum = 0;

            for(int i=0; i<qSize; i++) {
                TreeNode temp = q1.poll();
                levelSum += temp.val;
                if(temp.left != null)
                    q1.add(temp.left);
                if(temp.right != null)
                    q1.add(temp.right);
            }
            if(levelSum != 0)
                maxHeap.add(levelSum);
        }

        if(maxHeap.size() < k)
            return -1;
        
        for(int i=1; i<k; i++)
            maxHeap.remove();
        return maxHeap.remove();
        
    }
}

-----------------
  
// Aproach 2 - Using Arraylist; T: O(N) [Not nlogn as we are only sorting the levelsum, and not all the nodes], S: O(N).
// We do BFS traversal of tree and store the level sum in a array list.
// If list size < k, return -1
// Else, we sort the list and then return kth largest sum
  
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
    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> q1 = new LinkedList<>();
        ArrayList<Long> list = new ArrayList<>();
        q1.add(root);
        
        while(!q1.isEmpty()) {
            int qSize = q1.size();
            long levelSum = 0;
            for(int i=0; i<qSize; i++) {
                TreeNode temp = q1.poll();
                levelSum += temp.val;
                if(temp.left != null)
                    q1.add(temp.left);
                if(temp.right != null)
                    q1.add(temp.right);
            }
            if(levelSum != 0)
                list.add(levelSum);
        }
        
        System.out.println(list.size());
        if(list.size() < k)
            return -1;

        Collections.sort(list, Collections.reverseOrder());
        return list.get(k-1);
        
    }
}
