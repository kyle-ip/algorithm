package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 最小高度树
 * [图] [BFS] [DFS]
 *
 * @author ywh
 * @since 12/01/2020
 */
public class LeetCode310 {

    /**
     *
     *
     * @param g
     * @param visited
     * @param node
     * @param dist
     * @param max
     */
    private void dfs(List<List<Integer>> g, boolean[] visited, int node, int dist, int[] max) {
        visited[node] = true;
        if (dist > max[0]) {
            max[0] = dist;
        }
        for (int v : g.get(node)) {
            if (!visited[v]) {
                dfs(g, visited, v, dist + 1, max);
            }
        }
        visited[node] = false;
    }

    /**
     * Time: O(n^2), Space: O(n)
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTreesBruteForce(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<List<Integer>> g = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int[] heights = new int[n], max = new int[]{0};
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            dfs(g, visited, i, 0, max);
            heights[i] = max[0];
            if (heights[i] < minHeight) {
                minHeight = heights[i];
            }
            max[0] = 0;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (heights[i] == minHeight) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 收缩解法：从外围的叶子节点一步步向中心收缩（删去叶子），最后剩下的叶子节点即为中心。
     *
     * Time: O(n), Space: O(n)
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTreesShrink(int n, int[][] edges) {
        // n 个节点，以任意一个节点为根可以构成 n 棵树，其高度即与离根节点最远的节点之间的距离。
        // 最小高度树，其根应该在图的中心，距离各个节点距离最短。
        if (n == 1) {
            return Collections.singletonList(0);
        }

        //     1
        //     |
        // 2 - 0 - 3
        //         |
        //         4

        // 无向无环图以二维列表表示，存储邻接节点（每对节点的邻接节点都包含对方）。
        List<List<Integer>> g = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }

        // 存储节点的度。
        int[] degree = new int[n];

        // 存储每次待处理的叶子节点（队列）。
        LinkedList<Integer> leaves = new LinkedList<>();

        // 遍历所有节点，节点的度即为该节点邻接节点的个数；度为 1 表示这是叶子节点，添加到 leaves。
        for (int i = 0; i < n; ++i) {
            degree[i] = g.get(i).size();
            if (degree[i] == 1) {
                leaves.add(i);
            }
        }
        // 所有叶子向内收缩：直到所有节点都是叶子节点（如果叶子节点数量小于当前节点数 n，表示叶子节点中间还有节点）。
        while (leaves.size() < n) {
            // 每轮循环剪去最外围的 size 个叶子节点（向内收缩一圈）。
            int size = leaves.size();
            n -= size;
            // 处理一圈叶子节点：
            //          [1]
            //           |
            //     [2] - 0 - 3
            //               |
            //              [4]
            for (int i = 0; i < size; ++i) {
                // 对于每个叶子节点，依次取出其邻接节点，邻接节点的度都 -1，表示剪去这个叶子节点。
                int leaf = leaves.poll();
                for (int v : g.get(leaf)) {
                    // 如果邻接节点剪去叶子节点后度为 1，表示产生了新的叶子节点，添加到队列：
                    //         1        处理 [4] 时取出了邻接节点 [3]，[3] 剪去 [4] 后度为 1，因此把 [3] 添加到队列。
                    //         |
                    //     2 - 0 - (3)
                    //              x
                    //             [4]
                    if (--degree[v] == 1) {
                        leaves.add(v);
                    }
                }
            }
        }
        // 把 1、2、4 剪去后剩下 0 - 3，两个叶子节点都可以在原图中作为树根，因此返回。
        return leaves;
    }

}
