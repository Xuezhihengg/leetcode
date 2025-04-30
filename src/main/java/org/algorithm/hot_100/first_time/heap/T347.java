package org.xjtu_learner.hot_100.first_time.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//题目: 347.前K个高频元素

public class T347 {

    /**
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {

        // n -> n出现的次数
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pq.offer(e);
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        T347 t347 = new T347();

        int[] ints = t347.topKFrequent(nums, 2);
    }
}
