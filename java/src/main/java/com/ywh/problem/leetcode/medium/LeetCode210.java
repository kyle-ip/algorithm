package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 上课顺序
 * 参考 {@link LeetCode207}
 * <p>
 * [图] [回溯] [拓扑排序] [DFS] [BFS]
 *
 * @author ywh
 * @since 22/12/2019
 */
public class LeetCode210 {

    /**
     * @param graph
     * @param visited
     * @param checked
     * @param order
     * @param v
     * @return
     */
    private boolean hasCycle(List<List<Integer>> graph, boolean[] visited, boolean[] checked,
                             List<Integer> order, int v) {
        if (visited[v]) {
            return true;
        }
        visited[v] = true;
        for (int i : graph.get(v)) {
            if (!checked[i] && hasCycle(graph, visited, checked, order, i)) {
                return true;
            }
        }
        checked[v] = true;
        visited[v] = false;

        // 在深度遍历的过程，在回溯的阶段把已访问、已检查的节点添加到序列中
        order.add(v);
        return false;
    }

    /**
     * Time: O(V+E), Space: O(V+E)
     *
     * @param n
     * @param pairs
     * @return
     */
    public int[] findOrderDFS(int n, int[][] pairs) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int[] pair : pairs) {
            graph.get(pair[1]).add(pair[0]);
        }

        boolean[] checked = new boolean[n], visited = new boolean[n];
        List<Integer> order = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (!checked[i] && hasCycle(graph, visited, checked, order, i)) {
                return new int[]{};
            }
        }

        // 按回溯的顺序，倒置即为排列
        int[] ret = new int[n];
        for (int v : order) {
            ret[--n] = v;
        }

        return ret;
    }

    /**
     * Time: O(V+E), Space: O(V+E)
     *
     * @param n
     * @param pairs
     * @return
     */
    public int[] findOrderTopoSort(int n, int[][] pairs) {

        int[] inDegree = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int[] pair : pairs) {
            graph.get(pair[1]).add(pair[0]);
            inDegree[pair[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        int[] order = new int[n];
        int size = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            order[size++] = v;
            for (int i : graph.get(v)) {
                --inDegree[i];
                if (inDegree[i] == 0) {
                    q.add(i);
                }
            }
        }
        return size == n ? order : new int[]{};
    }
}
