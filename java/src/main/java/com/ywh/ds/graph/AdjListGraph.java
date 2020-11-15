package com.ywh.ds.graph;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 邻接表
 *
 * space: O(V + E)
 */
public class AdjListGraph {

    private int V;

    private int E;

    private LinkedList<Integer>[] adj;

    /**
     * 建图
     *
     * Time: O(V * E)
     *
     * @param filename
     */
    public AdjListGraph(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
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
    }

    /**
     * @param v
     */
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
     * Time: O(degree(v))
     *
     * @param v
     * @param w
     * @return
     */
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    /**
     * 求相邻节点（相当于缓存了顶点的相邻节点，返回时需要遍历一遍）
     *
     * Time: O(degree(v))
     *
     * @param v
     * @return
     */
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
    public int degree(int v) {
        return adj[v].size();
    }

    /**
     * @return
     */
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
}
