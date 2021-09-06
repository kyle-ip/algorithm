package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和 II
 * [数组] [回溯]
 * 
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 *      所有数字（包括目标数）都是正整数。
 *      解集不能包含重复的组合。
 * 示例 1:
 *      输入: candidates = [10,1,2,7,6,1,5], target = 8,
 *      所求解集为:
 *      [
 *        [1, 7],
 *        [1, 2, 5],
 *        [2, 6],
 *        [1, 1, 6]
 *      ]
 * 示例 2:
 *      输入: candidates = [2,5,2,1,2], target = 5,
 *      所求解集为:
 *      [
 *        [1,2,2],
 *        [5]
 *      ]
 *
 * @author ywh
 * @since 06/09/2020
 */
public class LeetCode40 {

    /**
     *
     *
     * @param nums
     * @param target
     * @param start
     * @param elem
     * @param ret
     */
    private void combSum(int[] nums, int target, int start, LinkedList<Integer> elem, List<List<Integer>> ret) {
        if (target == 0) {
            ret.add(new ArrayList<>(elem));
        } else if (target > 0) {
            for (int i = start; i < nums.length; i++) {
                // 当前元素与上一个元素相同，跳过。
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                elem.add(nums[i]);
                // candidates 中的每个数字在每个组合中只能使用一次，因此传递到下一层递归的“start”参数需要 +1。
                combSum(nums, target - nums[i], i + 1, elem, ret);
                elem.removeLast();
            }
        }
    }

    /**
     * 类似 {@link LeetCode39}，需要先对数组排序、在每轮回溯中要对元素作判断（是否与上一个重复）。
     *
     * 参考 {@link LeetCode40} 组合总和 II、{@link LeetCode39} 组合总和、 {@link LeetCode78} 子集、{@link LeetCode46} 全排列、{@link LeetCode47} 全排列 II、{@link LeetCode77} 组合
     *
     * Time: O(n^(target/min)), Space: O(target/min)
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(candidates);
        combSum(candidates, target, 0, new LinkedList<>(), ret);
        return ret;
    }

}
