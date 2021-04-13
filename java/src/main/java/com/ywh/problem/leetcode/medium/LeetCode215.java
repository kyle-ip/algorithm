package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode703;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 数组中的第 K 个最大元素
 * [分治] [堆]
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1：
 *      输入: [3,2,1,5,6,4] 和 k = 2
 *      输出: 5
 * 示例 2：
 *      输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 *      输出: 4
 * 说明：
 *      你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author ywh
 * @since 2019/10/30
 */
public class LeetCode215 {

    /**
     * 排序后返回对应下标
     * Time: O(n*log(n)), Space: O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestQuickSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 最小堆法，参考 {@link LeetCode703}
     * Time: O(n*log(k)), Space: O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestMinHeap(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int num: nums) {
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(num);
            }
        }
        return minHeap.peek();
    }

    /**
     *
     * @param nums
     * @param i
     * @param j
     */
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 快速选择法：
     * 类似二分查找和快速排序，每次获取分区点的下标；
     * 如果下标为 k - 1（比如 2），表示前面有 k - 1 个元素比它大（nums[0]、nums[1]）；
     * 所以下标为 pivot 的元素即为数组第 k（3）大元素
     *
     * Time(avg): O(n), Time(worst): O(n^2), Space: O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestQuickSelect(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {

            // 反向的分区方法，对于返回值 l：左边的元素都大于等于 l，右边的元素都小于 l。
            int pivot = nums[low], l = low, r = high;
            while (l < r) {
                for (; l < r && nums[r] < pivot; r--);
                if (l < r) {
                    swap(nums, l, r);
                }
                for (; l < r && nums[l] >= pivot; l++);
                if (l < r) {
                    swap(nums, l, r);
                }
            }
            // 至此，l 左边的元素都大于等于 pivot，l 右边的元素都小于 pivot。
            // 如果 l 正处在 k-1 的位置（从 0 开始），表示该位置正是数组中第 k 大的元素，直接返回。
            // 否则，如果 l 左边的元素大于 k 个，处理左半边；如果 l 左边的元素小于 k 个，处理右半边。
            if (l == k - 1) {
                return nums[l];
            } else if (l > k - 1) {
                high = l - 1;
            } else {
                low = l + 1;
            }
        }
        return -1;
    }

}
