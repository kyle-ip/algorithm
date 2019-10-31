package com.ywh.algorithm.leetcode.medium;

/**
 * 荷兰国旗问题
 * [数组] [双指针] [排序]
 *
 * @author ywh
 * @since 2019/2/21
 */
public class LeetCode75 {

    /**
     * 两次循环：
     * 第一次循环统计出 0、1、2 分别的个数，
     * 第二次循环根据其个数重新填充数组即可
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     */
    public void sortThreeColorsCount(int[] nums) {
        if (nums == null || nums.length == 3) {
            return;
        }
        int cnt0 = 0, cnt1 = 0, cnt2 = 0, k = 0;
        for(int num: nums) {
            if (num == 0) {
                cnt0++;
            } else if (num == 1) {
                cnt1++;
            } else {
                cnt2++;
            }
        }
        for (int i = 0; i < cnt0; i++) {
            nums[k++] = 0;
        }
        for (int i = 0; i < cnt1; i++) {
            nums[k++] = 1;
        }
        for (int i = 0; i < cnt2; i++) {
            nums[k++] = 2;
        }
    }

    /**
     * 交换两数位置
     *
     * @param nums
     * @param i
     * @param j
     */
    private void swap(int[] nums, int i , int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 三指针法：左指针指向“0 区域”的右边界、中指针指向“1 区域”的右边界，右指针指向“2 区域”的左边界；
     * 左、中指针从头出发，右指针从尾出发，每次判断中指针的值：
     * 如中指针的值为 0，表示该值应该被放在左边，因此与左指针交换；
     * 如中指针的值为 1，表示1已被放在正确的位置，因此判断下一位；
     * 如中指针的值为 2，表示该值应该被放在右边，因此与右指针交换；
     * 每发生交换，都要在交换后把指针向中间移动
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     */
    public void sortThreeColorsSwap(int[] nums) {
        if (nums == null || nums.length < 3) {
            return;
        }
        int left = 0, mid = 0, right = nums.length - 1;

        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(nums, left++, mid++);
            }
            else if (nums[mid] == 1) {
                mid++;
            }
            else {
                swap(nums, right--, mid);
            }
        }
    }
}
