package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 前 K 个高频数字
 * [哈希表] [堆] [排序] [快速选择]
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 *      输入: nums = [1,1,1,2,2,3], k = 2
 *      输出: [1,2]
 * 示例 2:
 *      输入: nums = [1], k = 1
 *      输出: [1]
 * 提示：
 *      你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 *      你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *      题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 *      你可以按任意顺序返回答案。
 *
 * @author ywh
 * @since 12/01/2020
 */

public class LeetCode347 {

    /**
     * Time: O(n*log(k)), Space: O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequentMinHeap(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        for (Map.Entry<Integer, Integer> e : counter.entrySet()) {
            pq.add(e);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<Integer> ret = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : pq) {
            ret.add(e.getKey());
        }
        return ret;
    }

    /**
     * @param entries
     * @param low
     * @param high
     * @return
     */
    private int partition(List<Map.Entry<Integer, Integer>> entries, int low, int high) {
        int pivot = entries.get(low).getValue();
        int i = low, j = high;
        while (i < j) {
            while (i < j && entries.get(j).getValue() < pivot) {
                --j;
            }
            if (i < j) {
                Collections.swap(entries, i, j);
            }
            while (i < j && entries.get(i).getValue() >= pivot) {
                ++i;
            }
            if (i < j) {
                Collections.swap(entries, i, j);
            }
        }
        return i;
    }

    /**
     * Time(avg): O(n), Time(worst): O(n^2), Space: O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequentQuickSelect(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(counter.entrySet());
        int low = 0, high = entries.size() - 1;
        while (low <= high) {
            int p = partition(entries, low, high);
            if (p == k - 1) {
                break;
            } else if (p > k - 1) {
                high = p - 1;
            } else {
                low = p + 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            result.add(entries.get(i).getKey());
        }
        return result;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequentBucketSort(int[] nums, int k) {

        // 统计每个元素出现频率。
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        // 创建一些桶，把数组的元素按出现的频率分配到对应下标的桶中：
        // 比如 [1, 1, 1, 2, 2, 2, 3]，1 出现 3 次放在 3 桶、2 出现 3 次放在 3 桶，3 出现 1 次放在 1 桶。
        // [
        //     [],
        //     [3],
        //     [],
        //     [1, 2]
        // ]
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }
        for (Map.Entry<Integer, Integer> e : counter.entrySet()) {
            buckets.get(e.getValue()).add(e.getKey());
        }

        // 从桶中取出元素，返回。
        int[] ret = new int[k];
        for (int i = nums.length; i >= 0 && k > 0; i--) {
            List<Integer> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size() && k > 0; j++, k--) {
                ret[k - 1] = bucket.get(j);
            }
        }
        return ret;
    }

}

