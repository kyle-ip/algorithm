package com.ywh.problem.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二分图
 * [图] [DFS] [BFS]
 *
 * @author ywh
 * @since 2020/11/12/012
 */
public class LeetCode785 {

    /**
     * 深度优先遍历
     * Time: O(V+E), Space: O(V)
     *
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
         int V = graph.length;
         int[] visited = new int[graph.length];
        for (int v = 0; v < graph.length; v++) {
            if (visited[v] != 0) {
                continue;
            }
            if (!dfs(v, 1, visited, graph)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 深度优先遍历，如果非二分图返回 false。
     * Time: O(V+E), Space: O(V)
     *
     * @param v
     * @param color
     * @param visited
     * @param graph
     * @return
     */
    private boolean dfs(int v, int color, int[] visited, int[][] graph) {
        visited[v] = color;
        for (int w : graph[v]) {
            if (visited[w] == 0) {
                if (!dfs(w, -color, visited, graph)) {
                    return false;
                }
            } else if (visited[w] == visited[v]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 广度优先遍历
     *
     * @param graph
     * @return
     */
    public static boolean isBipartite2(int[][] graph) {
        int[] visited = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (visited[i] != 0) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            visited[i] = 1;
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int w : graph[v]) {
                    if (visited[w] == 0) {
                        visited[w] = -visited[v];
                        queue.add(w);
                    } else if (visited[w] == visited[v]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isBipartite2(new int[][]{{1,3},{0,2},{1,3},{0,2}}));
    }
}
