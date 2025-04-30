package org.algorithm.hot_100.first_time.backtracking;

import java.util.ArrayList;
import java.util.List;

//题目: 78.子集

public class T78 {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        int[] attend = new int[nums.length];
        dfs(nums, attend, 0, result);

        return result;
    }

    void dfs(int[] nums, int[] attend, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < attend.length; i++) {
                if(attend[i] == 1){
                    list.add(nums[i]);
                }
            }
            result.add(list);
            return;
        }

        for (int i = 0; i <= 1; i++) {
            attend[index] = i;
            dfs(nums, attend, index + 1, result);
        }
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3};
        T78 t78 = new T78();
        List<List<Integer>> subsets = t78.subsets(nums);
    }
}
