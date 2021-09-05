package com.ywh.problem.leetcode.hard;

import java.util.TreeMap;

/**
 * 滑动窗口最大值
 * [堆] [滑动窗口]
 * 
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。返回滑动窗口中的最大值。
 * 示例 1：
 *      输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 *      输出：[3,3,5,5,6,7]
 *      解释：
 *          滑动窗口的位置                最大值
 *          ---------------               -----
 *          [1  3  -1] -3  5  3  6  7       3
 *           1 [3  -1  -3] 5  3  6  7       3
 *           1  3 [-1  -3  5] 3  6  7       5
 *           1  3  -1 [-3  5  3] 6  7       5
 *           1  3  -1  -3 [5  3  6] 7       6
 *           1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *      输入：nums = [1], k = 1
 *      输出：[1]
 * 示例 3：
 *      输入：nums = [1,-1], k = 1
 *      输出：[1,-1]
 * 示例 4：
 *      输入：nums = [9,11], k = 2
 *      输出：[11]
 * 示例 5：
 *      输入：nums = [4,-2], k = 2
 *      输出：[4]
 * 提示：
 *      1 <= nums.length <= 10^5
 *      -10^4 <= nums[i] <= 10^4
 *      1 <= k <= nums.length
 *
 * @author ywh
 * @since 18/11/2019
 */
public class LeetCode239 {

    /**
     * 暴力解法，每 k 个元素算依次最大值，填充到结果数组。
     *
     * Time: O(k*n), Space: O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxNumInSlidingWindowBruteForce(int[] nums, int k) {
        int n = nums.length;
        int[] ret = new int [n - k + 1];
        for (int l = 0; l < n - k + 1; l++) {
            int max = nums[l];
            for (int r = l; r < l + k; r++) {
                max = Math.max(max, nums[r]);
            }
            ret[l] = max;
        }
        return ret;
    }

    /**
     * 使用大小为 k 的红黑树保存滑动窗口内的数字：
     * 取最大值、移除窗口最左边的值、加入新值的时间复杂度都为 O(log(k))
     *
     * Time: O(n*log(k)), Space: O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxNumInSlidingWindowTreeMap(int[] nums, int k) {
        int n = nums.length, p = 0;

        // key 为数组中的值，value 为下标，把前 k 个数字加到 map
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            map.put(nums[i], i);
        }
        int [] ret = new int[n - k + 1];

        // map 默认从小到大排序，因此取最后一个
        ret[p++] = map.lastKey();

        for (int i = k; i < n; i++) {
            // 由于数组中存在相等的值，在取出窗口最左边的数字对应下标时，要判断是否等于 i - k（即 ）
            // 如果相等，表示当前窗口最左边的数字，移除；如果不相等，表示已经被后来出现的相同数字覆盖，忽略
            if (map.get(nums[i - k]) == i - k) {
                map.remove(nums[i - k]);
            }

            // 把当前位置元素及下标加入窗口
            map.put(nums[i], i);

            // 取出最大值，填充结果数组
            ret[p++] = map.lastKey();
        }
        return ret;
    }

    /**
     * 使用两个分组辅助数组
     *
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxNumInSlidingWindowOn(int[] nums, int k) {
        int n = nums.length;

        // 结果数组，从左到右分组最大值，从右到左分组最大值。
        int[] ret = new int[n - k + 1], lMax = new int[n], rMax = new int[n];

        // 一轮循环，从左到右、从右到左求分组长度为 k 的阶段最大值。
        for (int i = 0, j = n - 1; i < n; i++, j--) {

            // 可以整除 k，表示分组内的第一个值，最大值为自身；否则与上一个最大值对比。
            // [0, 4, 4], [1, 1, 8], 2
            lMax[i] = i % k == 0? nums[i]: Math.max(lMax[i - 1], nums[i]);

            // 除以 k 余 k - 1，表示分组内最后一个值，最大值为自身；否则与上一个最大值对比。
            // [4, 4, 2], [8, 8, 8], 2
            rMax[j] = j == n - 1 || j % k == k - 1? nums[j]: Math.max(rMax[j + 1], nums[j]);
        }

        // [1, 2, 3, 4, 5, 6, 7]
        //  ↑     ↑
        //     ↑     ↑ ...
        //              ↑     ↑
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Math.max(lMax[i], rMax[i + k - 1]);
        }
        return ret;
    }
}
