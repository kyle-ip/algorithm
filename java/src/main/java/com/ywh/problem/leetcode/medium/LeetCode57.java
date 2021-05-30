package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入区间
 * [排序] [数组]
 *
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1：
 *      输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 *      输出：[[1,5],[6,9]]
 * 示例 2：
 *      输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *      输出：[[1,2],[3,10],[12,16]]
 *      解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 *      输入：intervals = [], newInterval = [5,7]
 *      输出：[[5,7]]
 * 示例 4：
 *      输入：intervals = [[1,5]], newInterval = [2,3]
 *      输出：[[1,5]]
 * 示例 5：
 *      输入：intervals = [[1,5]], newInterval = [2,7]
 *      输出：[[1,7]]
 * 提示：
 *      0 <= intervals.length <= 10^4
 *      intervals[i].length == 2
 *      0 <= intervals[i][0] <= intervals[i][1] <= 10^5
 *      intervals 根据 intervals[i][0] 按 升序 排列
 *      newInterval.length == 2
 *      0 <= newInterval[0] <= newInterval[1] <= 10^5
 *
 * @author ywh
 * @since 5/4/2021
 */
public class LeetCode57 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int l = newInterval[0], r = newInterval[1];
        boolean placed = false;
        List<int[]> retList = new ArrayList<>();
        for (int[] interval : intervals) {

            // [[1, 3],       [6, 9]]
            //         [2, 5]
            if (interval[0] > r) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    retList.add(new int[]{l, r});
                    placed = true;
                }
                retList.add(interval);
            }
            else if (interval[1] < l) {
                // 在插入区间的左侧且无交集
                retList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                l = Math.min(l, interval[0]);
                r = Math.max(r, interval[1]);
            }
        }
        if (!placed) {
            retList.add(new int[]{l, r});
        }
        int[][] ret = new int[retList.size()][2];
        for (int i = 0; i < retList.size(); ++i) {
            ret[i] = retList.get(i);
        }
        return ret;
    }

}
