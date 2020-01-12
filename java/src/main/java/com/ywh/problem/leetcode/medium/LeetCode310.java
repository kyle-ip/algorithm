package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 高度最小的树
 * [图] [BFS] [DFS]
 *
 * @author ywh
 * @since 12/01/2020
 */
public class LeetCode310 {

    private void dfs(List<List<Integer>> g, boolean[] visited, int node, int dist, int[] max) {
        visited[node] = true;
        if (dist > max[0]) {
            max[0] = dist;
        }
        for (int v: g.get(node)) {
            if (!visited[v]) {
                dfs(g, visited, v, dist+1, max);
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
        for (int[] e: edges) {
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
     * Time: O(n), Space: O(n)
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTreesShrink(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<List<Integer>> g = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<>());
        }
        for (int[] e: edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }

        int[] degree = new int[n];
        LinkedList<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            degree[i] = g.get(i).size();
            if (degree[i] == 1) {
                leaves.add(i);
            }
        }
        while (leaves.size() < n) {
            int size = leaves.size();
            n -= size;
            for (int i = 0; i < size; ++i) {
                int leaf = leaves.poll();
                for (int v: g.get(leaf)) {
                    --degree[v];
                    if (degree[v] == 1) {
                        leaves.add(v);
                    }
                }
            }
        }
        return leaves;
    }

}
