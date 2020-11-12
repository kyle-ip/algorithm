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


    // 相关问题：
    // 树遍历：144、94、145、102、589、590、429
    // 图遍历：185、886、547、695
    // Flood Fill 算法：200、1020、130、733、1034、529、827

    // ========== DFS ==========

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
                for (int w : adj[cur]) {
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
    public Map<Integer, Integer> connectedComponentsDfs() {
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
     * 判断两点间是否联通，并求单源路径。
     *
     * @param src
     * @param dest
     */
    public Iterable<Integer> singleSourcePathDfs(int src, int dest) {
        boolean[] visited = new boolean[V], found = new boolean[1];
        int[] prev = new int[V];
        Arrays.fill(prev, -1);
        genPathDfs(src, dest, visited, found, prev);
        List<Integer> ret = new ArrayList<>();
        collect(prev, src, dest, ret);
        return ret;
    }

    /**
     *
     * @param src
     * @param dest
     * @param visited
     * @param found
     */
    private void genPathDfs(int src, int dest, boolean[] visited, boolean[] found, int[] prev) {
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
            prev[w] = src;
            genPathDfs(w, dest, visited, found, prev);
            if (found[0]) {
                return;
            }
        }
    }

    /**
     * 递归处理 src -> dest 的路径。
     *
     * @param prev
     * @param src
     * @param dest
     */
    private void collect(int[] prev, int src, int dest, List<Integer> ret) {
        if (prev[dest] != -1 && dest != src) {
            collect(prev, src, prev[dest], ret);
        }
        ret.add(dest);
    }

    /**
     * 判断图中是否有环
     * 成环的条件：出现已访问节点，且该节点不是上一个节点。
     *
     * @return
     */
    public boolean hasCycleDfs() {
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
     * 判断是否为二分图
     *
     * @return
     */
    private boolean isBipartiteGraphDfs() {
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

    /**
     *
     * @param v
     * @param color
     * @param visited
     * @return
     */
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

    // ========== BFS ==========

    /**
     * 广度优先遍历
     */
    public Iterable<Integer> bfs() {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();

        // 遍历所有节点，添加到队列中。
        for (int i = 0; i < V; i++) {
            queue.add(i);
            // 队列为空，表示一个联通分量遍历完成。
            while (!queue.isEmpty()) {
                // 从队列中取出一个节点 v，添加到结果数组。
                int v = queue.poll();
                order.add(v);
                // 遍历所有邻接节点 w，如果 w 未被访问则添加到队列中，并标记为已访问。
                for (int w : adj(v)) {
                    if (!visited[w]) {
                        queue.add(w);
                        visited[w] = true;
                    }
                }
            }
        }
        return order;
    }

    /**
     * 判断两点间是否联通，并求单源路径。
     *
     * @param src
     * @param dest
     */
    public Iterable<Integer> singleSourcePathBfs(int src, int dest) {
        if (src > V || dest > V) {
            return Collections.emptyList();
        }
        if (src == dest) {
            return Collections.singletonList(src);
        }
        boolean[] visited = new boolean[V];
        visited[src] = true;
        ArrayList<Integer> ret = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        int[] prev = new int[V];
        Arrays.fill(prev, -1);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : adj(v)) {
                if (visited[w]) {
                    continue;
                }
                prev[w] = v;
                if (w == dest) {
                    collect(prev, src, dest, ret);
                    return ret;
                }
                visited[w] = true;
                queue.add(w);
            }
        }
        return ret;
    }

    /**
     * 判断两点间是否联通，并求单源路径。
     *
     * @param src
     * @param dest
     */
    public Iterable<Integer> singleSourcePathBfs2(int src, int dest) {
        if (src > V || dest > V) {
            return Collections.emptyList();
        }
        boolean[] visited = new boolean[V];
        visited[src] = true;
        ArrayList<Integer> ret = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        int[] prev = new int[V];
        Arrays.fill(prev, -1);
        prev[src] = src;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    prev[w] = v;
                }
            }
        }
        if (!visited[dest]) {
            return ret;
        }
        int cur = dest;
        while (cur != src) {
            ret.add(cur);
            cur = prev[cur];
        }
        ret.add(src);
        Collections.reverse(ret);
        return ret;
    }

    /**
     * 所有点对路径
     *
     * @param dest
     * @return
     */
    private Map<Integer, Iterable<Integer>> allPairsPath(int dest) {
        // 逐个点求到达 dest 的单源路径。
        Map<Integer, Iterable<Integer>> paths = new HashMap<>();
        for (int v = 0; v < V; v++) {
            paths.put(v, singleSourcePathBfs(v, dest));
        }
        return paths;
    }

    /**
     * 判断两点间是否可达
     *
     * @param src
     * @param dest
     * @return
     */
    private boolean isConnected(int src, int dest) {
        List<Integer> ret = (List<Integer>)singleSourcePathBfs(src, dest);
        return ret.isEmpty();
    }

    /**
     * 根据联通分量对顶点分组
     *
     * @return
     */
    public Map<Integer, Integer> connectedComponentsBfs() {
        int[] visited = new int[V];
        Arrays.fill(visited, -1);
        int cccount = 0;
        for (int i = 0; i < V; i++) {
            if (visited[i] != -1) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            visited[i] = cccount;
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int w : adj[v]) {
                    if (visited[w] == -1) {
                        queue.add(w);
                        visited[w] = cccount;
                    }
                }
            }
            cccount++;
        }
        Map<Integer, Integer> ret = new HashMap<>(V);
        for (int v = 0; v < V; v++) {
            ret.put(v, visited[v]);
        }
        return ret;
    }

    /**
     * 判断图中是否有环
     *
     * @return
     */
    public boolean hasCycleBfs() {
        boolean[] visited = new boolean[V];
        int[] prev = new int[V];
        Arrays.fill(prev, -1);

        for (int i = 0; i < V; i++) {
            if (visited[i]) {
                continue;
            }
            // 从顶点 i 开始，判断图是否有环。
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            visited[i] = true;
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int w : adj(v)) {
                    if (!visited[w]) {
                        queue.add(w);
                        visited[w] = true;
                        prev[w] = v;
                    } else if (prev[v] != w) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断图是否为二分图
     *
     * @return
     */
    public boolean isBipartiteGraphBfs() {
        int[] visited = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] != 0) {
                continue;
            }
            // 从 i 节点开始遍历，颜色设置为 1。
            visited[i] = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int v = queue.poll();
                // v 的邻接节点 w
                for (int w : adj[v]) {
                    // w 未访问：取反色，入队。
                    if (visited[w] == 0) {
                        queue.add(w);
                        visited[w] = -visited[v];
                    }
                    // w 已访问，且与 v 颜色相同，非二分图。
                    else if (visited[w] == visited[v]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 最短路径（只适用于无向图）
     *
     * @param src
     * @param dest
     * @return
     */
    public Iterable<Integer> shortestPath(int src, int dest) {

        // 遍历过程记录当前节点的上一个节点，以及从 src 到达当前节点的距离。
        int[] prev = new int[V], dis = new int[V];
        Arrays.fill(prev, -1);
        Arrays.fill(dis, -1);

        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited[src] = true;
        prev[src] = src;

        // dis[i] 表示从 src 到达 i 的最短距离。
        dis[src] = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : adj(v)) {

                // 访问过的邻接点会跳过（不计算距离），计算距离时该点都是首次被访问的。
                // 对于广度优先遍历而言，首次被访问即表示经由最短路径访问。
                if (visited[w]) {
                    continue;
                }
                queue.add(w);
                visited[w] = true;
                prev[w] = v;
                dis[w] = dis[v] + 1;
            }
        }

        LinkedList<Integer> ret = new LinkedList<>();
        if (!visited[dest]) {
            return ret;
        }
        System.out.println(dis[dest]);
        int cur = dest;
        while (cur != src) {
            ret.addFirst(cur);
            cur = prev[cur];
        }
        ret.addFirst(src);
        return ret;
    }

    public static void main(String[] args) {
        UndirectedGraph g = new UndirectedGraph("C:\\Project\\cs-basic\\algorithm\\java\\src\\main\\java\\com\\ywh" +
            "\\ds\\graph\\g.txt");
//        System.out.print(g);
//        System.out.println(g.shortestPath(0, 6));
        g.dfsRecursive();
        g.dfsIterative();
    }

}
