package org.xjtu_learner.hot_100.first_time.binary_tree;

import java.util.*;

//题目: 102.二叉树的层序遍历
//思路: 广度优先搜索，使用队列

// !*! 2024/4/22 对广度优先搜索还不太熟悉，尤其不明白如何把层的关系区分开

public class T102 {

    // 广度优先搜索
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {

            // 先确定该层会有多少个节点
            int n = queue.size();
            List<Integer> list = new ArrayList<>(n);
            // 将该层节点全部拓展
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.remove();
                list.add(node.val);
                // 拓展其左右节点（左节点先入）
                if(node.left!=null){
                        queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            result.add(list);
        }
        return result;
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
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        node2.left = node3;
        node2.right = node4;

        T102 t102 = new T102();
        List<List<Integer>> lists = t102.levelOrder(root);
    }
}
