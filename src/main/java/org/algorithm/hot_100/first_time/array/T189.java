package org.xjtu_learner.hot_100.first_time.array;

//题目: 189.轮转数组

// !*! 2025/4/30 第三种方法值得看一看

public class T189 {

    //思路: 原地解决，每一步所有元素都向后移一位，并将最后一个元素置与头部，空间复杂度O(n^2)，空间复杂度O(1)
    public void rotate1(int[] nums, int k) {

        for (int i = 0; i < k; i++) {
            int last = nums[nums.length - 1];
            // 每一个元素向后移动一位，从倒数第二个开始
            for (int j = nums.length - 2; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = last;
        }
    }

    //思路: 在上面的基础上改进，一次性把元素移动到位（一次性移动k位），时间复杂度O(n)，空间复杂度O(k)
    public void rotate2(int[] nums, int k) {

        k %= nums.length;   // 用于解决nums.length() < k的情况

        int[] lastK = new int[k];
        System.arraycopy(nums, nums.length - k, lastK, 0, k);
        for (int i = nums.length - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }
        System.arraycopy(lastK, 0, nums, 0, k);
    }

    //思路: 见题解，是一种取巧的方法，没有通用性，时间复杂度O(n)，空间复杂度O(1)
    //题解: https://leetcode.cn/problems/rotate-array/solutions/551039/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/?envType=study-plan-v2&envId=top-100-liked
    public void rotate3(int[] nums, int k) {

        k %= nums.length;   // 用于解决nums.length() < k的情况

        // 先将nums整体翻转，使用双指针法，时间复杂度为O(n)
        reverse(nums, 0, nums.length - 1);
        // 再翻转[0,k-1]部分
        reverse(nums, 0, k - 1);
        // 再翻转[k,len-1]部分
        reverse(nums, k, nums.length - 1);
    }

    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            // 交换start和end元素
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        T189 t189 = new T189();
        t189.rotate3(nums, 4);
    }
}
