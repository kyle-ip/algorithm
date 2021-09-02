package com.ywh.problem.offer;

/**
 * 数组中的逆序对
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 *      输入: [7,5,6,4]
 *      输出: 5
 * 限制：
 *      0 <= 数组长度 <= 50000
 * 
 * @author ywh
 * @since 30/03/2021
 */
public class Offer51 {

    /**
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        return reversePairs(nums, 0, n - 1, new int[n]);
    }

    /**
     *
     * @param nums
     * @param low
     * @param high
     * @param temp
     * @return
     */
    private int reversePairs(int[] nums, int low, int high, int[] temp) {
        if (low == high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        int leftPairs = reversePairs(nums, low, mid, temp), rightPairs = reversePairs(nums, mid + 1, high, temp);
        return leftPairs + rightPairs + mergeAndCount(nums, low, mid, high, temp);
    }

    /**
     *
     * @param nums
     * @param low
     * @param mid
     * @param high
     * @param temp
     * @return
     */
    private int mergeAndCount(int[] nums, int low, int mid, int high, int[] temp) {
        if (high - low + 1 >= 0) {
            System.arraycopy(nums, low, temp, low, high + 1 - low);
        }
        int count = 0;
        for (int i = low, j = mid + 1, k = low; k <= high; k++) {
            // 左半已经遍历到尽头。
            if (i == mid + 1) {
                nums[k] = temp[j++];
            }
            // 右半已经遍历到尽头。
            else if (j == high + 1) {
                nums[k] = temp[i++];
            }
            // 左边的小于等于右边。
            else if (temp[i] <= temp[j]) {
                nums[k] = temp[i++];
            }
            // 左边大于右边，[i, mid] 的元素与 j 构成逆序对。
            else {
                nums[k] = temp[j++];
                count += mid - i + 1;
            }
        }
        return count;
    }
    
}
