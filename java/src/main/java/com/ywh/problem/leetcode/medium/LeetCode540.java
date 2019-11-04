package com.ywh.problem.leetcode.medium;

/**
 * 有序数组中的单身数字
 * [位操作] [二分搜索]
 *
 * @author ywh
 * @since 2019/10/30
 */
public class LeetCode540 {

    /**
     * 利用相同数字异或为 0 的特性
     * <p>
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumInSortedArrayWithXOR(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    /**
     * 利用数组有序的特性使用二分搜索
     *
     * Time: O(log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumInSortedArrayBinarySearch(int[] nums) {
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;

            // mid 落点的三种情况
            // 2 (2) 3 => (2) 2 3
            if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                mid--;
            }
            // 2 (3) 3 => 2 (3) 3
            else if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
                ;
            }
            // 1 (2) 3 => return
            else {
                return nums[mid];
            }

            // 缩小范围的两种处理
            // 1 1 2 2 (4) 4 6 8 8 => 6 8 8
            if ((mid - low) % 2 == 0) {
                low = mid + 2;
            }
            // 1 1 2 2 3 (4) 4 8 8 9 9 => 1 1 2 2 3
            else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
