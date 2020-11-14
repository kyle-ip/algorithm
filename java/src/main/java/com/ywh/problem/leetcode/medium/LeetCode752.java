package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 打开转盘锁
 * [BFS]
 *
 * @author ywh
 * @since 2020/11/14
 */
public class LeetCode752 {

    /**
     * 描述达到某种状态需要经过多少步骤、哪些步骤，本质上是图论问题：
     * 1. 一次变动可以达到多种状态：对应无向图的边。
     * 2. 一个状态可以由多个状态转换而成：对应图顶点可以被重复访问。
     * 3. 存在不可达的状态：对应两顶点之间的边不存在。
     *         +-----> [1000] ------+
     *         |                    ↓
     *      [0000] --> [0100] --> [1100] --> ... --> [6666]
     *         |  ...
     *         |
     *         +-----> [0900] -x> [0901]
     *
     * Time: O(N^2∗A^N+D), Space: O(A^N+D)（A 表示数字的个数，N 表示状态的位数，D 表示数组 deadends 的大小）
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {

        // 死亡状态集合，包含最少步数的已访问集合，状态转换数组，BFS 队列。
        HashSet<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        String start = "0000";

        // 死亡集合中包含开始或最终状态，则不能经过任何转换达到，返回 -1。
        if (deadSet.contains(target) || deadSet.contains(start)) {
            return -1;
        }

        // 开始状态即为最终状态，返回 0。
        if (target.equals(start)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();

        // 每种状态可以转动四个转轮，每次转动可以转 1 下或转 9 下（反方向转 1 下）。
        int[][] dirs = new int[][]{{0, 1}, {1, 1}, {2, 1}, {3, 1}, {0, 9}, {1, 9}, {2, 9}, {3, 9}};

        // 记录到达已访问的状态，最少需要多少步。
        Map<String, Integer> visited = new HashMap<>();
        queue.add(start);
        visited.put(start, 0);
        while (!queue.isEmpty()) {
            String curs = queue.poll();
            // 循环处理八个方向。
            for (int[] dir : dirs) {
                char[] curArr = curs.toCharArray();
                // 取出第 dir[0] 位的字符并转换为数字、加上 dir[1] 并取模。
                int num = (curArr[dir[0]] - '0' + dir[1]) % 10;
                // 把 num 转换为字符并重新设回。
                curArr[dir[0]] = Character.forDigit(num, 10);
                String next = new String(curArr);

                // next 是死亡状态或 next 已被访问，跳过。
                if (deadSet.contains(next) || visited.containsKey(next)) {
                    continue;
                }
                // 到达最终状态，返回。
                if (next.equals(target)) {
                    return visited.get(curs) + 1;
                }
                queue.add(next);
                visited.put(next, visited.get(curs) + 1);
            }
        }
        return -1;
    }


}
