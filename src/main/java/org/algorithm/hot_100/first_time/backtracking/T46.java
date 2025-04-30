package org.xjtu_learner.hot_100.first_time.backtracking;

import java.util.ArrayList;
import java.util.List;

//题目: 46.全排列

public class T46 {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        dfs(nums, 0, result);

        return result;
    }

    void dfs(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> path = new ArrayList<>(nums.length);
            for (int num : nums) path.add(num);
            result.add(path);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            dfs(nums, index+1, result);
            swap(nums, i, index);
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};

        T46 t46 = new T46();
        List<List<Integer>> permute = t46.permute(nums);
    }
}
