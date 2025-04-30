package org.algorithm.hot_100.first_time.heap;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

//题目: 295.数据流的中位数
//题解: https://leetcode.cn/problems/find-median-from-data-stream/solutions/2361972/295-shu-ju-liu-de-zhong-wei-shu-dui-qing-gmdo/?envType=study-plan-v2&envId=top-100-liked

// 2025/4/27 好题，思想很巧妙，很值得重刷

public class T295 {

    //思路: 通过二分查找插入这个数据，二分查找的时间复杂度是O(logn)，把数据插入list中的时间复杂度是O(n)，所以
    //      总的插入一条数据的时间复杂度是O(n)
    static class MedianFinder1 {
        private final List<Integer> sorted;

        public MedianFinder1() {
            this.sorted = new LinkedList<>();
        }

        public void addNum(int num) {
            // 通过二分查找插入这个元素
            int i = find(num);
            sorted.add(i, num);
        }

        public double findMedian() {
            int size = sorted.size();
            if (size % 2 != 0) {
                return sorted.get(size / 2);
            } else {
                return (double) (sorted.get(size / 2 - 1) + sorted.get(size / 2)) / 2;
            }
        }

        private int find(int num) {
            int left = 0;
            int right = sorted.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (sorted.get(mid) <= num) { // 向右走
                    left = mid + 1;
                } else { // 向左走
                    right = mid - 1;
                }
            }

            return left;
        }
    }

    //思路: 维护一个大顶堆，一个小顶堆，分别保存一半的元素，中位数即二堆堆顶元素的平均，插入一个元素时就是向一个堆中插入
    //      时间复杂度是O(logn)
    static class MedianFinder2 {
        private final PriorityQueue<Integer> smaller;   // 大顶堆
        private final PriorityQueue<Integer> bigger;    // 小顶堆

        public MedianFinder2() {
            this.smaller = new PriorityQueue<>((a, b) -> (b - a));
            this.bigger = new PriorityQueue<>();
        }

        // 需要保证smaller和bigger元素个数相同，可能存在当前元素要添加到smaller（size为奇数），
        // 但实际它属于bigger（num>smaller堆顶元素），这需要将num加入bigger并将bigger的堆顶元素下移
        // 到smaller中，所以同一的方法是：
        //      - 向smaller添加一个元素。实现方法：将新元素num插入至bigger，再将bigger堆顶元素插入至smaller
        //      - 向bigger添加一个元素。实现方法：将新元素num插入至smaller，再将smaller堆顶元素插入至bigger
        public void addNum(int num) {
            // 添加到smaller
            if (smaller.size() == bigger.size()) {
                bigger.offer(num);
                smaller.offer(bigger.poll());
            }else {     // 添加到bigger
                smaller.offer(num);
                bigger.offer(smaller.poll());
            }
        }

        public double findMedian() {
            return smaller.size() != bigger.size()? smaller.peek() : (double) (smaller.peek() + bigger.peek()) /2;
        }
    }

    public static void main(String[] args) {
        MedianFinder2 medianFinder2 = new MedianFinder2();
        medianFinder2.addNum(3);
        medianFinder2.addNum(1);
        System.out.println(medianFinder2.findMedian());
        medianFinder2.addNum(2);
        System.out.println(medianFinder2.findMedian());
    }
}
