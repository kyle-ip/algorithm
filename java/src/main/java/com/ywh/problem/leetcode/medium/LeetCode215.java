package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode703;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 数组中第 K 大的元素
 * [分治] [堆]
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
    public int findKthLargestQuickSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[k - 1];
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

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 反向的分区方法，对于返回值 i：左边的元素都大于等于 i，右边的元素都小于 i
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    int partition(int[] nums, int low, int high) {
        int pivot = nums[low], i = low, j = high;
        while (i < j) {
            while (i < j && nums[j] < pivot) {
                j--;
            }
            while (i < j && nums[i] >= pivot) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        return i;
    }

    /**
     * 快速选择法：
     * 类似二分搜索，每次使用 {@link #partition} 获取分区点的下标；
     * 如果下标为 k - 1（比如 2），表示前面有 k - 1 个元素比它大（nums[0]、nums[1]）；
     * 所以下标为 pivot 的元素即为数组第 k（3）大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestQuickSelect(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int pivot = partition(nums, low, high);
            if (pivot == k - 1) {
                return nums[pivot];
            }
            if (pivot > k - 1) {
                high = pivot - 1;
            } else {
                low = pivot + 1;
            }
        }


        return -1;
    }

}
