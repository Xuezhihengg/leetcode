package org.xjtu_learner.hot_100.first_time.hash;

import java.util.*;

//题目: 49.字母异位词分组
//思路: 字母异位词字典排序后一定相同，就以它们字典排序后的字符串为键

public class T49 {

    public List<List<String>> groupAnagrams(String[] strs) {

        // 升序排列后的s -> s的字母异位词列表
        Map<String, List<String>> map = new HashMap<>();

        // 遍历strs，把s变为字典升序然后往map中存
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(s);
        }

        return map.values().stream().toList();
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        T49 t49 = new T49();
        List<List<String>> lists = t49.groupAnagrams(strs);
    }
}
