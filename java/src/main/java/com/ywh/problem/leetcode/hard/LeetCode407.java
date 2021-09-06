package com.ywh.problem.leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 接雨水 II
 * [堆] [广度优先搜索]
 *
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * 示例：
 *      给出如下 3x6 的高度图:
 *      [
 *        [1,4,3,1,3,2],
 *        [3,2,1,3,2,4],
 *        [2,3,3,2,3,1]
 *      ]
 *      返回 4 。
 * 提示：
 *      1 <= m, n <= 110
 *      0 <= heightMap[i][j] <= 20000
 *
 * @author ywh
 * @since 4/4/2021
 */
public class LeetCode407 {



    /**
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length, m = heightMap[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // 最小堆存放矩阵（比较的值为高度），每次返回高度最低的坐标，处理其四周。
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        // 处理第一列、最后一列。
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{i, 0, heightMap[i][0]});
            queue.add(new int[]{i, m - 1, heightMap[i][m - 1]});
            visited[i][0] = visited[i][m - 1] = true;
        }

        // 处理第一行、最后一行。
        for (int i = 0; i < m; i++) {
            queue.add(new int[]{0, i, heightMap[0][i]});
            queue.add(new int[]{n - 1, i, heightMap[n - 1][i]});
            visited[0][i] = visited[n - 1][i] = true;
        }

        int ret = 0;
        while (!queue.isEmpty()) {
            int[] elem = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = elem[0] + dirs[i][0], nextY = elem[1] + dirs[i][1];

                // 下一个坐标未越界，且未被访问则处理：
                if (nextX >= 0 && nextY >= 0 && nextX < n && nextY < m && !visited[nextX][nextY]) {

                    // 下一个坐标高度比当前坐标小，可积水。
                    if (elem[2] > heightMap[nextX][nextY]) {
                        ret += (elem[2] - heightMap[nextX][nextY]);
                    }
                    visited[nextX][nextY] = true;

                    // 添加相邻节点、其高度取相邻两点高度中的较高者。
                    queue.add(new int[]{nextX, nextY, Math.max(elem[2], heightMap[nextX][nextY])});
                }
            }
        }
        return ret;
    }

}
