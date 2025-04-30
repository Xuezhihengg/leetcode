package org.xjtu_learner.hot_100.first_time.double_pointer;

//题目: 283.移动零

// !*! 2025/4/29 自己倒是写出来了，但是比较丑陋而且需要比较久的debug。

/*
* ！！！双指针问题大总结！！！：
* 双指针问题有两种代码框架：
*   1）两个指针从同一边出发，如T283、T42，这时对走在前面的指针（如从左向右遍历时为right）使用fori循环进行更新，然后
*      在必要的时候更新走得慢的指针（如left），这样就天然保证了left<=right，还有一系列别的好处，总之比while循环好写很多
*   2）两个指针从两个边界向中间靠拢，如T11、T15，则使用while(left<right)框架，会比较清晰
* */


public class T283 {

    //思路: 双指针法，左边的指针指0，右边的指针指向非0元素，然后交换
    public void moveZeroes1(int[] nums) {

        int left = 0;
        int right = 0;

        while (right < nums.length - 1) {
            // 左指针找0
            while (left < nums.length - 1 && nums[left] != 0) {
                left++;
            }

            // 右指针从左边开始找第一个非0元素
            while (right < nums.length - 1 && nums[right] == 0) {
                right++;
                // 当右指针找到数组尾部则退出
            }

            if (left > right) {
                right++;
                continue;
            }

            // 交换
            swap(nums, left, right);
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    // 更简洁的写法，但是不知道如何想出这样的写法，没有框架性
    public void moveZeroes2(int[] nums) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {   // right找到非0元素
                // 交换左右指针元素，这时left所指元素只有两种可能：1）为0，2）left与right重合
                swap(nums, left, right);
                // 交换后认为该元素已经确定好了
                left++;
            }
        }
    }

    public static void main(String[] args) {
        T283 t283 = new T283();

        t283.moveZeroes2(new int[]{0, 1, 0, 3, 12});
    }
}
