package org.algorithm.hot_100.first_time.skill;

//题目: 31. 下一个排列
//思路: 1）由于按字典排序，「下一个排列」一定比当前排列的数值大，为了使数值增大，需要将一个左边的较小数与右边的较大数进行交换
//      2）交换后得到的排列只是当前排列后面的排列，不一定是当前排列的「下一个排列」，所以要紧缩，即让交换前后数值变化尽可能小
//      3）为了了数值变化尽可能小需要这样做：
//                        - 较小数尽可能靠右（这样涉及变化的单位更小）
//                        - 较大数尽可能接近较小数（让增大的幅度尽可能小）
//                        - 交换完成后将重拍较大数右边的数
//题解: https://leetcode.cn/problems/next-permutation/solutions/479151/xia-yi-ge-pai-lie-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/28 没想出来，值得重刷，这种思路记住就可以了吧


public class T31 {

    public void nextPermutation(int[] nums) {

        if(nums.length == 1) return;

        // 先倒序遍历找到左边的较小数
        int smallerIdx = nums.length - 1;
        while (nums[smallerIdx - 1] >= nums[smallerIdx]) {
            smallerIdx--;
            if (smallerIdx == 0) {  // 说明nums是倒序的，直接返回其正序排列
                reverse(nums, 0, nums.length - 1);
                return;
            }
        }
        smallerIdx--;

        int smaller = nums[smallerIdx];

        // 再倒序遍历找到右边的较大数
        int biggerIdx = nums.length - 1;
        while (nums[biggerIdx] <= smaller) {
            biggerIdx--;
        }

        // 交换较小数与较大数
        swap(nums, smallerIdx, biggerIdx);

        // 将现在smallerIdx以右的元素重排序，变成升序，由于现在smallerIdx以右的元素本身就是逆序的，所以重排为升序没必要直接
        // 排序，通过双指针就可以实现
        reverse(nums, smallerIdx + 1, nums.length - 1);
    }

    void swap(int[] nums, int i, int j) {
        // 交换较小数与较大数
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }


    public static void main(String[] args) {
        T31 t31 = new T31();
        t31.nextPermutation(new int[]{1});
    }
}
