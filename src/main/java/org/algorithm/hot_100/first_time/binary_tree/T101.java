package org.xjtu_learner.hot_100.first_time.binary_tree;

//题目: 101.对称二叉树
//思路: 先实现一个辅助函数，判断两个二叉树是否对称，这可以通过递归实现
//题解: https://leetcode.cn/problems/symmetric-tree/solutions/46560/dong-hua-yan-shi-101-dui-cheng-er-cha-shu-by-user7/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/21 需要看题解，没想到递归怎么用

public class T101 {

    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return false;
        }

        return dfs(root.left, root.right);
    }

    // 判断两个树是否对称
    static boolean dfs(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }

        return dfs(t1.left, t2.right) && dfs(t1.right, t2.left);
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
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(3);
        node2.left = node5;
        node2.right = node6;

        T101 t101 = new T101();
        System.out.println(t101.isSymmetric(root));
    }
}
