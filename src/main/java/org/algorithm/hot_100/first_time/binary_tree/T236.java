package org.xjtu_learner.hot_100.first_time.binary_tree;

import java.util.*;

//题目: 236.二叉树的最近公共祖先

// !*! 2024/4/23 lowestCommonAncestor1为自己实现，lowestCommonAncestor2直接递归，其递归返回值不直接是最近公共祖先，不太好理解

public class T236 {

    //思路: 找到从根节点到p的路径（包含p）-> 找到从根节点到q的路径（包含q）-> 由于所有的val不同，两路径相同的部分即为公共祖先节点
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null) return null;

        // 找到从根节点到p的路径（包含p）
        List<TreeNode> pPath = new ArrayList<>();
        dfs(root, p, pPath);

        // 找到从根节点到q的路径（包含q）
        List<TreeNode> qPath = new ArrayList<>();
        dfs(root, q, qPath);

        // 由于所有的val不同，两路径相同的部分即为公共祖先节点

        Set<TreeNode> set = new HashSet<>();
        // 先将路径短的存入set
        if (pPath.size() > qPath.size()) {
            set.addAll(qPath);
            // 使用较长的逆序查找set是否有存在
            for (int i = pPath.size() - 1; i > 0; i--) {
                if(set.contains(pPath.get(i))){
                    return pPath.get(i);
                }
            }
        }else {
            // pPath.size() <= qPath.size()
            set.addAll(pPath);
            for (int i = qPath.size() - 1; i > 0; i--) {
                if (set.contains(qPath.get(i))) {
                    return qPath.get(i);
                }
            }
        }

        return root;
    }


    boolean dfs(TreeNode root, TreeNode target, List<TreeNode> result) {

        if (root == null) return false;

        result.add(root);

        boolean leftFind = false;
        boolean rightFind = false;
        if (root.val == target.val) {
            return true;
        } else {
            if (root.left != null) {
                leftFind = dfs(root.left, target, result);
            }

            if (root.right != null && !leftFind) {
                rightFind = dfs(root.right, target, result);
            }
        }

        // 没找到则回溯
        if (!leftFind && !rightFind) {
            result.remove(result.size() - 1);
        }

        return leftFind || rightFind;
    }


    //思路: 直接递归，递归的结果是最近公共祖先、p（如果找到p）、q（如果找到q）、null（该子树中压根不存在p或者q）
    //      本题的递归返回值含义并不直接是最近公共祖先，需要保证p和q一定在二叉树中才能保证找到最近公共祖先
    //题解: https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        // 这个终止条件中root == p || root == q很重要，如果找到了p或q也会返回，也就是说返回值不为空不一定是找到了
        if(root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);


        if(left==null) return right;
        if(right==null) return left;
        return root;

//        上面三行代表了四种情况：
//        if(left == null && right == null) return null; // 1.左右都没找到
//        if(left == null) return right; // 3.左边没找到（左子树中压根不存在p或者q）
//        if(right == null) return left; // 4.右边没找到（右子树中压根不存在p或者q）
//        return root; // 2. if(left != null and right != null) p，q在root的异侧，root为最近公共祖先
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);

        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);

        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(4);

        node4.left = node5;
        node4.right = node6;

        TreeNode node7 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);

        node2.left = node7;
        node2.right = node8;

        T236 t236 = new T236();
        TreeNode treeNode = t236.lowestCommonAncestor2(root, node1, null);
    }
}
