package com.ywh.problem.leetcode.medium;

import com.ywh.model.Interval;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 需要的最少会议室数量
 * [排序] [贪心] [堆] [扫描线]
 *
 * @author ywh
 * @since 2020/9/11/011
 */
public class LeetCode253 {

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }
        // 对于每个区间，按会议开始时间从小到大排序
        // intervals: [2, 4], [4, 6], [1, 3] => [1, 3], [2, 4], [4, 6]
        intervals.sort((a, b) -> a.start - b.start);

        // 创建最小堆（每次调用 poll 从中获取最小元素），存放会议结束时间
        Queue<Integer> minHeap = new PriorityQueue<>();

        // 遍历所有会议区间：[1, 3], [2, 4], [4, 6]
        for (Interval meeting : intervals) {
            // 如果堆顶（最小）值小于等于当前会议区间的开始时间：
            // 现存的所有会议房间里、最早结束的那个，在当前会议开始前就已经结束，因此可以空出来，不需要另外开设房间（出堆）
            // 三间     =>    两间
            // [1, 3]        [1, 3]
            // [2, 4]   =>   [2, 4], [4, 6]
            // [4, 6]

            // interval: [4, 6], heap: [4, 3] => heap: [4]
            if (minHeap.size() > 0 && minHeap.peek() <= meeting.start) {
                minHeap.poll();
            }
            // heap: [4] => heap: [6, 4]
            minHeap.add(meeting.end);
        }
        return minHeap.size();
    }
}
