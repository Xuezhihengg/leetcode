package org.xjtu_learner.hot_100.first_time.binary_tree;

//题目: 108.将有序数组转换为二叉搜索树
//思路: 递归实现，每次把数组从中间拆成两部分
//进阶: 现在的实现需要拷贝数组，能否在原来的数组上操作以减小空间复杂度

public class T108 {

    public TreeNode sortedArrayToBST(int[] nums) {

        if(nums.length == 0) return null;
        if(nums.length == 1) return new TreeNode(nums[0]);

        int mid = nums.length / 2;
        int rootVal = nums[mid];
        TreeNode root = new TreeNode(rootVal);


        int[] left = new int[mid];
        System.arraycopy(nums, 0, left, 0, mid);
        TreeNode leftTree = sortedArrayToBST(left);

        int[] right = new int[nums.length - mid - 1];
        System.arraycopy(nums, mid + 1, right, 0, nums.length - mid - 1);
        TreeNode rightTree = sortedArrayToBST(right);

        root.left = leftTree;
        root.right = rightTree;

        return root;
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
        int[] nums = new int[]{0, 1, 2, 3, 4, 5};

        T108 t108 = new T108();
        TreeNode treeNode = t108.sortedArrayToBST(nums);
    }
}
