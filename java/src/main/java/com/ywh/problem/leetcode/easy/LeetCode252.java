package com.ywh.problem.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 会议室
 * [排序]
 * 
 * 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
 * 请你判断一个人是否能够参加这里面的全部会议。
 * 示例 1：
 *      输入：intervals = [[0,30],[5,10],[15,20]]
 *      输出：false
 * 示例 2：
 *      输入：intervals = [[7,10],[2,4]]
 *      输出：true
 * 提示：
 *      0 <= intervals.length <= 10^4
 *      intervals[i].length == 2
 *      0 <= starti < endi <= 10^6
 *
 * @author ywh
 * @since 23/06/2020
 */
public class LeetCode252 {

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(int[][] intervals) {
        // 先对区间按开始时间做排序，(a, b) -> a.start - b.start。
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        // 逐个区间判断，如果当前区间的结束时间比下一个区间的开始时间大，则返回错误。
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }
}
