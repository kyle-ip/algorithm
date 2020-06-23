package com.ywh.problem.leetcode.medium;

import com.ywh.model.Interval;

import java.util.Comparator;
import java.util.List;

/**
 * 能否参加所有会议
 * [排序]
 *
 * @author ywh
 * @since 23/06/2020
 */
public class LeetCode252 {

    /**
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(List<Interval> intervals) {

        // 先对区间按开始时间做排序，(a, b) -> a.start - b.start
        intervals.sort(Comparator.comparingInt(a -> a.start));

        // 逐个区间判断，如果当前区间的结束时间比下一个区间的开始时间大，则返回错误
        for (int i = 0; i < intervals.size() - 1; ++i) {

            if (intervals.get(i).end > intervals.get(i + 1).start) {
                return false;
            }
        }
        return true;
    }
}
