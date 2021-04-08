package com.ywh.problem.leetcode.medium;

/**
 * 寻找重复数
 * [数组] [双指针] [二分搜索]
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 * 示例 1:
 *      输入: [1,3,4,2,2]
 *      输出: 2
 * 示例 2:
 *      输入: [3,1,3,4,2]
 *      输出: 3
 * 提示：
 *      2 <= n <= 3 * 10^4
 *      nums.length == n + 1
 *      1 <= nums[i] <= n
 *      nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * 进阶：
 *      如何证明 nums 中至少存在一个重复的数字?
 *      你可以在不修改数组 nums 的情况下解决这个问题吗？
 *      你可以只用常量级 O(1) 的额外空间解决这个问题吗？
 *      你可以设计一个时间复杂度小于 O(n^2) 的解决方案吗？
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
        int slow = nums[0], fast = nums[nums[0]];

        // 这会不断循环，如对于 4, 3, 3, 1, 2, 5，序列为：2 -> 3 -> 1 -> 3 -> 1 -> ...
        // 这是因为数组长度为 n + 1，值域为 1 ~ n，不断循环都是合法的下标（成环）
        // 使用快慢两个指针：
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // 当快慢指针相遇，新建一个指针从头开始，与慢指针同步移动，直到两者相遇，即为环的入口，即重复元素。
        for (int p = 0; p != slow; ) {
            slow = nums[slow];
            p = nums[p];
        }
        return slow;
    }
}
