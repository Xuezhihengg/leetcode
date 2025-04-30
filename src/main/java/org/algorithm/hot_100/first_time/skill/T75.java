package org.xjtu_learner.hot_100.first_time.skill;

//题目: 75.颜色分类
//思路: 遍历一遍数组，如果是0则与第一个元素交换位置并固定（不参与后续交换），如果是1则原地不动，如果是2则与最后一个元素
//      交换位置并固定

public class T75 {

    public void sortColors(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        int cur = 0;
        while (cur <= right) {
            if (nums[cur] == 1) {
                cur++;
                continue;
            }
            if (nums[cur] == 0) { // 与第一个元素交换位置并固定
                swap(nums, left++, cur++);
                continue;
            }
            if (nums[cur] == 2) { // 与最后一个元素交换位置并固定
                swap(nums, right--, cur);
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 2};
        T75 t75 = new T75();
        t75.sortColors(nums);
    }

}
