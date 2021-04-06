package com.ywh.problem.leetcode.medium;

/**
 * 颜色分类
 * [数组] [双指针] [排序]
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 示例 1：
 *      输入：nums = [2,0,2,1,1,0]
 *      输出：[0,0,1,1,2,2]
 * 示例 2：
 *      输入：nums = [2,0,1]
 *      输出：[0,1,2]
 * 示例 3：
 *      输入：nums = [0]
 *      输出：[0]
 * 示例 4：
 *      输入：nums = [1]
 *      输出：[1]
 * 提示：
 *      n == nums.length
 *      1 <= n <= 300
 *      nums[i] 为 0、1 或 2
 * 进阶：
 *      你可以不使用代码库中的排序函数来解决这道题吗？
 *      你能想出一个仅使用常数空间的一趟扫描算法吗？
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
        for (int l = 0, m = 0, r = nums.length - 1; m <= r; ) {
            // m 指向 0，把 0 换到 l 上，移动两指针。
            if (nums[m] == 0) {
                swap(nums, l++, m++);
            }
            // m 指向 1，不动。
            else if (nums[m] == 1) {
                m++;
            }
            // m 指向 2，把 2 换到 r 上，移动 r。
            else {
                swap(nums, r--, m);
            }
        }
    }
}
