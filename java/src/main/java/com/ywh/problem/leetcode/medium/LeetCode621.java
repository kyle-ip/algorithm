package com.ywh.problem.leetcode.medium;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 任务调度器
 * [贪心] [队列] [堆]
 *
 * @author ywh
 * @since 27/10/2020
 */
public class LeetCode621 {

    /**
     * Time: O(m), Space: O(1)
     * m 最大值为 log26，辅助空间最大为 26。
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastIntervalMaxHeap(char[] tasks, int n) {
        // 由于连续执行两个相同任务需要 1 个时间间隔，可以理解为以 n+1 个时间单位为一组。
        // 比如 n = 2，则执行 AAA：A _ _ A _ _ A，每组内的 n+1 个时间单位可以添加 n+1 个不同的任务（不足则闲置）。
        // 中间可以插入 BBBD：       B D   B     B，因此可以认为 ABD、AB_、AB 三个分组。
        // 因此总时间由出现次数最多的任务决定（能提供更多间隔供其他任务插入），需要对任务按出现次数从多到少来排列，并统计出现次数。
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        // 统计每种任务出现次数。
        int[] freqs = new int[26];
        for (char t : tasks) {
            ++freqs[t - 'A'];
        }
        // 最大堆，堆顶元素为最大值（出现次数最多的任务的次数）。
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for (int freq : freqs) {
            if (freq != 0) {
                q.add(freq);
            }
        }

        // ret 表示累加时间单位、idle 表示分组内闲置的时间单位（最后一个分组的闲置时间，但不需要统计到最终结果，所以随后要减去）。
        int ret = 0, idle = 0;
        while (!q.isEmpty()) {
            // n == 2 时，两个 A 之间有 1 个 n，因此到处理下一个 A 需要加上 n + 1 即 3。
            ret += (n + 1);
            // 共有 A、B、D 三种任务，所以闲置时间为 n + 1 - 3 == 0，分组中的停顿时间为 0。
            // 在下一次循环中，由于 D 已经执行完，一个分组内只能执行 A、B，因此 idle 为 3 - 2 == 1。
            idle = n + 1 - q.size();
            int size = Math.min(n + 1, q.size());
            // 把堆中 top3 的元素出堆，且频率都 -1（即 3、3、1 => 2、2、0），视为执行了一个分组：ABD。
            for (int i = 0; i < size; ++i) {
                freqs[i] = q.poll() - 1;
            }
            // 把剩余次数不为 0 的重新入堆（即 A、B）供下一个分组执行。
            for (int i = 0; i < size; ++i) {
                if (freqs[i] != 0) {
                    q.add(freqs[i]);
                }
            }
        }
        return ret - idle;
    }

    /**
     * Time: O(m), Space: O(1)
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastIntervalMath(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }

        // 字符转数字，统计每个任务的执行次数。
        int[] freqs = new int[26];
        for (char t : tasks) {
            ++freqs[t - 'A'];
        }
        // maxFreq 表示出现次数最多的任务的次数，cnt 表示出现次数最多的任务的数量（AAABBBC，A 和 B 都是出现 maxFreq 次，则 cnt == 2）。
        int maxFreq = 0, cnt = 0;
        for (int freq : freqs) {
            if (freq > maxFreq) {
                maxFreq = freq;
                cnt = 1;
            } else if (freq == maxFreq) {
                ++cnt;
            }
        }

        // 最终的递推式为 (maxFreq - 1) * (n + 1) + cnt，即：(分组个数 - 1) * 分组大小 + 最大值个数，其中分组大小 == 间隔 + 1。
        // 比如 A 和 B 都出现了 3 次（最多），其执行间隔为 2（分组为 3），因此 2 * 3 == 6，最后要加上多出来的、不足以填充一个间隔的 2。
        int ret = (n + 1) * (maxFreq - 1) + cnt;

        // 当任务种类很多，总是可以把这些任务插入到由最大值（A）形成的间隔中；
        // 由于多出的部分可以排到后面，最多不超过任务时间总和（包括闲置时间），因此要取递推式和数组长度之间的较大者。
        return Math.max(ret, tasks.length);
    }

}
