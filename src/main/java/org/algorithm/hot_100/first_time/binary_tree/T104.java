package org.xjtu_learner.hot_100.first_time.binary_tree;

//题目: 104.二叉树的最大深度

public class T104 {

    public int maxDepth(TreeNode root) {

        if (root == null) return 0;

        int leftMaxDeep = maxDepth(root.left);
        int rightMaxDeep = maxDepth(root.right);

        return Math.max(leftMaxDeep, rightMaxDeep) + 1;
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
