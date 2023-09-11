/*
863. All Nodes Distance K in Binary Tree

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all
nodes that have a distance k from the target node.

You can return the answer in any order. 


Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:
Input: root = [1], target = 1, k = 3
Output: []

*/


-----------

// Approach -  T: O(N), S: O(N)


class Solution {
    // Map to store the parent nodes of each node in the binary tree
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // Build the parentMap to keep track of parent nodes
        buildParentMap(root, null);

        // Queue for BFS
        Queue<TreeNode> queue = new LinkedList<>();
        // Set to keep track of visited nodes
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target); // Start BFS from the target node
        visited.add(target);

        int distance = 0; // Initialize the distance

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (distance == K) {
                List<Integer> result = new ArrayList<>();
                for (TreeNode node : queue) {
                    result.add(node.val);
                }
                return result;
            }

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Explore left child
                if (node.left != null && visited.add(node.left)) {
                    queue.offer(node.left);
                }
                // Explore right child
                if (node.right != null && visited.add(node.right)) {
                    queue.offer(node.right);
                }
                // Explore the parent node
                TreeNode parent = parentMap.get(node);
                if (parent != null && visited.add(parent)) {
                    queue.offer(parent);
                }
            }

            distance++;
        }

        return Collections.emptyList(); // No nodes found at distance K
    }

    // Helper method to build the parentMap
    private void buildParentMap(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        parentMap.put(node, parent);
        buildParentMap(node.left, node);
        buildParentMap(node.right, node);
    }
}
