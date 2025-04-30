package org.xjtu_learner.hot_100.first_time.backtracking;

import java.util.ArrayList;
import java.util.List;

//题目: 17.电话号码的字母组合
//思路: 子集树，使用递归解决，但是这个子集树不仅仅是不存在（0）、存在（1）的状态，而是根据按键的不同有不同的状态

public class T17 {

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();
        if(digits == null || digits.isEmpty()){
            return result;
        }

        // 初始化映射关系
        List<char[]> map = new ArrayList<>();
        map.add(new char[]{'a', 'b', 'c'});
        map.add(new char[]{'d', 'e', 'f'});
        map.add(new char[]{'g', 'h', 'i'});
        map.add(new char[]{'j', 'k', 'l'});
        map.add(new char[]{'m', 'n', 'o'});
        map.add(new char[]{'p', 'q', 'r', 's'});
        map.add(new char[]{'t', 'u', 'v'});
        map.add(new char[]{'w', 'x', 'y', 'z'});

        int[] nums = new int[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            nums[i] = Character.getNumericValue(digits.charAt(i)) - 2;
        }

        int[] attend = new int[nums.length];

        dfs(map, nums, attend, 0, result);

        return result;
    }

    void dfs(List<char[]> map, int[] nums, int[] attend, int index, List<String> result) {
        if (index == nums.length) {
            // output
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                builder.append(map.get(nums[i])[attend[i]]);
            }
            result.add(builder.toString());
            return;
        }

        int len = map.get(nums[index]).length;
        for (int i = 0; i < len; i++) {
            attend[index] = i;
            dfs(map, nums, attend, index + 1, result);
        }
    }

    public static void main(String[] args) {

        T17 t17 = new T17();
        List<String> strings = t17.letterCombinations("3");
    }
}
