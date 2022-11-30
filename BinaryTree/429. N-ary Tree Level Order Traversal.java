/*
429. N-ary Tree Level Order Traversal

Given an n-ary tree, return the level order traversal of its nodes' values.
Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: [[1],[3,2,4],[5,6]]

Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]

*/

------------


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new LinkedList();
        if(root == null)
            return list;
        
        Queue<Node> q1 = new LinkedList();
        q1.add(root);
        
        while(!q1.isEmpty()) {
            List<Integer> list1 = new LinkedList();
            
            int size = q1.size();
            // we will take out all the nodes of a level instead of processing one Node only, as we want to make it level-wise. We do this through for loop. Similar to question 1302.
            for(int i=0; i<size; i++) {
                Node temp = q1.poll();
                list1.add(temp.val);
                for(Node child : temp.children)
                    q1.add(child);
            }
            list.add(list1);
        }
        
        return list;
    }
}
