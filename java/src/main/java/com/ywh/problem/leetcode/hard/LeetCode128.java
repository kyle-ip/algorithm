package com.ywh.problem.leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列
 * [排序] [哈希表]
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 提示：
 * 0 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 *
 * @author ywh
 * @since 2019/11/12/012
 */
public class LeetCode128 {

    /**
     * Time: O(n*log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int lengthOfLongestConsecutiveSequenceSorting(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 0, p = 0, len;
        while (p < nums.length) {
            len = 1;
            while (p < nums.length - 1) {
                // 由于已排序，相邻两位元素大小不相邻表示已经断开，需要重置长度
                if (nums[p + 1] - nums[p] > 1) {
                    break;
                }
                if (nums[p + 1] - nums[p] == 1) {
                    ++len;
                }
                p++;
            }
            // 退出内层循环表示已完成一段序列的统计，下一段从 p + 1 开始
            max = Math.max(max, len);
            p++;
        }
        return max;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLongestConsecutiveSequenceSet1(int[] nums) {

        if (nums == null) {
            return 0;
        }

        // 把所有元素添加到哈希表：8, 4, 2, 1, 2, 3, 6
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        // 每轮循环从哈希表中移除当前元素，并判断以此元素为向两边扩展的其他值是否在哈希表中。
        // 如第一个被移除的元素是 8，但与之相邻的 7 和 9 不在集合中，所以长度为 (8+1)-(8-1)-1 == 1，更新到 max。
        // 如第二个被移除的元素是 4，与之一并被移除的元素还有相邻的 3、2、1，所以连续长度为 (4+1)-(4-1-1-1-1)-1 == 4。
        // 最后哈希表中剩下 6。
        int ret = 0;
        for (int i = 0; i < nums.length && !set.isEmpty(); i++) {
            set.remove(nums[i]);

            // 一边向两边扩展，一边从 set 中移除元素。
            int l = nums[i], r = nums[i];
            for (; set.contains(--l); set.remove(l))
                ;
            for (; set.contains(++r); set.remove(r))
                ;

            // 跳出上面的循环时，l、r 表示为开区间，因此统计区间长度需要减去 1（如 5 与 2 之间有 3、4，所以 5-2-1 == 2）。
            ret = Math.max(ret, r - l - 1);
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLongestConsecutiveSequenceSet2(int[] nums) {
        // 把所有元素添加到哈希表：8, 4, 2, 1, 2, 3, 6
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ret = 0;
        for (int num : set) {
            // 判断集合中的当前元素是否存在前驱数，比如 [1, 2, 3, 4]，1 没有前驱数，因此它就是连续序列的起始值。
            if (!set.contains(num - 1)) {
                int len = 1;
                // 从起始值开始，判断紧随其后的每个数是否存在与哈希表中，存在的更新长度。
                for (; set.contains(num + 1); num++, len++)
                    ;
                ret = Math.max(ret, len);
            }
        }
        return ret;
    }
}
