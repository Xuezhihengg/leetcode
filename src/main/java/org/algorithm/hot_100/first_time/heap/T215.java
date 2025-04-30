package org.algorithm.hot_100.first_time.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//题目: 215.数组中的第K个最大元素
//思路: 自己实现一个大顶堆，将nums中所有元素都插入（建堆的时间复杂度是O(n)，而不是直觉上的O(nlogn)），然后再删除k-1
//      个堆顶元素（O(klogn)），总的时间复杂度为O(n + klogn) = O(nlogn)

// !*! 2025/4/26 这里自己实现了一个堆，堆的实现需要熟练掌握

public class T215 {

    public int findKthLargest(int[] nums, int k) {

        // 初始化一个最大堆，并将所有元素都插入其中
        MaxHeap maxHeap = new MaxHeap();

        for (int n: nums){
            maxHeap.offer(n);
        }

        // 堆最大堆执行k-1次删除操作
        for (int i = 0; i < k - 1; i++) {
            int poll = maxHeap.poll();
        }

        return maxHeap.poll();
    }

    static class MaxHeap {
        private final List<Integer> heap;

        MaxHeap() {
            heap = new ArrayList<>();
        }

        // 向小顶堆中插入一个元素
        void offer(int e) {

            // 先直接将该元素添加到数组末尾
            heap.add(e);

            // 执行自底向上堆化
            sifUp(heap.size() - 1);
        }

        // 弹出最小的元素（根节点元素）
        int poll() {
            if (heap.isEmpty()) {
                throw new RuntimeException("堆为空");
            }

            // 根节点即为堆中最小元素
            int min = heap.get(0);
            // 将根节点与数组最后一个元素交换
            int last = heap.remove(heap.size() - 1);
            // 如果堆中只有一个元素，也就没必要sifDown了
            if (!heap.isEmpty()) {
                heap.set(0, last);
                // 执行自顶向下堆化
                sifDown(0);
            }

            return min;
        }

        // 自底向上堆化
        private void sifUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;
                // 如果当前元素比父节点小，则交换
                if (heap.get(index) > heap.get(parent)) {
                    Collections.swap(heap, index, parent);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void sifDown(int index) {
            int size = heap.size();
            while (true) {
                int biggest = index;
                int leftChild = 2 * index + 1;
                int rightChild = 2 * index + 2;

                // 找到leftChild与rightChild中较小的
                if (leftChild < size && heap.get(leftChild) > heap.get(biggest)) {
                    biggest = leftChild;
                }

                if (rightChild < size && heap.get(rightChild) > heap.get(biggest)) {
                    biggest = rightChild;
                }

                if (biggest != index) {
                    // 交换index和smallest
                    Collections.swap(heap, index, biggest);
                    // 更新index
                    index = biggest;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        T215 t215 = new T215();
        int kthLargest = t215.findKthLargest(nums, 2);
    }


}
