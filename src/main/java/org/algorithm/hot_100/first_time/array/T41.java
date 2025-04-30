package org.xjtu_learner.hot_100.first_time.array;

public class T41 {

    //思路: 遍历nums中的数字n，将n与n-1下标上的元素互换位置（如元素1会与nums第一个元素交换位置，从而坐到它该坐的位置上）
    //      如果n>nums.length()，说明这个n不可能为答案（也就是nums中就没有它该坐的位置），经过交换后，所有出现在nums
    //      中且<=nums.length()的元素都坐到了自己该坐的位置上。现在遍历一遍nums，发现第一个坐着不是自己座位的人，说明
    //      这个座位的主人就没来，所以这个座位的主人就是缺失的第一个正数
    public int firstMissingPositive(int[] nums) {

        int cur = 0;
        while (cur < nums.length){
            // 为了处理重复元素的情况，判断是不是坐到自己位置上时不是通过nums[cur]-1 != cur，而是看自己座位上的人是不是自己（的分身）
            if (0 < nums[cur] && nums[cur] <= nums.length && nums[cur] != nums[nums[cur] - 1]) {
                swap(nums, cur, nums[cur] - 1);     // 让nums[i]坐到它该坐的位置上
            }else {
                cur++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i){
                return i + 1;
            }
        }
        return nums.length+1;
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        T41 t41 = new T41();
        int i = t41.firstMissingPositive(new int[]{1,1});
    }

}
