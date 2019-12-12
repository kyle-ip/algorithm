package com.ywh.problem.leetcode.medium;

/**
 * 查找重复数字
 * [数组] [双指针] [二分搜索]
 *
 * @author ywh
 * @since 12/12/2019
 */
public class LeetCode287 {


    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findDuplicateBruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    /**
     * Time: O(n*log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findDuplicateBinarySearch(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low < high) {
            // 每次取子数组值域的中间值（如第一次为 nums.length / 2），统计数组中小于等于中间值的元素个数
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            // 如果 count > mid，表示左边 mid 个位置不足以放完小于等于它自身的元素（2 个位放 3 个元素，必定有重复）
            // 因此左边的 mid 个元素必定有重复，所以迭代处理左边，否则迭代处理右边
            if (count > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * 把数组视为带环的链表，求入口节点，见 {@link LeetCode141}
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int findDuplicateTwoPointer(int[] nums) {
        // 快慢两个指针，慢指针指向第一个元素，快指针指向以慢指针元素为下标的元素
        // 不断以值为下标、访问下一个元素（快指针转换两次、慢指针转换一次）
        int slow = nums[0], fast = nums[nums[0]], p = 0;

        // 这会不断循环，如对于 4, 3, 3, 1, 2, 5，序列为：2 -> 3 -> 1 -> 3 -> 1 -> ...
        // 这是因为数组长度为 n + 1，值域为 1 ~ n，不断循环都是合法的下标（成环）
        // 使用快慢两个指针，
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // 当快慢指针相遇，新建一个指针从头开始，与慢指针同步移动，直到两者相遇，即为环的入口，即重复元素
        while (slow != p) {
            slow = nums[slow];
            p = nums[p];
        }
        return slow;
    }
}
