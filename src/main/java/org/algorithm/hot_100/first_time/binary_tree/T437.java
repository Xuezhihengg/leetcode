package org.algorithm.hot_100.first_time.binary_tree;

import java.util.HashMap;
import java.util.Map;

//题目: 437.路径总和II
//思路: 二叉树版的T560，使用前缀和加哈希表解决

// !*! 2024/4/22 比较难，值得重写

public class T437 {
    long cur;    // 当前前缀和
    int count;  // 总路径数
    Map<Long, Integer> map;   // 前缀和 -> 出现次数

    public int pathSum(TreeNode root, int targetSum) {
        cur = 0;
        count = 0;
        map = new HashMap<>();
        map.put(0L, 1);
        dfs(root, targetSum);

        return count;
    }

    // 考察以当前节点为根的二叉树的总路径和
    private void dfs(TreeNode root, int targetSum) {

        if (root == null) {
            return;
        }

        cur += root.val;
        // 计算需要的前缀和
        long need = cur - targetSum;

        if (map.containsKey(need)) {
            count += map.get(need);
        }

        // 该前缀和计数加1
        map.put(cur, map.getOrDefault(cur, 0) + 1);

        dfs(root.left, targetSum);
        dfs(root.right, targetSum);

        // 回溯
        map.put(cur, map.get(cur) - 1);
        cur -= root.val;
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

//        TreeNode root = new TreeNode(10);
//        TreeNode node1 = new TreeNode(5);
//        TreeNode node2 = new TreeNode(-3);
//
//        root.left = node1;
//        root.right = node2;
//
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(2);
//
//        node1.left = node3;
//        node1.right = node4;
//
//        TreeNode node5 = new TreeNode(3);
//        TreeNode node6 = new TreeNode(-2);
//
//        node3.left = node5;
//        node3.right = node6;
//
//        TreeNode node7 = new TreeNode(1);
//
//        node4.right = node7;
//
//        TreeNode node8 = new TreeNode(11);
//
//        node2.right = node8;

        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(-2);
        TreeNode node2 = new TreeNode(-3);

        root.left = node1;
        root.right = node2;

        T437 t437 = new T437();
        int i = t437.pathSum(root, -1);
    }
}
