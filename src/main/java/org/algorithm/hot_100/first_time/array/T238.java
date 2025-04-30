package org.xjtu_learner.hot_100.first_time.array;

//题目: 238. 除自身以外数组的乘积
//思路: 类似前缀和的方法，leftProduct[i]表示nums中前i个元素的乘积，rightProduct[i]同理，第i个元素的除自身以外数组的乘积
//      就是leftProduct[i]与rightProduct[i]对于位置的乘积

public class T238 {

    public int[] productExceptSelf(int[] nums) {

        int length = nums.length;

        int[] leftProduct = new int[length + 1];
        leftProduct[0] = 1;
        for (int i = 0; i < length; i++) {
            leftProduct[i + 1] = leftProduct[i] * nums[i];
        }

        int[] rightProduct = new int[length + 1];
        rightProduct[nums.length] = 1;
        for (int i = length - 1; i >= 0; i--) {
            rightProduct[i] = rightProduct[i + 1] * nums[i];
        }

        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = leftProduct[i] * rightProduct[i + 1];
        }

        return result;
    }

    public static void main(String[] args) {
        T238 t238 = new T238();
        int[] ints = t238.productExceptSelf(new int[]{-1,1,0,-3,3});
    }
}
