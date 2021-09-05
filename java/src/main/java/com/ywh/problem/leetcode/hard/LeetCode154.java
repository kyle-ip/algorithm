package com.ywh.problem.leetcode.hard;

import com.ywh.problem.leetcode.medium.LeetCode153;
import com.ywh.problem.leetcode.medium.LeetCode33;
import com.ywh.problem.leetcode.medium.LeetCode81;

/**
 * 寻找旋转排序数组中的最小值 II
 * [数组] [二分查找]
 * 
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 *      若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 *      若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 示例 1：
 *      输入：nums = [1,3,5]
 *      输出：1
 * 示例 2：
 *      输入：nums = [2,2,2,0,1]
 *      输出：0
 * 提示：
 *      n == nums.length
 *      1 <= n <= 5000
 *      -5000 <= nums[i] <= 5000
 *      nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * 进阶：
 *      这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 *      允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 * 
 * @author ywh
 * @since 4/13/2021
 */
public class LeetCode154 {

    /**
     * 参考 {@link LeetCode153}, {@link LeetCode33}, {@link LeetCode81}
     * 处理比较 nums[mid] 和 nums[high] 的三种情况，收缩区间时要注意不要排除可能的边界值。
     *
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;

            // nums[mid] 比 nums[high] 小，即此时 [mid, high] 仍递增，因此可以向左收缩区间（不确定是否包含 mid，因此不能略过）。
            // [4, 5, (1), 2, 3]
            //         ↑      h
            //         |      |
            //         +------+
            if (nums[mid] < nums[high]) {
                high = mid;
            }
            // nums[mid] 比 nums[high] 大，即此时 mid 落在翻转后的左边部分、不可能有最小值，因此向右收缩区间。
            // [3, 4, (5), 1, 2]
            //  l          ↑
            //  |          |
            //  +----------+
            else if (nums[mid] > nums[high]) {
                low = mid + 1;
            }
            // 由于重复元素的存在，不能确定 nums[mid] 在最小值的左侧还是右侧。
            // 只知道由于它们的值相同，无论 nums[high] 是不是最小值，都有重复值 nums[mid] 在它的右侧，因此可以向右收缩区间。
            // [2, 3, (1), 1, 1]
            //              ← h
            else {
                high--;
            }
        }
        return nums[low];
    }
}
