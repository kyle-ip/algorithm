package com.ywh.problem.leetcode.hard;

/**
 * 缺失的第一个正数
 * [数组] [哈希表]
 * 
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 * 示例 1：
 *      输入：nums = [1,2,0]
 *      输出：3
 * 示例 2：
 *      输入：nums = [3,4,-1,1]
 *      输出：2
 * 示例 3：
 *      输入：nums = [7,8,9,11,12]
 *      输出：1
 * 提示：
 *      1 <= nums.length <= 5 * 10^5
 *      -2^31 <= nums[i] <= 2^31 - 1
 *
 * @author ywh
 * @since 2020/9/15/015
 */
public class LeetCode41 {

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 遍历数组，对于每个元素 a 都归位到对应的下标 a-1。
     * 再次遍历数组时，元素的值不为下标 +1，即表示缺失该元素。
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int firstMissingPositiveSwap(int[] nums) {
        int n = nums.length;

        // 2, -1, 4, 1, 8

        // nums[2-1] != 2, swap
        //  +----+
        //  |    ↓
        // [2], -1, 4, 1, 8     =>      [-1], 2, 4, 1, 8

        // nums[-1-1] < 1, skip
        // [-1], 2, 4, 1, 8

        // nums[2-1] == 2, skip
        // -1, [2], 4, 1, 8

        // nums[4-1] != 4, swap
        //         +---+
        //         |   ↓
        // -1, 2, [4], 1, 8     =>      -1, 2, [1], 4, 8

        // nums[1-1] != 1, swap
        //  +------+
        //  ↓      |
        // -1, 2, [1], 4, 8     =>      1, 2, [-1], 4, 8

        // nums[4-1] == 4, skip
        // 1, 2, -1, [4], 8

        // 8 > n, skip
        // 1, 2, -1, 4, [8]
        for (int i = 0; i < n; ) {
            // 值转换成下标，比如当前位置的值为 5，则对应下标为 4。
            int j = nums[i] - 1;
            // 如果 j 在 [0, n) 范围内，且数组中对应的值不为 j+1，则交换 i、j 位置的值。
            // 比如下标 4 的值不为 5，则与值为 5 的下标（i）交换。
            if (j >= 0 && j < n && nums[j] != j + 1) {
                swap(nums, j, i);
            }
            // 否则表示值已经正确归位，如 [1, -1, 3, ...]，处理下一个值。
            else {
                ++i;
            }
        }

        // -1 != 3, return i + 1
        // 1, 2, [-1], 4, 8

        // 遍历交换好的数组，找到第一个未正确归位的值，返回该位置“原来应有的值”。
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 否则认为全部归位，返回最后一个值即可。
        return n + 1;
    }

    /**
     * FIXME bug
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int firstMissingPositiveFlipSign(int[] nums) {
        int n = nums.length;
        // 2, [-1], 4, 1, 8       =>      2, [6], 4, 1, 8
        for (int i = 0; i < n; ++i) {
            if (nums[i] < 1) {
                nums[i] = n + 1;
            }
        }
        // 2, 6, 4, 1, 8

        // abs(nums[0]) => 2
        // nums[2-1] > 0, set
        // [2], (6), 4, 1, 8      =>      [2], (-6), 4, 1, 8

        // abs(nums[1]) => 6
        // 6 > n, skip
        // 2, [-6], 4, 1, 8

        // abs(nums[2]) == 4
        // nums[4-1] > 0, set
        // 2, -6, [4], (1), 8     =>      2, -6, [4], -1, 8

        // abs(nums[3]) == 1
        // nums[1-1] > 0, set
        // (2), -6, 4, [-1], 8    =>      -2, -6, 4, [-1], 8

        // abs(nums[4]) == 8
        // 8 > n, skip
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n && nums[num - 1] > 0) {
                nums[num - 1] = -nums[num - 1];
            }
        }

        // -2, -6, 4, [-1], 8
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
