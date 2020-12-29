package com.ywh.problem.leetcode.easy;

import java.util.*;

/**
 * 两个数组的交集 II
 * [哈希表] [双指针] [排序]
 * 
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 *      输入：nums1 = [1,2,2,1], nums2 = [2,2]
 *      输出：[2,2]
 * 示例 2:
 *      输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *      输出：[4,9]
 * 说明：
 *      输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 *      我们可以不考虑输出结果的顺序。
 * 进阶：
 *      如果给定的数组已经排好序呢？你将如何优化你的算法？
 *      如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 *      如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @author ywh
 * @since 20/11/2019
 */
public class LeetCode350 {

    /**
     * Time: O(m+n), Space: O(m+k)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectHashMap(int[] nums1, int[] nums2) {
        Map<Integer, Integer> counter = new HashMap<>(nums1.length + nums2.length);

        // 统计 nums1 的元素及其出现个数
        for (int num : nums1) {
            int cnt = counter.getOrDefault(num, 0);
            counter.put(num, cnt + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            int cnt = counter.getOrDefault(num, 0);
            // 如果 counter 中存在该元素，表示可以添加到交集列表中
            if (cnt > 0) {
                list.add(num);
                // 由于数组的交集包括重复（如 nums1 和 nums2 都有 2 个 4，则都要添加进去）
                // 需要对计数器减 1，表示已取出配对
                counter.put(num, cnt - 1);
            }
        }

        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    /**
     * Time: O(m*log(m)+n*log(n)), Space: O(k)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
