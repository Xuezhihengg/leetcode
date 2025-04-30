package org.xjtu_learner.hot_100.first_time.binary_tree;

//题目: 114.二叉树展开为链表
//思路: 递归


public class T114 {

    public void flatten(TreeNode root) {

        if(root == null) return;

        // 递归展平左子树
        flatten(root.left);

        // 递归展平右子树
        flatten(root.right);

        // 将本树展平为链表
        TreeNode tail = findTail(root.left);

        if(tail == null){   // 左子树为空，不需要展平
            return;
        }

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        tail.right = temp;
    }

    private TreeNode findTail(TreeNode root) {

        if(root == null) return null;

        TreeNode temp = root;
        while (temp.right != null){
            temp = temp.right;
        }
        return temp;
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
        TreeNode node2 = new TreeNode(5);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(6);
        node2.right = node5;

        T114 t114 = new T114();
        t114.flatten(root);
    }
}
