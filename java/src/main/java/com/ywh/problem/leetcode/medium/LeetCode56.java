package com.ywh.problem.leetcode.medium;

import com.ywh.model.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 区间合并
 * [数组] [排序]
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
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();

        // 先对区间数组排序（根据左端点大小），intervals.sort((a, b) -> a.start - b.start);
        intervals.sort(Comparator.comparingInt(a -> a.start));
        for (Interval interval: intervals) {
            int n = result.size();
            // 当结果数组为空，或结果数组最后区间的右端点小于当前区间的左端点，区间直接加入结果数组。
            // [..., [3, 6]] <- [8, 9] => [..., [3, 6], [8, 9]]
            if (result.isEmpty() || result.get(n - 1).end < interval.start) {
                result.add(interval);
            }
            // 否则当前区间的左端点包含在结果数组的最后区间内，此时如果当前区间的右端点大于结果数组最后区间的右端点，合并最后区间。
            //           +----------+           x  x
            //           ↓          |        3, 4, 6, 7
            // [..., [3, 6]] <- [4, 7] => [..., [3, 7]]
            //                                  x  x
            //                               3, 4, 5, 6
            // [..., [3, 6]] <- [4, 5] => [..., [3, 6]]
            else {
                result.get(n - 1).end = Math.max(result.get(n - 1).end, interval.end);
            }
        }
        return result;
    }
}
