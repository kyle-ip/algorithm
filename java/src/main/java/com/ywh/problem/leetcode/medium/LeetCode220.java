package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 存在重复元素 III
 * [排序] [Ordered Map]
 * 
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，
 * 使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 * 示例 1：
 *      输入：nums = [1,2,3,1], k = 3, t = 0
 *      输出：true
 * 示例 2：
 *      输入：nums = [1,0,1,1], k = 1, t = 2
 *      输出：true
 * 示例 3：
 *      输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 *      输出：false
 * 提示：
 *      0 <= nums.length <= 2 * 10^4
 *      -2^31 <= nums[i] <= 2^31 - 1
 *      0 <= k <= 10^4
 *      0 <= t <= 2^31 - 1
 *
 * @author ywh
 * @since 4/19/2021
 */
public class LeetCode220 {

    /**
     * 滑动窗口 + 有序集合
     *
     * Time: O(n*log(min(n, k))), Space: O(min(n, k))
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 桶
     *
     * Time: O(n), Space: O(min(n, k))
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 对于元素 x，区间 [x-t, x+t] 。设桶大小为 t+1。
        // 如果两个元素同属一个桶，这两个元素必然符合条件。
        // 如果两个元素属于相邻桶，需要校验这两个元素是否差值不超过 t。
        // 如果两个元素既不属于同一个桶，也不属于相邻桶，这两个元素必然不符合条件。
        Map<Long, Long> map = new HashMap<>();

        // 遍历序列，假设遍历的当前元素 x = nums[i]，首先检查 x 所属于的桶是否已经存在元素，是则找到一对符合条件的元素，
        // 否则继续检查两个相邻的桶内是否存在符合条件的元素。
        for (int i = 0; i < nums.length; i++) {
            // 获取当前元素所属的桶 id。
            long id = getID(nums[i], t + 1);

            // 已存在于 map 中，返回 true。
            if (map.containsKey(id)) {
                return true;
            }

            // 相邻的 id 存在于 map 中，其绝对值也符合要求，返回 true。
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) <= t) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) <= t) {
                return true;
            }

            // 否则把 id 和值的映射关系存放在 map 中。
            map.put(id, (long) nums[i]);

            // 如果遍历的下标已大于等于 k，则每次从 map 中移除窗口左边的值（满足条件 abs(i - j) <= k）。
            if (i >= k) {
                map.remove(getID(nums[i - k], t + 1));
            }
        }
        return false;
    }

    /**
     * 将 nums[i] 表示为 x=(t+1)*a+b 的形式，x 即属于编号为 a 的桶。
     *
     * @param x
     * @param w     两值绝对值的最大值
     * @return
     */
    public long getID(long x, long w) {
        // 比如输入 w == 2，分桶规则：
        // ... [-2, -1], [0, 1], [2, 3] ...
        // ...    -1       0       1    ...
        return x >= 0? x / w: (x + 1) / w - 1;
    }
}
