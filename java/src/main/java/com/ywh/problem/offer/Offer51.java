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
//        int[] copy = new int[len];
//        System.arraycopy(nums, 0, copy, 0, len);
        return reversePairs(nums, 0, n - 1, new int[n]);
    }

    /**
     *
     * @param nums
     * @param left
     * @param right
     * @param temp
     * @return
     */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }
        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    /**
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param temp
     * @return
     */
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        if (right + 1 - left >= 0) {
            System.arraycopy(nums, left, temp, left, right + 1 - left);
        }
        int count = 0;
        for (int i = left, j = mid + 1, k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j++];
            } else if (j == right + 1) {
                nums[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i++];
            } else {
                nums[k] = temp[j++];
                count += mid - i + 1;
            }
        }
        return count;
    }
    
}
