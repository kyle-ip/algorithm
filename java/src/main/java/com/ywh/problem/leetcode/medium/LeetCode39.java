package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和
 * [数组] [回溯]
 * 
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 *      所有数字（包括 target）都是正整数。
 *      解集不能包含重复的组合。
 * 示例 1：
 *      输入：candidates = [2,3,6,7], target = 7,
 *      所求解集为：
 *      [
 *        [7],
 *        [2,2,3]
 *      ]
 * 示例 2：
 *      输入：candidates = [2,3,5], target = 8,
 *      所求解集为：
 *      [
 *        [2,2,2,2],
 *        [2,3,3],
 *        [3,5]
 *      ]
 * 提示：
 *      1 <= candidates.length <= 30
 *      1 <= candidates[i] <= 200
 *      candidate 中的每个元素都是独一无二的。
 *      1 <= target <= 500
 *
 * @author ywh
 * @since 2019/11/29/029
 */
public class LeetCode39 {

    /**
     *
     * @param nums
     * @param target
     * @param start
     * @param elem
     * @param ret
     */
    private List<List<Integer>> combSumSort(int[] nums, int target, int start, LinkedList<Integer> elem, List<List<Integer>> ret) {
        if (target == 0) {
            ret.add(new ArrayList<>(elem));
        } else if (target > 0) {
            // 由于已排序，当前元素大于剩余目标值，剩余元素必然大于目标值，不需要继续拼凑。
            for (int i = start; i < nums.length && nums[i] <= target; i++) {
                elem.add(nums[i]);
                // 由于允许多次使用同一个元素，传递到下一层递归的 start 不需要 +1。
                combSumSort(nums, target - nums[i], i, elem, ret);
                elem.removeLast();
            }
        }
        return ret;
    }

    /**
     * 参考 {@link LeetCode39} 组合总和、 {@link LeetCode78} 子集、{@link LeetCode46} 全排列、{@link LeetCode47} 全排列 II、{@link LeetCode77} 组合
     *
     * Time: O(n^(target/min)), Space: O(target/min)
     * 
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSumSort(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combSumSort(candidates, target, 0, new LinkedList<>(), new ArrayList<>());
    }
}
