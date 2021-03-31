package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 课程表
 * [图] [回溯] [拓扑排序] [DFS] [BFS]
 * 
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
 * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 示例 1：
 *      输入：numCourses = 2, prerequisites = [[1,0]]
 *      输出：true
 *      解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 *      输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 *      输出：false
 *      解释：总共有 2 门课程。学习课程 1 之前，你需要先完成 课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * 提示：
 *      1 <= numCourses <= 10^5
 *      0 <= prerequisites.length <= 5000
 *      prerequisites[i].length == 2
 *      0 <= ai, bi < numCourses
 *      prerequisites[i] 中的所有课程对 互不相同
 *
 * @author ywh
 * @since 20/12/2019
 */
public class LeetCode207 {

    /**
     * 判断有向图是否有环
     *
     * 指向：<-
     * (1, 0)
     * (3, 0)
     * (3, 1)
     * (2, 1)
     * (2, 3)
     * (4, 2)
     * (4, 3)
     *
     *  0 ---> 3 ---> 4
     *   \    ^ \    ^
     *    \  /   \  /
     *     v/     v/
     *     1 ---> 2
     *
     * @param graph
     * @param visited   记录访问过的节点
     * @param checked   记录已检查且不会有环的节点
     * @param v
     * @return
     */
    private boolean hasCycle(List<List<Integer>> graph, boolean[] visited, boolean[] checked, int v) {
        // 传入的元素被再次访问，返回有环
        if (visited[v]) {
            return true;
        }

        // 标记当前节点为已访问
        visited[v] = true;

        // 访问该节点的邻接节点
        for (int i : graph.get(v)) {

            // 如果该邻接节点未检查过，且递归调用自身返回 true（有环），表示有环，直接返回
            if (!checked[i] && hasCycle(graph, visited, checked, i)) {
                return true;
            }
        }

        // 邻接节点循环结束未提前返回，表示该节点无环，同时标记当前节点为未访问（回溯）
        checked[v] = true;
        visited[v] = false;

        return false;
    }

    /**
     * 判断是否有循环依赖，可以表示为有向图，判断是否有环
     *
     * （节点总数为 V，边总数为 E）
     * Time: O(V+E), Space: O(V+E)
     *
     * @param n
     * @param pairs
     * @return
     */
    public boolean canFinishDFS(int n, int[][] pairs) {

        // 以邻接表表示的图，从 0 到 n - 1 定义一个链表，表示 n 个节点的邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }

        // pair[0] 依赖 pair[1]：即上课程 pair[0] 之前需要先上课程 pair[1]
        // 邻接表表示有向图，第一维指向第二维（pair[1] -> pair[0]）
        for (int[] pair : pairs) {
            graph.get(pair[1]).add(pair[0]);
        }
        boolean[] checked = new boolean[n], visited = new boolean[n];

        // 遍历 n 个节点，如果该节点未检查过且有环，返回 false
        for (int i = 0; i < n; i++) {
            if (!checked[i] && hasCycle(graph, visited, checked, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否存在拓扑排序（有向无环图）：
     * 拓扑排序从入度为 0 的节点开始，将其邻接节点的入度 -1（相当于将从该节点指出的边都删除），
     * 取出该节点，再继续寻找下一个入度为 0 的节点；
     * 最后剩下一个节点，取下后得到拓扑排序
     *
     * Time: O(V+E), Space: O(V+E)
     *
     * @param n
     * @param pairs
     * @return
     */
    public boolean canFinishTopoSortAdjList(int n, int[][] pairs) {
        if (n <= 1 || pairs == null || pairs.length == 0) {
            return true;
        }

        // 生成图，并记录每个节点的入度
        int[] inDegree = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int[] pair : pairs) {
            graph.get(pair[1]).add(pair[0]);
            ++inDegree[pair[0]];
        }

        // 使用一个队列存放入度为 0 的点
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        // 拓扑排序计数（每有一个入度为 0 的节点被处理则 + 1，如最终所有节点都被处理，表示可生成拓扑排序）
        int count = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            ++count;
            for (int i : graph.get(v)) {
                --inDegree[i];
                if (inDegree[i] == 0) {
                    q.add(i);
                }
            }
        }

        return count == n;
    }
}
