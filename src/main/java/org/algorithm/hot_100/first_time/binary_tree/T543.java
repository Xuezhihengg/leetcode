package org.xjtu_learner.hot_100.first_time.binary_tree;

//题目: 543.二叉树的直径
//题解: https://leetcode.cn/problems/diameter-of-binary-tree/solutions/141445/liang-chong-si-lu-shi-yong-quan-ju-bian-liang-yu-b/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/21 第二种使用全局变量的方法不太理解

public class T543 {

    int diameter;

    //思路: 二叉树的最长路径=max｛左子树的直径，右子树的直径，左子树的深度+2+右子树的深度｝，这个递归返回的就是树的直径
    public int diameterOfBinaryTree1(TreeNode root) {

        if (root == null) return 0;

        // 先找到左右子树的直径
        int leftDiameter = diameterOfBinaryTree1(root.left);
        int rightDiameter = diameterOfBinaryTree1(root.right);

        // 再找到左右子树到根节点的最大深度
        int leftMaxDepth = maxDepth(root.left) - 1;
        int rightMaxDepth = maxDepth(root.right) - 1;

        // 比较（左子树最大深度 + 1 +右子树最大深度）、左子树直径以及右子树直径
        return Math.max(Math.max(leftDiameter, rightDiameter), leftMaxDepth + 2 + rightMaxDepth);
    }

    //思路: 这个递归返回的是树的最大深度，在递归的过程中通过全局变量diameter来保存直径
    //      这样做相比第一种方法，把两个子问题的递归简化为了一个
    public int diameterOfBinaryTree2(TreeNode root) {
        if (root == null) return 0;

        diameter = 1;
        maxDepthWithDiameter(root);
        return diameter - 2;

    }

    public int maxDepthWithDiameter(TreeNode root) {

        if (root == null) return 0;

        int leftMaxDepth = maxDepthWithDiameter(root.left);
        int rightMaxDepth = maxDepthWithDiameter(root.right);

        // 走到这里，两个递归函数对diameter的修改已经完成，diameter现在是考虑了两个子树的直径
        // 现在只需要判断考虑根节点的情况是否更优

        diameter = Math.max(diameter, leftMaxDepth + 2 + rightMaxDepth);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

    static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);

        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
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
