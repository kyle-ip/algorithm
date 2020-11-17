package com.ywh.ds.graph;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 图的邻接表实现（基于红黑树）
 *
 * @author ywh
 * @since 10/11/2020
 */
public class UnweightedGraph implements Graph, Cloneable {

    private int V;

    private int E;

    /**
     * 邻接表
     * [
     *      0: (1, 2, 3, 4),
     *      1: (2, 3, 4)
     * ]
     */
    private TreeSet<Integer>[] adj;

    /**
     * 是否有向
     */
    private final boolean directed;

    /**
     * 入度，出度
     */
    private int[] indegrees, outdegrees;

    @Override
    public Object clone() {
        try {
            UnweightedGraph cloned = (UnweightedGraph) super.clone();
            cloned.adj = new TreeSet[V];
            for (int v = 0; v < V; v++) {
                cloned.adj[v] = new TreeSet<>();
                for (int w : adj[v]) {
                    cloned.adj[v].add(w);
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UnweightedGraph(String filename, boolean directed) {
        this.directed = directed;
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
            indegrees = new int[V];
            outdegrees = new int[V];
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
                // a -> b
                adj[a].add(b);
                outdegrees[a]++;
                indegrees[b]++;
                if (!directed) {
                    // a <-> b
                    adj[b].add(a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建图
     *
     * @param fileName
     */
    public UnweightedGraph(String fileName) {
        this(fileName, false);
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
     * 删除边
     *
     * @param v
     * @param w
     */
    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj[v].contains(w)) {
            E--;
            if (directed) {
                indegrees[w]--;
                outdegrees[v]--;
            }
        }
        adj[v].remove(w);
        if (!directed) {
            adj[w].remove(v);
        }
    }

    /**
     * 求相邻顶点
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
     *
     * @param v
     * @return
     */
    @Override
    public int degree(int v) {
        if (!directed) {
            throw new RuntimeException("degree only works in undirected graph.");
        }
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * @param v
     * @return
     */
    @Override
    public int indegree(int v) {
        if (!directed) {
            throw new RuntimeException("indegree only works in directed graph.");
        }
        validateVertex(v);
        return indegrees[v];
    }

    /**
     * @param v
     * @return
     */
    @Override
    public int outdegree(int v) {
        if (!directed) {
            throw new RuntimeException("outdegree only works in directed graph.");
        }
        validateVertex(v);
        return outdegrees[v];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d, directed = %b\n", V, E, directed));
        IntStream.range(0, V).forEach(v -> {
            sb.append(String.format("%d : ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');
        });
        return sb.toString();
    }

    @Override
    public int connectedComponentsCount() {
        boolean[] visited = new boolean[V];
        int count = 0;
        ArrayList<Integer> order = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                dfs(v, visited, order);
                // 此处可统计连通分量。
                count++;
            }
        }
        return count;
    }

    // 相关问题：
    // 树遍历：144、94、145、102、589、590、429
    // 图遍历：185、886、547、695
    // Flood Fill 算法：200、1020、130、733、1034、529、827
    // 图建模：1091、752（状态转换）、773、五桶+三桶凑四升水、狼羊菜问题
    // 哈密尔顿路径：980（状态压缩、记忆化搜索）

    // ========== DFS ==========

    /**
     * 深度优先遍历（迭代解法）
     * 
     * Time: O(V^2)
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
                // 此处可统计连通分量。
            }
        }
        System.out.println(order);
    }

    /**
     * 深度优先遍历（从某点开始访问整个连通分量）
     * 可用于求解以下问题：
     * 1. 求图的连通分量
     * 2. 求两点间时否可达
     * 3. 求两点间的一条路径
     * 4. 判断图中是否有环
     * 应用：
     * 1. 二分图检测
     * 2. 寻找图中的割点
     * 3. 哈密尔顿路径
     * 4. 拓扑排序
     * ...
     * 
     * Time: O(V + E)
     *
     * @param v
     */
    private void dfs(int v, boolean[] visited, ArrayList<Integer> order) {
        visited[v] = true;

        // 先序
        order.add(v);

        // 依次取出该点的所有未被访问过的邻接顶点，递归遍历。
        for (int w : adj(v)) {
            if (!visited[w]) {
                dfs(w, visited, order);
            }
        }
        // 后序：即遍历完顶点的邻接顶点时才遍历其自身。
        // order.add(v);
    }

    /**
     * 根据连通分量对顶点分组
     *
     * @return
     */
    public Map<Integer, Integer> connectedComponentsDfs() {
        // 以顶点为下标，连通分量编号为值的访问数组
        int[] visited = new int[V];
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
     * 判断两点间是否连通，并求单源路径。
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

            // 求某邻接顶点时保存其与上一个顶点的对应关系。
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
     * 成环的条件：出现已访问顶点，且该顶点不是上一个顶点。
     *
     * @return
     */
    public boolean hasCycleDfs() {
        boolean[] visited = new boolean[V];
        boolean hasCycle = false;

        // 无向图。
        if (!this.directed) {
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

        // 有向图。
        boolean[] onPath = new boolean[V];

        for (int v = 0; v < V; v++) {
            if (visited[v]) {
                continue;
            }
            if (cycleDetection(v, v, visited, onPath)) {
                hasCycle = true;
                break;
            }
        }
        return hasCycle;
    }

    /**
     * @param v
     * @param prev
     * @param visited
     * @return
     */
    private boolean cycleDetection(int v, int prev, boolean[] visited) {
        visited[v] = true;

        // 遍历所有邻接顶点 w。
        for (int w : adj(v)) {
            // w 未被访问：递归判断 w，判断成功则返回到上层。
            if (!visited[w]) {
                if (cycleDetection(w, v, visited)) {
                    return true;
                }
            }
            // w 已被访问，且 w 不是 v 的上一个顶点。
            else if (w != prev) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param v
     * @param prev
     * @param visited
     * @param onPath
     * @return
     */
    private boolean cycleDetection(int v, int prev, boolean[] visited, boolean[] onPath) {
        visited[v] = true;
        onPath[v] = true;
        for (int w : adj(v)) {
            if (!visited[w]) {
                if (cycleDetection(w, v, visited, onPath)) {
                    return true;
                }
            }
            // 找到已访问节点，且该点在但当前路径上（而不局限于对父节点的判断）。
            else if (onPath[w]) {
                return true;
            }
        }
        // 退递归时将该顶点移出 onPath，表示当前路径上无环，需要判断其他路径。
        //  +---> [0]
        //  |
        // [1] -> [2] -> [3]
        //  |      ↑            判断过 [1] -> [0] 和 [1] -> [2] -> [3] 都没有发现环时，需要把 [1] 从 onPath 移除，
        //  +---> [4]           避免对检测 [1] -> [4] -> [2] -> [3] 时造成干扰。
        onPath[v] = false;
        return false;
    }


    /**
     * 判断是否为二分图
     *
     * @return
     */
    private boolean isBipartiteGraphDfs() {
        int[] visited = new int[V];
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
     * @param v
     * @param color
     * @param visited
     * @return
     */
    private boolean bipartiteDetection(int v, int color, int[] visited) {
        // 给当前访问的 v 涂色。
        visited[v] = color;

        // 遍历所有邻接顶点 w。
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
     * 
     * Time: O(V+E)
     */
    public void bfs() {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();

        // 遍历所有顶点，添加到队列中。
        for (int i = 0; i < V; i++) {
            if (visited[i]) {
                continue;
            }
            queue.add(i);
            // 队列为空，表示一个连通分量遍历完成。
            while (!queue.isEmpty()) {
                // 从队列中取出一个顶点 v，添加到结果数组。
                int v = queue.poll();
                order.add(v);
                // 遍历所有邻接顶点 w，如果 w 未被访问则添加到队列中，并标记为已访问。
                for (int w : adj(v)) {
                    if (!visited[w]) {
                        queue.add(w);
                        visited[w] = true;
                    }
                }
            }
        }
        System.out.println(order);
    }

    /**
     * 判断两点间是否连通，并求单源路径。
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
     * 判断两点间是否连通，并求单源路径。
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
        List<Integer> ret = (List<Integer>) singleSourcePathBfs(src, dest);
        return ret.isEmpty();
    }

    /**
     * 根据连通分量对顶点分组
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
     * 判断图中是否有环（仅支持无向图）
     *
     * @return
     */
    public boolean hasCycleBfs() {
        if (this.directed) {
            throw new IllegalArgumentException("directed cycle detection only works in directed graph.");
        }
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
            // 从 i 顶点开始遍历，颜色设置为 1。
            visited[i] = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int v = queue.poll();
                // v 的邻接顶点 w
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

        // 遍历过程记录当前顶点的上一个顶点，以及从 src 到达当前顶点的距离。
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

    // ========== 桥与割点 ==========

    /**
     * 寻找桥和割点
     */
    public void bridges() {
        boolean[] visited = new boolean[V];
        List<UnweightedEdge> bridges = new ArrayList<>();
        HashSet<Integer> articulationPoints = new HashSet<>();

        // ord[v] 表示顶点 v 在 DFS 中的访问顺序，low[v] 表示 DFS 过程中，顶点 v 能到达的最小 ord 值。
        int[] ord = new int[V], low = new int[V];

        // cnt 用于记录顶点被访问的序号（DFS 遍历树的顺序）。
        int[] cnt = {0};
        for (int v = 0; v < V; v++) {
            if (visited[v]) {
                continue;
            }
            bridgesDfs(v, v, visited, ord, low, cnt, bridges, articulationPoints);
        }
        System.out.println("bridges in graph: " + bridges);
        System.out.println("articulation points in graph: " + articulationPoints);
    }

    /**
     * @param v
     * @param parent
     * @param visited
     * @param ord
     * @param low
     * @param cnt
     * @param bridges
     * @return
     */
    private void bridgesDfs(int v, int parent, boolean[] visited, int[] ord, int[] low, int[] cnt,
                            List<UnweightedEdge> bridges, HashSet<Integer> articulationPoints) {
        visited[v] = true;
        ord[v] = cnt[0];

        // low 初始化为最大值 ord，表示经由 v 能到达的顶点序号最小值为自身序号（表示从根顶点遍历到达当前顶点的顺序）。
        low[v] = ord[v];
        cnt[0]++;

        // 记录孩子顶点，如果 w 未被访问，即遍历过程中 v 找到新的孩子顶点，此时 child++。
        int child = 0;
        for (int w : adj(v)) {
            // 顶点未被访问。
            if (!visited[w]) {
                bridgesDfs(w, v, visited, ord, low, cnt, bridges, articulationPoints);

                // 如果 w 经由回向边可指向祖先顶点，则 v 也应该满足条件，故更新 low[v]。
                low[v] = Math.min(low[v], low[w]);

                // low[w] > ord[v]，表示 w 能回到的顶点序号（最小值）也比 v 的序号大，不能经由回向边（非遍历树边）指向祖先顶点。
                if (low[w] > ord[v]) {
                    bridges.add(new UnweightedEdge(v, w));
                }
                // v 不是根顶点（其父顶点不是自身）且 low[w] >= ord[v]，则为割点。
                if (v != parent && low[w] >= ord[v]) {
                    articulationPoints.add(v);
                }
                // v 是根顶点，且遍历树的孩子数量大于 1，则为割点。
                if (v == parent && ++child > 1) {
                    articulationPoints.add(v);
                }
            }
            // 顶点已被访问，且不为根顶点，更新 low[v] 即可。
            else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    // ========== 哈密尔顿、欧拉回路与路径 ==========

    /**
     * 判断是否存在哈密尔顿回路
     *
     * @return
     */
    private boolean hasHamiltonLoop() {
        LinkedList<Integer> ret = new LinkedList<>();
        boolean[] visited = new boolean[V];
        int[] end = {-1};
        // 从 0 开始 DFS。
        hasHamiltonLoopDfs(0, visited, end, V);
        // 没有找到哈密尔顿回路。
        return end[0] != -1;
    }

    /**
     * @param v
     * @param visited
     * @param end
     * @param left
     * @return
     */
    private boolean hasHamiltonLoopDfs(int v, boolean[] visited, int[] end, int left) {
        visited[v] = true;
        // 如果算上点 v 后剩余未访问点为 0，且 v 的邻接顶点中有 0，即已找到哈密尔顿回路，设 v 为终点并返回 true。
        if (--left == 0 && adj[v].contains(0)) {
            end[0] = v;
            return true;
        }
        for (int w : adj(v)) {
            // w 未访问过，且从 w 出发搜索到哈密尔顿回路，返回 true。
            if (!visited[w] && hasHamiltonLoopDfs(w, visited, end, left)) {
                return true;
            }
        }
        // 从 v 出发的所有邻接点搜索完毕，没有找到路径，回溯（重置 visited[v]）并返回 false。
        visited[v] = false;
        return false;
    }

    /**
     * 求哈密尔顿回路
     *
     * @return
     */
    private Iterable<Integer> hamiltonLoop() {
        LinkedList<Integer> ret = new LinkedList<>();
        boolean[] visited = new boolean[V];
        int[] prev = new int[V], end = {-1};
        // 从 0 开始 DFS。
        hamiltonLoopDfs(0, 0, visited, prev, end, V);
        // 没有找到哈密尔顿回路，返回空列表。
        if (end[0] == -1) {
            return ret;
        }
        int cur = end[0];
        while (cur != 0) {
            ret.addFirst(cur);
            cur = prev[cur];
        }
        ret.addFirst(0);
        return ret;
    }

    /**
     * 遍历一个顶点的邻接顶点 w：
     * 如果 w 未被访问，则递归调用 DFS，结果返回 true 表示从 w 出发找到哈密尔顿回路，即 v 也找到哈密尔顿回路，直接返回。
     * 如果 w 已被访问，且 w 不为 0，跳过。
     * 如果 w 未被访问，且 w 为 0，则检查是否所有顶点都已被访问，是则设置终点为 v，返回 true（递归函数实际出口）。
     * 如果遍历完所有的 w 仍未返回，表示从 v 出发无法找到哈密尔顿回路，（重置 v 的已访问状态）回溯并返回 false。
     *
     * @param v
     * @param parent
     * @param visited
     * @param prev
     * @param end
     * @param left
     * @return
     */
    private boolean hamiltonLoopDfs(int v, int parent, boolean[] visited, int[] prev, int[] end, int left) {
        visited[v] = true;
        prev[v] = parent;
        // 如果算上点 v 后剩余未访问点为 0，且 v 的邻接顶点中有 0，即已找到哈密尔顿回路，设 v 为终点并返回 true。
        if (--left == 0 && adj[v].contains(0)) {
            end[0] = v;
            return true;
        }
        for (int w : adj(v)) {
            // w 未访问过，且从 w 出发搜索到哈密尔顿回路，返回 true。
            if (!visited[w] && hamiltonLoopDfs(w, v, visited, prev, end, left)) {
                return true;
            }
        }
        // 从 v 出发的所有邻接点搜索完毕，没有找到路径，回溯（重置 visited[v]）并返回 false。
        visited[v] = false;
        return false;
    }

    /**
     * 求哈密尔顿路径（状态压缩）
     *
     * @param src
     * @return
     */
    private Iterable<Integer> hamiltonPath(int src) {
        LinkedList<Integer> ret = new LinkedList<>();
        int[] prev = new int[V], end = {-1};

        // visited 布尔数组压缩为二进制数（初始化为 0），比如 [true, false, true, false] 表示为 0b1010，再转换为十进制即 5。
        // 判断第 n 位是否为 1：    visited & (1 << n)
        // 将第 n 位设为 1：        visited += (1 << n)
        // 将第 n 位设为 0：        visited -= (1 << n)
        hamiltonPathDfs(src, src, 0, prev, end, V);
        // 没有找到哈密尔顿回路，返回空列表。
        if (end[0] == -1) {
            return ret;
        }
        int cur = end[0];
        while (cur != 0) {
            ret.addFirst(cur);
            cur = prev[cur];
        }
        ret.addFirst(0);
        return ret;
    }

    /**
     * @param v
     * @param parent
     * @param visited
     * @param prev
     * @param end
     * @param left
     * @return
     */
    private boolean hamiltonPathDfs(int v, int parent, int visited, int[] prev, int[] end, int left) {
        // 把第 v 位设位已访问（1）。
        visited += (1 << v);
        prev[v] = parent;
        if (--left == 0) {
            end[0] = v;
            return true;
        }
        for (int w : adj(v)) {
            // w 未被访问（第 w 位为 0），且找到从 w 出发的哈密尔顿路径。
            if ((visited & (1 << w)) == 0 && hamiltonPathDfs(w, v, visited, prev, end, left)) {
                return true;
            }
        }
        // visited -= (1 << v);
        return false;
    }

    /**
     * 判断有向图是否存在欧拉回路
     *
     * @return
     */
    private boolean hasEulerLoopDirected() {
        // TODO 判断连通性
        for (int v = 0; v < V; v++) {
            if (indegrees[v] != outdegrees[v]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求有向图的欧拉回路
     *
     * @return
     */
    private Iterable<Integer> eulerLoopDirected() {

        // 有向图应使用链表记录节点，避免最终要对结果逆序。
        LinkedList<Integer> ret = new LinkedList<>();
        if (!hasEulerLoopDirected()) {
            return ret;
        }

        // 在拷贝图上操作。
        UnweightedGraph graph = (UnweightedGraph) this.clone();
        Stack<Integer> stack = new Stack<>();
        int v = 0;
        stack.push(v);
        while (!stack.isEmpty()) {
            // 当前节点 v 出度 > 0：表示有路可走。
            // v 入栈，并取其邻接顶点 w、去除两者之间的边，继续以 w 迭代遍历。
            if (graph.outdegree(v) != 0) {
                stack.push(v);
                int w = graph.adj(v).iterator().next();
                graph.removeEdge(v, w);
                v = w;
            }
            // 当前顶点度数为 0，表示已经把相连的边都去除（成环）。
            // 此节点即为欧拉回路上的点，添加到结果列表。
            else {
                ret.addFirst(v);
                v = stack.pop();
            }
        }
        return ret;
    }

    /**
     * 判断无向图是否存在欧拉回路
     *
     * @return
     */
    private boolean hasEulerLoopUndirected() {
        boolean[] visited = new boolean[V];

        // 判断连通分量个数是否为 1（从 0 出发 DFS，结束后剩余未访问节点 > 0，表示存在不连通的点，返回 false）。
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int left = V - 1;
        visited[0] = true;
        while (!stack.empty()) {
            int v = stack.pop();
            for (int w : adj(v)) {
                if (visited[w]) {
                    continue;
                }
                left--;
                visited[w] = true;
                stack.push(w);
            }
        }
        if (left != 0) {
            return false;
        }
        for (int v = 0; v < V; v++) {
            // 存在度数为奇数的顶点，返回空。
            if (adj[v].size() % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求无向图的欧拉回路（Hierholzer），另外还有：
     * 回溯法
     * Fleury 算法
     * 
     * Time: O(E)
     *
     * @return
     */
    private Iterable<Integer> eulerLoopUndirected() {

        // [A]     [E]
        //  | \   / |
        //  |  [B]  |
        //  | /   \ |
        // [C]     [D]

        // 双栈解法：每条边走一次、回退一次，回退的路径即为欧拉回路。
        // 1. 从某一点出发沿路一直走，把边去除，并把顶点添加到 curPath 栈：A、B、C、A。
        // 2. 直到出现环即回退：不断把环中的节点从 curPath 转移到 loop 栈：A、C。
        // 3. 在回退过程中，发现当前环与外部环相连的公共点（B），开始从路径开始重复第一步，curPath 栈：A、B、D、E、B。
        // 4. 新路径也处理完，发现新环，且所有的边都被去除了（从 B 不能走到任何顶点），开始重复第二步。
        // 5. 最终 curPath 栈的元素被全部转移到 loop 栈：A、C、B、E、D、B、A，此时 loop 栈存储的即为欧拉回路。

        // 单栈解法：只保留 curPath 栈，结果直接存在 ret。

        List<Integer> ret = new ArrayList<>();
        if (!hasEulerLoopUndirected()) {
            return ret;
        }

        // 在拷贝图上操作。
        UnweightedGraph graph = (UnweightedGraph) this.clone();
        Stack<Integer> stack = new Stack<>();
        int v = 0;
        stack.push(v);
        while (!stack.isEmpty()) {
            // 当前节点 v 度数 > 0：表示有路可走。
            // v 入栈，并取其邻接顶点 w、去除两者之间的边，继续以 w 迭代遍历。
            if (graph.degree(v) != 0) {
                stack.push(v);
                int w = graph.adj(v).iterator().next();
                graph.removeEdge(v, w);
                v = w;
            }
            // 当前顶点度数为 0，表示已经把相连的边都去除（成环）。
            // 此节点即为欧拉回路上的点，添加到结果列表。
            else {
                ret.add(v);
                v = stack.pop();
            }
        }
        return ret;
    }

    /**
     * 求欧拉路径
     *
     * @param v
     * @return
     */
    private Iterable<Integer> eulerPath(int v) {
        List<Integer> ret = new ArrayList<>();
        // 在拷贝图上操作。
        UnweightedGraph graph = (UnweightedGraph) this.clone();
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            // 当前节点 v 度数 > 0：表示有路可走。
            // v 入栈，并取其邻接顶点 w、去除两者之间的边，继续以 w 迭代遍历。
            if (graph.degree(v) != 0) {
                stack.push(v);
                int w = graph.adj(v).iterator().next();
                graph.removeEdge(v, w);
                v = w;
                // 所有边都已去除，返回欧拉路径。
                if (graph.E() == 0) {
                    ret.add(v);
                    return ret;
                }
            } else {
                ret.add(v);
                v = stack.pop();
            }
        }
        // 当前顶点不存在欧拉路径。
        return Collections.emptyList();
    }

    /**
     * 拓扑排序（可用于环检测）
     *
     * @return
     */
    public Iterable<Integer> topologicalSort() {
        if(!directed) {
            throw new IllegalArgumentException("topological sort only works in directed graph.");
        }
        List<Integer> ret = new ArrayList<>(V);
        Queue<Integer> queue = new LinkedList<>();
        int[] indgr = new int[V];
        for (int i = 0; i < V; i++) {
            indgr[i] = indegree(i);
            if (indgr[i] == 0) {
                queue.add(i);
            }
        }

        // 循环处理入度为 0 的顶点
        while (!queue.isEmpty()) {
            int v = queue.poll();
            ret.add(v);
            for (int w : adj(v)) {
                indgr[w]--;
                if (indgr[w] == 0) {
                    queue.add(w);
                }
            }
        }

        // 如果队列已空，但仍有顶点未添加到排序中（入度大于 0），则必然有环：
        //  +------+
        //  ↓      |
        // [1] -> [2]
        if (ret.size() != V) {
            System.out.println("the graph has cycle");
        }
        return ret;
    }

    /**
     *
     * @return
     */
    public Iterable<Integer> topologicalSort2() {
        if(!directed) {
            throw new IllegalArgumentException("topological sort only works in directed graph.");
        }
        if (hasCycleDfs()) {
            System.out.println("the graph has cycle");
            return Collections.emptyList();
        }
        boolean[] visited = new boolean[V];
        LinkedList<Integer> ret = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            if (visited[v]) {
                continue;
            }
            topologicalSortDfs(v, visited, ret);
        }
        return ret;
    }

    /**
     * 拓扑排序（不可用于环检测，前置条件是 DAG）
     *
     * @param v
     * @param visited
     * @param ret
     */
    public void topologicalSortDfs(int v, boolean[] visited, LinkedList<Integer> ret) {
        visited[v] = true;
        for (int w : adj(v)) {
            if (!visited[w]) {
                topologicalSortDfs(w, visited, ret);
            }
        }
        ret.addFirst(v);
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("D:\\Project\\cs-basic\\algorithm\\java\\src\\main\\java\\com\\ywh\\ds\\graph\\ug2.txt", true);
        System.out.print(g.eulerLoopDirected());
//        System.out.println(g.shortestPath(0, 6));
    }

}
