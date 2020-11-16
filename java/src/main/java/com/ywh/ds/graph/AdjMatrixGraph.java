package com.ywh.ds.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 邻接矩阵
 *
 * space: O(V^2)
 */
public class AdjMatrixGraph {

    private int V;

    private int E;

    private int[][] adj;

    public AdjMatrixGraph(int[][] adj, int V, int E) {
        this.adj = adj;
        this.V = V;
        this.E = E;
    }


    /**
     * 建图
     *
     * Time: O(E)
     *
     * V E
     * 7 9
     * 0 1      0 -> 1
     * 0 3      0 -> 3
     * 1 2      ...
     * 1 6
     * 2 3
     * 2 5
     * 3 4
     * 4 5
     * 5 6
     *
     * @param filename
     */
    public AdjMatrixGraph(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new int[V][V];
            E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                // 自环边
                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                // 平行边
                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + "is invalid");
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * 两点是否相邻
     *
     * Time: O(1)
     *
     * @param v
     * @param w
     * @return
     */
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    /**
     * 求相邻节点
     *
     * Time: O(V)
     *
     * @param v
     * @return
     */
    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 度数
     *
     * @param v
     * @return
     */
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 深度优先遍历（递归解法）
     */
    public void dfsRecursive() {
        boolean[] visited = new boolean[V];
        ArrayList<Integer> order = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                dfs(v, visited, order);
                // 此处可统计连通分量。
            }
        }
        System.out.println(order);
    }

    /**
     * 深度优先遍历（从某点开始访问整个连通分量）
     * 可用于求解以下问题：
     *      求图的连通分量
     *      求两点间时否可达
     *      求两点间的一条路径
     *      判断图中是否有环
     * 应用：
     *      二分图检测
     *      寻找图中的割点
     *      哈密尔顿路径
     *      拓扑排序
     *
     * Time: O(V + E)
     *
     * @param v
     */
    private void dfs(int v, boolean[] visited, ArrayList<Integer> order) {
        visited[v] = true;

        // 先序
        order.add(v);

        // 依次取出该点的所有未被访问过的邻接节点，递归遍历。
        for (int w : adj(v)) {
            if (!visited[w]) {
                dfs(w, visited, order);
            }
        }
        // 后序
        // order.add(v);
    }
}
