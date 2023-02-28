package exercises.treess;

import java.util.ArrayList;
import java.util.List;

public class Treess {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return new ArrayList<>();
        traverserPreOrder(root, list);
        return list;
    }

    public void traverserPreOrder(Node root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        root.children.forEach((next) -> traverserPreOrder(next, list));
    }

    /**
     * Given the root of a binary tree, return the level
     * order traversal of its nodes' values. (i.e., from left to right, level by level).
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[9,20],[15,7]]
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        traverseLevelOrder(root, list, 0);
        return list;
    }

    public void traverseLevelOrder(TreeNode root, List<List<Integer>> list, int lvl) {
        if (root == null) return;
        boolean shouldCreteInner = list.size() <= lvl;
        List<Integer> innerList = shouldCreteInner ? new ArrayList<>() : list.get(lvl);
        innerList.add(root.val);
        if (shouldCreteInner) list.add(innerList); else list.set(lvl, innerList);
        traverseLevelOrder(root.left, list, lvl + 1);
        traverseLevelOrder(root.right, list, lvl + 1);
    }

    // Definition for a Node.
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
