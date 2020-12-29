package com.ywh.problem.leetcode.medium;

/**
 * 除了自身元素的数组乘积
 * [数组]
 *
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 示例:
 *      输入: [1,2,3,4]
 *      输出: [24,12,8,6]
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 进阶：
 *      你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * @author ywh
 * @since 28/11/2019
 */
public class LeetCode238 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        // 保存数组从左到右、从右到左的累乘结果
        // 则 output[i] == left[i - 1] * right[i + 1]
        int n = nums.length;
        int[] leftProduct = new int[n];
        int[] rightProduct = new int[n];
        int[] output = new int[n];
        leftProduct[0] = nums[0];
        rightProduct[n - 1] = nums[n - 1];
        for (int i = 1; i < n; ++i) {
            leftProduct[i] = leftProduct[i - 1] * nums[i];
        }
        for (int j = n - 2; j >= 0; --j) {
            rightProduct[j] = rightProduct[j + 1] * nums[j];
        }
        output[0] = rightProduct[1];
        output[n - 1] = leftProduct[n - 2];
        for (int i = 1; i < n - 1; ++i) {
            output[i] = leftProduct[i - 1] * rightProduct[i + 1];
        }
        return output;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelfO1(int[] nums) {
        // 在解法 1 基础上，left 和 right 也可以存储在输入 nums 和输出 output 中，不需要辅助空间
        int n = nums.length;
        int[] output = new int[n];
        output[n - 1] = nums[n - 1];

        for (int j = n-2; j >= 0; --j) {
            output[j] = output[j + 1] * nums[j];
        }
        for (int i = 1; i < n; ++i) {
            nums[i] = nums[i - 1] * nums[i];
        }

        int first = output[1], last = nums[n-2];
        for (int i = 1; i < n - 1; ++i) {
            output[i] = nums[i - 1] * output[i + 1];
        }
        output[0] = first;
        output[n - 1] = last;
        return output;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelfNotChangeInputO1(int[] nums) {
        // 在解法 2 的基础上，如果还不允许修改原数组，还可以只把数据存储在 output 中，不需要辅助空间
        // 第一次遍历，利用 output 记录从左到右的左累计乘积
        int[] output = new int[nums.length];

        // 第一个元素左边没有，因此累计为 1
        // 比如 1, 4, 2, 8，左累计乘积为 1, 1, 4, 8
        output[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        // 从最右边开始乘回去（右边累计乘积只需要一个变量即可），最后一个元素右边没有，因此累计为 1。
        for (int i = nums.length - 1, right = 1; i >= 0; i--) {
            output[i] *= right;
            right *= nums[i];
        }
        return output;
    }
}
