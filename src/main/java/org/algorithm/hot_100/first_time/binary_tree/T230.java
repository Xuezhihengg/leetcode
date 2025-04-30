package org.xjtu_learner.hot_100.first_time.binary_tree;

import java.util.ArrayList;
import java.util.List;

//题目: 230.二叉搜索树中第K小的元素
//题解: https://leetcode.cn/problems/kth-smallest-element-in-a-bst/solutions/1051120/gong-shui-san-xie-yi-ti-san-jie-pai-xu-y-8uah/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/22 这里再次出现了使用全局变量优化递归的方式

public class T230 {
    int k;
    int kth;

    //思路: 找到中序表达式，然后直接按下标找到第k小的元素
    public int kthSmallest1(TreeNode root, int k) {

        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        return list.get(k-1);
    }

    void dfs(TreeNode root, List<Integer> result){
        if(root == null) return;

        dfs(root.left,result);
        result.add(root.val);
        dfs(root.right,result);
    }

    //思路: 通过全局变量来减少递归的次数
    public int kthSmallest2(TreeNode root, int k) {
        this.k = k;
        dfsWithoutList(root);
        return kth;
    }

    void dfsWithoutList(TreeNode root){
        if(root == null || k <= 0) return;

        dfsWithoutList(root.left);
        if(--k == 0) kth = root.val;
        dfsWithoutList(root.right);
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
