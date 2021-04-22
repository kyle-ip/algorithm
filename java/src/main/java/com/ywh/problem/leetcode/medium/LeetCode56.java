package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 * [数组] [排序]
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 *      输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *      输出: [[1,6],[8,10],[15,18]]
 *      解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *      输入: intervals = [[1,4],[4,5]]
 *      输出: [[1,5]]
 *      解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 * 提示：
 *      intervals[i][0] <= intervals[i][1]
 *
 * @author ywh
 * @since 2019/10/30
 */
public class LeetCode56 {

    /**
     * Time: O(n*log(n)), Space: O(1)
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> ret = new ArrayList<>();
        // 先对区间数组排序（根据左端点大小），intervals.sort((a, b) -> a.start - b.start);
        Arrays.sort(intervals, Comparator.comparingInt(r -> r[0]));
        for (int[] interval : intervals) {
            int n = ret.size(), start = interval[0], end = interval[1];
            // 当结果数组为空，或结果数组最后区间的右端点小于当前区间的左端点，区间直接加入结果数组。
            // [..., [3, 6]] <- [8, 9] => [..., [3, 6], [8, 9]]
            if (n == 0 || ret.get(n - 1)[1] < start) {
                ret.add(interval);
            }
            // 否则当前区间的左端点包含在结果数组的最后区间内，此时如果当前区间的右端点大于结果数组最后区间的右端点，合并最后区间。
            //           +----------+           x  x
            //           ↓          |        3, 4, 6, 7
            // [..., [3, 6]] <- [4, 7] => [..., [3, 7]]
            //                                  x  x
            //                               3, 4, 5, 6
            // [..., [3, 6]] <- [4, 5] => [..., [3, 6]]
            else {
                ret.get(n - 1)[1] = Math.max(ret.get(n - 1)[1], end);
            }
        }
        return ret.toArray(new int[ret.size()][]);
    }
}
