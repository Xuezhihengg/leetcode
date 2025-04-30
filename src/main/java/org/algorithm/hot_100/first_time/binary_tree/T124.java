package org.xjtu_learner.hot_100.first_time.binary_tree;

//题目: 124.二叉树中的最大路径和
//思路: 关键在于想清楚这里的递归函数，这个递归函数的返回值是当前树能提供的最大路径（从根节点出发），这个返回值主要是为了服务全局变量result的变更，
//      最大路径和为所有子树中各自最大路径和（不需要从根节点出发）中最大的（通过递归修改result实现），为了得到子树的各自最大路径和而
//      定义了如此的递归返回值含义
//题解: https://leetcode.cn/problems/binary-tree-maximum-path-sum/solutions/297276/shou-hui-tu-jie-hen-you-ya-de-yi-dao-dfsti-by-hyj8/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/23 好题，值得重写

public class T124 {

    int result; // 最大路径和

    public int maxPathSum(TreeNode root) {
        result = -Integer.MAX_VALUE;
        dfs(root);

        return result;
    }

    // 递归返回值含义：当前树能提供的最大路径（从根节点出发），这个返回值主要是为了服务全局变量result的变更，
    //              这样的递归不仅要关注其返回值含义，还要关注递归导致的全局状态的变更
    int dfs(TreeNode root) {

        if(root == null) return 0;

        int leftMax = dfs(root.left);
        int rightMax = dfs(root.right);

        // 更新全局状态result，如果当前子树的最大路径和大于历史最大路径和，则更新
        result = Math.max(result, leftMax + rightMax + root.val);

        // 当前树能提供的最大路径和（从根节点出发）即 max{0,root.val + leftMax, root.val + rightMax}
        // 0的目的是如果子树只能带来负收益（即leftMax<0或rightMax<0），则不应该进入
        int max = root.val + Math.max(leftMax, rightMax);
        return Math.max(max, 0);
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
        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(-1);
        root.left = node1;

        T124 t124 = new T124();
        int i = t124.maxPathSum(root);
    }
}
