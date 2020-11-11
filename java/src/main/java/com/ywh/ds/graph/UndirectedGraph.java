package com.ywh.ds.graph;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
        StringBuilder sb = new StringBuilder()
            .append(String.format("V = %d, E = %d\n", V, E));
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
    public void dfsIterative() {
        boolean[] visited = new boolean[V];
        ArrayList<Integer> order = new ArrayList<>();
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
        System.out.println(order);
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
                // 此处可统计联通分量。
            }
        }
        System.out.println(order);
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
     *      寻找图中的割点
     *      哈密尔顿路径
     *      拓扑排序
     *      ...
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

    /**
     * 根据联通分量对顶点分组
     *
     * @return
     */
    public Map<Integer, Integer> connectedComponents() {
        // 以顶点为下标，联通分量编号为值的访问数组
        int [] visited = new int[V];
        int cccount = 0;
        Arrays.fill(visited, -1);
        for (int v = 0; v < V; v++) {
            if (visited[v] == -1) {
                ccDfs(v, cccount++, visited);
            }
        }
        Map<Integer, Integer> ret = new HashMap<>(V);
        for (int i = 0; i < visited.length; i++) {
            ret.put(i, visited[i]);
        }
        return ret;
    }

    /**
     *
     * @param v
     * @param ccid
     * @param visited
     */
    private void ccDfs(int v, int ccid, int[] visited) {
        visited[v] = ccid;
        for (int w : adj(v)) {
            if (visited[w] == -1) {
                ccDfs(w, ccid, visited);
            }
        }
    }

    /**
     * 判断两点间是否联通，求单源路径。
     *
     * @param src
     * @param dest
     */
    public List<Integer> singleSourcePath(int src, int dest) {
        boolean[] visited = new boolean[V], found = new boolean[1];
        int[] path = new int[V];
        Arrays.fill(path, -1);
        genPath(src, dest, visited, found, path);
        List<Integer> ret = new ArrayList<>();
        collect(path, src, dest, ret);
        return ret;
    }

    /**
     *
     * @param src
     * @param dest
     * @param visited
     * @param found
     */
    private void genPath(int src, int dest, boolean[] visited, boolean[] found, int[] path) {
        visited[src] = true;
        if (src == dest) {
            found[0] = true;
            return;
        }
        for (int w : adj[src]) {
            if (visited[w]) {
                continue;
            }

            // 求某邻接节点时保存其与上一个节点的对应关系。
            path[w] = src;
            genPath(w, dest, visited, found, path);
            if (found[0]) {
                return;
            }
        }
    }

    /**
     * 递归处理 src -> dest 的路径。
     *
     * @param path
     * @param src
     * @param dest
     */
    private void collect(int[] path, int src, int dest, List<Integer> ret) {
        if (path[dest] != -1 && dest != src) {
            collect(path, src, path[dest], ret);
        }
        ret.add(dest);
    }

    /**
     * 判断图中是否有环
     * 成环的条件：出现已访问节点，且该节点不是上一个节点。
     *
     * @return
     */
    public boolean hasCycle() {
        boolean[] visited = new boolean[V];
        boolean hasCycle = false;
        for (int v = 0; v < V; v++) {
            if (visited[v]) {
                continue;
            }
            if (cycleDetection(v, v, visited)) {
                hasCycle = true;
                break;
            }
        }
        return hasCycle;
    }

    /**
     *
     * @param v
     * @param prev
     * @param visited
     * @return
     */
    private boolean cycleDetection(int v, int prev, boolean[] visited) {
        visited[v] = true;

        // 遍历所有邻接节点 w。
        for (int w : adj(v)) {
            // w 未被访问：递归判断 w，判断成功则返回到上层。
            if (!visited[w]) {
                if (cycleDetection(w, v, visited)) {
                    return true;
                }
            }
            // w 已被访问，且 w 不是 v 的上一个节点。
            else if (w != prev) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断图是否为二分图
     *
     * @return
     */
    private boolean isBipartiteGraph() {
        int[]  visited = new int[V];
        boolean isBipartite = true;
        for (int v = 0; v < V; v++) {
            if (visited[v] == 0) {
                if (!bipartiteDetection(v, 1, visited)) {
                    isBipartite = false;
                    break;
                }
            }
        }
        for (int i : visited) {
            System.out.println(i);
        }
        return isBipartite;
    }

    private boolean bipartiteDetection(int v, int color, int[] visited) {
        // 给当前访问的 v 涂色。
        visited[v] = color;

        // 遍历所有邻接节点 w。
        for (int w : adj(v)) {
            // 如果 w 未被涂色，则递归判断 w，判断出现矛盾直接返回 false。
            if (visited[w] == 0) {
                if (!bipartiteDetection(w, -color, visited)) {
                    return false;
                }
            }
            // 如果 w 已被涂色，且颜色与 v 相同，返回 false。
            else if (visited[w] == visited[v]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        UndirectedGraph g = new UndirectedGraph("t.txt");
        System.out.print(g);
        System.out.println(g.isBipartiteGraph());
    }

}
