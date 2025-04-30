package org.algorithm.hot_100.first_time.backtracking;

import java.util.ArrayList;
import java.util.List;

//题目: 39.组合总和
//思路: 子集树，每个元素的状态即其出现的次数，这里需要注意剪枝，不然会超时间限制

public class T39 {

    int sum;

    //思路: 子集树，每个元素的状态即其出现的次数
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        sum = 0;
        List<List<Integer>> result = new ArrayList<>();
        int[] state = new int[candidates.length];

        dfs(candidates, state, target, 0, result);

        return result;
    }

    void dfs(int[] candidates, int[] state, int target, int index, List<List<Integer>> result) {
        if (index == candidates.length) {

            if (sum == target) {
                List<Integer> temp = new ArrayList<>();
                for (int i = 0; i < candidates.length; i++) {
                    for (int j = 0; j < state[i]; j++) {
                        temp.add(candidates[i]);
                    }
                }
                result.add(temp);
            }
            return;
        }

        int max = target / candidates[index];
        for (int i = 0; i <= max; i++) {
            state[index] = i;
            sum += candidates[index] * i;
            // 剪枝
            if (sum <= target) {
                dfs(candidates, state, target, index + 1, result);
            }
            sum -= candidates[index] * i;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 6, 7};


        T39 t39 = new T39();
        List<List<Integer>> lists = t39.combinationSum(nums, 7);
    }
}
