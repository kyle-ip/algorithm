package com.ywh.ds.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 *
 * @author ywh
 * @since 2020/11/4/004z
 */
public class UG {

    /**
     * 顶点个数
     */
    private int v;

    /**
     * 邻接表：链表数组
     *      [
     *          [ ] -> [ ] -> [ ] ... [ ],
     *          [ ] -> [ ],
     *          [ ],
     *      ]
     */
    private LinkedList<Integer>[] adj;

    /**
     * 无向图初始化：共 v 个节点（默认用无符号整型表示）。
     * @param v
     */
    public UG(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 添加边到无向图：src -> dest，src -> dest。
     *
     * @param src
     * @param dest
     */
    public void addEdge(int src, int dest) {
        adj[src].add(dest);
        adj[dest].add(src);
    }

    // 不论是何种搜索，在遍历过程中都需要记录：已访问节点（避免重复），访问路径（上一个节点）。

    /**
     * 广度优先搜索：从 src 开始到 dest 结束，输出一条（最短）路径。
     * 使用队列实现逐层访问，每轮循环从队列取出一个节点，依次访问其所有邻接节点：如果已到达终点，则输出路径，否则添加到队尾。
     * 中间需要记录：已访问节点（避免重复访问），节点与上一个节点的映射关系（保存路径）。
     * 最坏情况下遍历整个图才能找到路径（每个顶点都要进出一遍队列、每条边都要被访问一次）。
     * 其中辅助存储的 visited、queue、prev 大小都不会超过顶点个数。
     *
     * Time: O(V+E)， Space: O(V)
     *
     * @param src
     * @param dest
     */
    public void bfs(int src, int dest) {
        if (src == dest) {
            return;
        }
        // 已访问的节点。
        boolean[] visited = new boolean[v];
        visited[src] = true;

        // 队列，保存已被访问但邻接节点未被访问的节点。
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

        // 访问当前节点的上一个节点（记录路径）。
        int[] prev = new int[v];
        Arrays.fill(prev, -1);
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 遍历当前节点的邻接节点。
            for (int i = 0; i < adj[cur].size(); i++) {
                int neighbour = adj[cur].get(i);

                // 如果当前邻居已经被访问过，跳过。
                if (visited[neighbour]) {
                    continue;
                }

                // 记录从 cur 到达 neighbour，如果 neighbour 即为 dest，即输出完整路径。
                prev[neighbour] = cur;
                if (neighbour == dest) {
                    print(prev, src, dest);
                    return;
                }
                // 否则标记 neighbour 已被访问，添加到队尾。
                visited[neighbour] = true;
                queue.add(neighbour);
            }
        }

    }

    /**
     * 深度优先搜索：从 src 开始递归访问当前节点的邻接节点，直到找到 dest。
     *
     * 每条边最多被访问两次（遍历和回退）。
     * 消耗的内存主要是 visited、prev 数组和递归调用栈，其中前两者与顶点个数成正比、递归调用栈最大深度不会超过顶点个数。
     *
     * Time: O(E), Space: O(V)
     *
     * @param src
     * @param dest
     */
    public void dfs(int src, int dest) {
        boolean[] found = new boolean[]{false}, visited = new boolean[v];
        int[] prev = new int[v];
        Arrays.fill(prev, -1);
        recurDfs(src, dest, visited, prev, found);
        print(prev, src, dest);
    }

    /**
     *
     * @param cur
     * @param dest
     * @param visited
     * @param prev
     * @param found
     */
    private void recurDfs(int cur, int dest, boolean[] visited, int[] prev, boolean[] found) {
        // if (found[0]) return;

        // 标记当前就节点已被访问。如果已到达终点，则标记搜索结束，返回。
        visited[cur] = true;
        if (cur == dest) {
            found[0] = true;
            return;
        }

        // 遍历当前节点的邻接节点，每轮循环取出一个判断是否已被访问，如未访问则递归访问该邻接节点。
        for (int i = 0; i < adj[cur].size(); i++) {
            int neighbour = adj[cur].get(i);
            if (visited[neighbour]) {
                continue;
            }
            prev[neighbour] = cur;
            recurDfs(neighbour, dest, visited, prev, found);
            // 如果已经找到路径，提前返回。
            if (found[0]) {
                return;
            }
        }
    }

    /**
     * 递归打印 src -> dest 的路径。
     *
     * @param prev
     * @param src
     * @param dest
     */
    private void print(int[] prev, int src, int dest) {
        if (prev[dest] != -1 && dest != src) {
            print(prev, src, prev[dest]);
        }
        System.out.print(dest + " ");
    }
}
