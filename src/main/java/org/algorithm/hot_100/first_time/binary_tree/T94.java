package org.algorithm.hot_100.first_time.binary_tree;

import java.util.ArrayList;
import java.util.List;

//题目: 94.二叉树的中序遍历

public class T94 {

    public List<Integer> inorderTraversal(TreeNode root) {


        List<Integer> result = new ArrayList<>();
        solve(root, result);

        return result;
    }

    private void solve(TreeNode root, List<Integer> result) {

        if (root == null) {
            return;
        }

        solve(root.left, result);
        result.add(root.val);
        solve(root.right, result);
    }

    public static class TreeNode {
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.right = node1;
        node1.left = node2;

        T94 t94 = new T94();
        System.out.println(t94.inorderTraversal(root));

    }
}
