package com.ywh.problem.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 两个数组的交集
 * [排序] [哈希表] [双指针] [二分搜素]
 *
 * @author ywh
 * @since 2020/9/18 11:19
 */
public class LeetCode349 {

    /**
     * Time: O(max(mlog(m), nlog(n))), Space: O(min(m, n))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0, p2 = 0, i = 0;
        Set<Integer> set = new HashSet<>();
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                set.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        Iterator<Integer> it = set.iterator();
        int[] ret = new int[set.size()];
        while (it.hasNext()) {
            ret[i++] = it.next();
        }
        return ret;
    }

    /**
     * Time: O(m+n), Space: O(m+n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>(), s2 = new HashSet<>();
        for (int i : nums1) {
            s1.add(i);
        }
        for (int i : nums2) {
            s2.add(i);
        }
        s2.retainAll(s1);
        int i = 0;
        int[] ret = new int[s2.size()];
        for (int elem : s2) {
            ret[i++] = elem;
        }
        return ret;
    }
}