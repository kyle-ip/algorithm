package com.ywh.problem.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 会议室 II
 * [排序] [贪心] [堆] [扫描线]
 * 
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi]，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 * 示例 1：
 *      输入：intervals = [[0,30],[5,10],[15,20]]
 *      输出：2
 * 示例 2：
 *      输入：intervals = [[7,10],[2,4]]
 *      输出：1
 * 提示：
 *      1 <= intervals.length <= 10^4
 *      0 <= starti < endi <= 10^6
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
    public int minMeetingRooms(int[][] intervals) {

        // 对于每个区间，按会议开始时间从小到大排序
        // intervals: [2, 4], [4, 6], [1, 3] => [1, 3], [2, 4], [4, 6]
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // 创建最小堆（每次调用 poll 从中获取最小元素），存放最近一场会议的结束时间。
        Queue<Integer> minHeap = new PriorityQueue<>();

        // 遍历所有会议区间：[1, 3], [2, 4], [4, 6]
        for (int[] meeting : intervals) {
            // 如果堆顶最小值小于等于当前会议区间的开始时间：
            // 现存的所有会议房间里、最早结束的那个，在当前会议开始前就已经结束，因此可以空出来，不需要另外开设房间（出堆）
            // 三间     =>    两间
            // [1, 3]        [1, 3]
            // [2, 4]   =>   [2, 4], [4, 6]
            // [4, 6]

            // meeting: [4, 6], heapPeek: [4, 5] => heapPeak: [4]，表示可复用房间。
            if (minHeap.size() > 0 && minHeap.peek() <= meeting[0]) {
                minHeap.poll();
            }
            // heap: [4] => heap: [6, 4]，表示更新会议结束时间。
            minHeap.add(meeting[1]);
        }
        return minHeap.size();
    }
}
