/*
590. N-ary Tree Postorder Traversal

Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)

Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: [5,6,3,2,4,1]
*/

--------------

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
    public List<Integer> postorder(Node root) {
        List<Integer> list = new LinkedList();
        traverse(root, list); 
        return list;
    }
    private void traverse(Node root, List<Integer> list) {
        if(root == null)
            return;
        for(Node node: root.children)
            traverse(node, list);
        list.add(root.val);
    }
}

// In postOrder we want to add all the children of a root from left to right and then the root.
// We will write a func that recusively traverses the children of a root and then the root and adds them to the list.
