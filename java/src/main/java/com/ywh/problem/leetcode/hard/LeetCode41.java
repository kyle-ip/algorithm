package com.ywh.problem.leetcode.hard;

/**
 * 第一个缺失的正整数
 * [数组]
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
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int firstMissingPositiveSwap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length, p = 0;

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
        while (p < n) {
            int num = nums[p];
            if (num >= 1 && num <= n && nums[num - 1] != num) {
                int tmp = nums[p];
                nums[p] = nums[num - 1];
                nums[num - 1] = tmp;
            } else {
                ++p;
            }
        }

        // -1 != 3, return i + 1
        // 1, 2, [-1], 4, 8
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // return n + 1
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
        if (nums == null || nums.length == 0) {
            return 1;
        }
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
