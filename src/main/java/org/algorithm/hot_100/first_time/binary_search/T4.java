package org.algorithm.hot_100.first_time.binary_search;

//题目: 4.寻找两个正序数组的中位数
//思路: 这道题的本质就是给两个有序数组，找到全局的第k小的数字的下标，这个问题通过归并排序在O(m+n)时间复杂度下可以解决，
//      使用二分查找在O(log(m+n))的时间复杂度下可以解决，这个二分查找的边界条件可以说是非常复杂了
//题解:https://leetcode.cn/problems/median-of-two-sorted-arrays/solutions/258842/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/25 二分查找的方法思路想不出来，而且即使知道了思路，也写不出来，边界太复杂了，这类二分查找的边界条件编码能力
//               需要深度训练，本题及其值得重刷，本刷并未深入理解

public class T4 {

    //思路: 先通过归并排序将两个基本有序的数组合并成一个大数组，再找第k小的位置，时间复杂度O(m+n)，空间复杂度O(m+n)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] sorted = merge(nums1, nums2);
        int k = (nums1.length + nums2.length) / 2;

        if ((nums1.length + nums2.length) % 2 != 0) {
            return (double) sorted[k];
        } else {
            return (double) (sorted[k - 1] + sorted[k]) / 2;
        }
    }

    int[] merge(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] merged = new int[len1 + len2];

        int cur1 = 0;
        int cur2 = 0;

        int cur = 0;

        while (cur1 < len1 && cur2 < len2) {
            if (nums1[cur1] <= nums2[cur2]) {
                merged[cur++] = nums1[cur1++];
            } else {
                merged[cur++] = nums2[cur2++];
            }
        }   // 从这里出来一定是cur1与cur2其中之一达到了边界

        // 拷贝剩余元素
        while (cur1 < len1) {
            merged[cur++] = nums1[cur1++];
        }
        while (cur2 < len2) {
            merged[cur++] = nums2[cur2++];
        }

        return merged;
    }

    //思路: 上面的方法相当于前k个数是一个一个找的，这里使用二分查找法，每次找nums1和nums2中第k/2的元素，这两者
    // 中较小的及其左侧元素一定不是第k小，排除掉并重复这样的查找。为了同一奇数和偶数的情况，提取出统一的getKth方
    // 法，即从两个基本有序的数组nums1和nums2中找到全局第k小的下标
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int k = (len1 + len2) / 2;  //代表着当前要找的元素，即第k小的元素
        if ((len1 + len2) % 2 != 0) {
            return getKth(nums1, nums2, k + 1);
        } else {
            return (double) (getKth(nums1, nums2, k) + getKth(nums1, nums2, k + 1)) / 2;
        }
    }

    // 这个方法是本题的重点
    /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
     * 这里的 "/" 表示整除
     * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
     * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
     * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
     * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
     * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
     */
    int getKth(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int index1 = 0;
        int index2 = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }


    public static void main(String[] args) {

        int[] nums1 = new int[]{0, 0, 0, 0, 0};
        int[] nums2 = new int[]{-1, 0, 0, 0, 0, 0, 1};

        T4 t4 = new T4();
        double medianSortedArrays1 = t4.findMedianSortedArrays2(nums1, nums2);
    }
}
