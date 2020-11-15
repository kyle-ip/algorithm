package com.ywh.ds.graph;

import com.ywh.ds.tree.UnionFind;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 无向有权图
 *
 * @author ywh
 * @since 15/11/2020
 */
public class UndirectedWeightedGraph implements Graph {

    private int V;

    private int E;

    private TreeMap<Integer, Integer>[] adj;

    /**
     * 7 12
     * 0 1 2
     * 0 3 7
     * 0 5 2
     * 1 2 1
     * 1 3 4
     * 1 4 3
     * 1 5 5
     * 2 4 4
     * 2 5 4
     * 3 4 1
     * 3 6 5
     * 4 6 7
     *
     * @param filename
     */
    public UndirectedWeightedGraph(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
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
                int weight = scanner.nextInt();
                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                if (adj[a].containsKey(b)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }
                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param v
     */
    public void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + "is invalid");
        }
    }

    /**
     * @param v
     * @param w
     * @return
     */
    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
    }

    /**
     * @param v
     * @param w
     */
    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj[v].containsKey(w)) {
            E--;
        }
        adj[v].remove(w);
        adj[w].remove(v);
    }

    /**
     * @return
     */
    @Override
    public int V() {
        return V;
    }

    /**
     * @return
     */
    @Override
    public int E() {
        return E;
    }

    /**
     * @param v
     * @param w
     * @return
     */
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    /**
     * @param v
     * @return
     */
    @Override
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v].keySet();
    }

    /**
     * @param v
     * @return
     */
    @Override
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public int connectedComponentsCount() {
        int cc = 0;
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (visited[i]) {
                continue;
            }
            Stack<Integer> stack = new Stack<>();
            visited[i] = true;
            stack.push(i);
            while (!stack.empty()) {
                int v = stack.pop();
                for (int w : adj(v)) {
                    if (visited[w]) {
                        continue;
                    }
                    // 每遇到一个未访问的邻接顶点，则 left--。
                    visited[w] = true;
                    stack.push(w);
                }
            }
            cc++;
        }
        return cc;
    }

    /**
     * @return
     */
    @Override
    public Object clone() {
        try {
            UndirectedWeightedGraph cloned = (UndirectedWeightedGraph) super.clone();
            cloned.adj = new TreeMap[V];
            for (int v = 0; v < V; v++) {
                cloned.adj[v] = new TreeMap<>();
                for (Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
            .append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                sb.append(String.format("(%d: %d) ", entry.getKey(), entry.getValue()));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 求最小生成树（Kruskal 算法）
     *
     * Time: O(E*log(E))
     *
     * @return
     */
    public Iterable<WeightedEdge> mstKruskal() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        // 从 0 出发遍历图，判断图是否联通。
        stack.push(0);
        int left = V - 1;
        visited[0] = true;
        while (!stack.empty()) {
            int v = stack.pop();
            for (int w : adj(v)) {
                if (visited[w]) {
                    continue;
                }
                // 每遇到一个未访问的邻接顶点，则 left--。
                left--;
                visited[w] = true;
                stack.push(w);
            }
        }
        // 图不联通，返回。
        if (left != 0) {
            return Collections.emptyList();
        }

        // 把所有边添加到 edges（不重复），按权值从小到大排序。
        List<WeightedEdge> edges = new ArrayList<>(E), mst = new ArrayList<>(V - 1);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                if (v < w) {
                    edges.add(new WeightedEdge(v, w, getWeight(v, w)));
                }
            }
        }
        Collections.sort(edges);

        // 使用并查集判断每次取出的边是否与已取出的边形成环，不成环则收集。
        UnionFind uf = new UnionFind(V);
        for (WeightedEdge edge : edges) {
            if (!uf.isConnected(edge.getV(), edge.getW())) {
                mst.add(edge);
                uf.union(edge.getV(), edge.getW());
            }
        }
        return mst;
    }

    /**
     *
     * Time: O(V*E)，即 O((V-1)*(V+E))
     *
     * @return
     */
    public Iterable<WeightedEdge> mstPrim0() {
        if (connectedComponentsCount() > 1) {
            return Collections.emptyList();
        }
        List<WeightedEdge> mst = new ArrayList<>();

        boolean[] visited = new boolean[V];
        visited[0] = true;
        for (int i = 1; i < V; i++) {
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);

            // 每轮循环中对所有的边扫描取最短边，但实际上此处只关心以 v 为起始点的最短边，因此可用最小堆优化。
            for (int v = 0; v < V; v++) {
                if (!visited[v]) {
                    continue;
                }
                for (int w : adj(v)) {
                    if (!visited[w] && getWeight(v, w) < minEdge.getWeight()) {
                        minEdge = new WeightedEdge(v, w, getWeight(v, w));
                    }
                }
            }
            mst.add(minEdge);
            visited[minEdge.getV()] = true;
            visited[minEdge.getW()] = true;
        }
        return mst;
    }

    /**
     * 求最小生成树（Prim 算法）
     *
     * @return
     */
    public Iterable<WeightedEdge> mstPrim() {
        if (connectedComponentsCount() > 1) {
            return Collections.emptyList();
        }

        // 标记节点当前归属的切分（false 为“from”，true 为“to”）。
        boolean[] visited = new boolean[V];
        visited[0] = true;

        // 使用最小堆（堆顶元素必然是最短边），初始化添加顶点 0 的所有邻接边。
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        for (int w : adj(0)) {
            pq.add(new WeightedEdge(0, w, getWeight(0, w)));
        }

        List<WeightedEdge> mst = new ArrayList<>();
        while (!pq.isEmpty()) {
            // 每轮循环取出最短边，如果边的两点已归属于“to”切分，表示该边非横切边，跳过。
            WeightedEdge minEdge = pq.remove();
            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {
                continue;
            }
            // 收集最短横切边，并把该边当前归属于“from”切分的点划归“to”。
            mst.add(minEdge);
            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newV] = true;

            // 遍历新加入的“to”切分的点的邻接顶点，把未访问（即归属于“from”切分）的顶点添加到最小堆。
            for (int w : adj(newV)) {
                if (!visited[w]) {
                    pq.add(new WeightedEdge(newV, w, getWeight(newV, w)));
                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        UndirectedWeightedGraph g = new UndirectedWeightedGraph("C:\\Project\\cs-basic\\algorithm\\java\\src\\main" +
            "\\java\\com\\ywh\\ds\\graph\\g.txt");
        System.out.println(g.mstKruskal());
    }
}
