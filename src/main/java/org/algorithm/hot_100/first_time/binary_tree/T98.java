package org.algorithm.hot_100.first_time.binary_tree;

import java.util.ArrayList;
import java.util.List;

//题目: 98.验证二叉搜索树

public class T98 {

    //思路: 找到其中序表达式判断是否有序
    public boolean isValidBST1(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        // 判断list是否有序
        return isAscending(list);

    }

    boolean isAscending(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) return false;
        }
        return true;
    }

    public void dfs(TreeNode root, List<Integer> result) {
        if (root == null) return;

        dfs(root.left, result);
        result.add(root.val);
        dfs(root.right, result);
    }


    //思路: 递归
    public boolean isValidBST2(TreeNode root) {

        if (root == null) return true;

        // 递归判断左子树是不是平衡二叉树
        if (!isValidBST2(root.left)) return false;

        // 递归判断右子树是不是平衡二叉树
        if (!isValidBST2(root.right)) return false;

        int rootVal = root.val;

        boolean leftFlag = true;
        if (root.left != null) {
            // 找到左子树的最大值
            int leftMax = findMax(root.left);
            leftFlag = leftMax < rootVal;
        }

        boolean rightFlag = true;
        if(root.right != null) {
            // 找到右子树的最小值
            int rightMin = findMin(root.right);
            rightFlag = rootVal < rightMin;
        }

        // 判断当前树是不是平衡二叉树

        return leftFlag && rightFlag;
    }

    int findMax(TreeNode root) {

        if (root == null) return -Integer.MAX_VALUE;

        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp.val;
    }

    int findMin(TreeNode root) {

        if (root == null) return Integer.MAX_VALUE;

        TreeNode temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp.val;
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
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);

        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(6);

        node2.left = node3;
        node2.right = node4;

        T98 t98 = new T98();
        boolean validBST = t98.isValidBST2(root);
    }
}
