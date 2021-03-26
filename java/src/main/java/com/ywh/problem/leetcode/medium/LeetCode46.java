package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 全排列
 * [回溯]
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 示例：
 *      输入: [1,2,3]
 *      输出:
 *      [
 *        [1,2,3],
 *        [1,3,2],
 *        [2,1,3],
 *        [2,3,1],
 *        [3,1,2],
 *        [3,2,1]
 *      ]
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode46 {

    /**
     *
     * @param nums      当前的一个排列
     * @param start     开始下标（划分子数组）
     * @param ret       全排列结果
     */
    private void permuteRec(List<Integer> nums, int start, List<List<Integer>> ret) {
        // 当起始位置 == 排列长度，表示得到一个排列。
        if (start == nums.size()) {
            ret.add(new ArrayList<>(nums));
            return;
        }
        // 把子数组的元素依次和 start 的位置交换（即固定到子数组的第一个位置），求后面的子数组的全排列即可。
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, i, start);

            // 递归地求从 start 开始的子数组的全排列。
            permuteRec(nums, start + 1, ret);

            // 回溯：每次递归结束都交换回来，开始新一轮的元素交换。
            Collections.swap(nums, i, start);
        }
    }

    /**
     * 参考 {@link LeetCode78} 子集、{@link LeetCode46} 全排列、{@link LeetCode47} 全排列 II、{@link LeetCode77} 组合
     *
     * Time: O(n*n!), Space: O(n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num: nums) {
            list.add(num);
        }
        permuteRec(list, 0, ret);
        return ret;
    }

}
