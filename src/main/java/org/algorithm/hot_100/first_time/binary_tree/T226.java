package org.algorithm.hot_100.first_time.binary_tree;

//题目: 226.翻转二叉树

public class T226 {

    public TreeNode invertTree(TreeNode root) {

        if(root==null){
            return null;
        }

        TreeNode newLeft = invertTree(root.right);
        TreeNode newRight = invertTree(root.left);

        root.left = newLeft;
        root.right = newRight;

        return root;
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
}
