package org.algorithm.hot_100.first_time.binary_tree;

//题目: 105.从前序与中序遍历序列构造二叉树
//思路: 先序数组的第一个元素一定为根节点，依据根节点在中序数组分出左子树与右子树，然后递归解决
//      时间复杂度为O(n^2)，最坏情况下二叉树是一条链，需要递归 O(n) 次，每次都需要 O(n) 的时间查找 preorder[0] 和复制数组。
//题解: https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solutions/2646359/tu-jie-cong-on2-dao-onpythonjavacgojsrus-aob8/?envType=study-plan-v2&envId=top-100-liked
//进阶: 该算法还有以下两个优化点
//            -使用哈希表预处理preorder元素到inorder的下标映射
//            -递归函数可传入数组下标，避免复制数组

// !*! 2025/4/22 没思路，看过思路会写


public class T105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder.length == 0) return null;
        if(preorder.length == 1) return new TreeNode(preorder[0]);

        // 先序数组的第一个元素一定为根节点
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);

        // 依据根节点在中序数组分出左子树与右子树
        for (int i = 0; i < inorder.length; i++) {
            if(inorder[i] == rootVal){
                // 递归构造左子树
                int[] leftPreorder = new int[i];
                int[] leftInorder = new int[i];
                System.arraycopy(preorder, 1, leftPreorder, 0, i);
                System.arraycopy(inorder, 0, leftInorder, 0, i);
                root.left = buildTree(leftPreorder, leftInorder);

                // 递归构造右子树
                int rlen = inorder.length - i - 1;
                int[] rightPreorder = new int[rlen];
                int[] rightInorder = new int[rlen];
                System.arraycopy(preorder, 1 + i, rightPreorder, 0, rlen);
                System.arraycopy(inorder, i + 1, rightInorder, 0, rlen);
                root.right = buildTree(rightPreorder, rightInorder);

                return root;
            }

        }

        return root;
    }

    int findIndex(int[] nums, int num) {
        for (int i = 0; i < nums.length; i++) {
            if (num == nums[i]) {
                return i;
            }
        }
        return -1;
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
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};

        T105 t105 = new T105();
        TreeNode treeNode = t105.buildTree(preorder, inorder);
    }
}
