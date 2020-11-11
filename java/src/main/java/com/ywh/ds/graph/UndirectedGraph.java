package com.ywh.ds.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

/**
 * 图的邻接表实现（基于红黑树）
 *
 * @author ywh
 * @since 10/11/2020
 */
public class UndirectedGraph implements Graph {
    private int V;

    private int E;

    private TreeSet<Integer>[] adj;

    /**
     * 遍历标记
     */
    private boolean[] visited;

    /**
     * 遍历顺序
     */
    private ArrayList<Integer> order = new ArrayList<>();

    /**
     * 建图
     *
     * @param filename
     */
    public UndirectedGraph(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }

            E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }

                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        visited = new boolean[V];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + "is invalid");
        }
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    /**
     * 两点是否相邻
     *
     * @param v
     * @param w
     * @return
     */
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    /**
     * 求相邻节点
     *
     * @param v
     * @return
     */
    @Override
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 度数
     *
     * @param v
     * @return
     */
    @Override
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 深度优先遍历（迭代解法）
     */
    private void dfsIterative() {
        for (int v = 0; v < V; v++) {
            if (visited[v]) {
                continue;
            }
            Stack<Integer> stack = new Stack<>();
            stack.push(v);
            visited[v] = true;
            while (!stack.empty()) {
                int cur = stack.pop();
                order.add(cur);
                for (int w : adj[v]) {
                    if (!visited[w]) {
                        stack.push(w);
                        visited[w] = true;
                    }
                }
            }
        }
    }

    /**
     * 深度优先遍历（递归解法）
     */
    private void dfsRecursive() {
        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    /**
     * 深度优先遍历（从某点开始访问整个联通分量）
     * 可用于求解以下问题：
     *      求图的联通分量
     *      求两点间时否可达
     *      求两点间的一条路径
     *      判断图中是否有环
     * 应用：
     *      二分图检测
     *
     * Time: O(V + E)
     *
     * @param v
     */
    private void dfs(int v) {
        visited[v] = true;

        // 先序
        order.add(v);

        // 依次取出该点的所有未被访问过的邻接节点，递归遍历。
        for (int w : adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        // 后序
        // order.add(v);
    }

    public static void main(String[] args) {
        UndirectedGraph g = new UndirectedGraph("g.txt");
        System.out.print(g);
        g.dfsRecursive();
        System.out.println(g.order);
    }

}
