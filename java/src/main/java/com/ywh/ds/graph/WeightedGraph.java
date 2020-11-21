package com.ywh.ds.graph;

import com.ywh.ds.tree.UnionFind;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 有权图
 *
 * @author ywh
 * @since 15/11/2020
 */
public class WeightedGraph implements Graph {

    /**
     * 顶点
     */
    private int V;


    /**
     * 边
     */
    private int E;

    /**
     * 邻接表
     * [
     *      0: {1: 3, 2: 2, 3: -2},
     *      1: (2: 2, 3: 2, 4: 1)
     * ]
     */
    private TreeMap<Integer, Integer>[] adj;

    /**
     * 是否有向
     */
    private final boolean directed;

    /**
     * 入度，出度
     */
    private int[] indegrees, outdegrees;

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
    public WeightedGraph(String filename, boolean directed) {
        this.directed = directed;
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
            indegrees = new int[V];
            outdegrees = new int[V];
            int e = scanner.nextInt();
            if (e < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }
            for (int i = 0; i < e; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int v = scanner.nextInt();
                addEdge(a, b, v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WeightedGraph(String fileName) {
        this(fileName, false);
    }

    public WeightedGraph(int v, boolean directed) {
        this.V = v;
        this.directed = directed;
        this.E = 0;
        this.adj = new TreeMap[v];
        indegrees = new int[V];
        outdegrees = new int[V];
        for (int i = 0; i < v; i++) {
            adj[i] = new TreeMap<>();
        }
    }

    /**
     * 添加边
     *
     * @param a
     * @param b
     * @param v
     */
    public void addEdge(int a, int b, int v) {
        validateVertex(a);
        validateVertex(b);
        if (a == b) {
            throw new IllegalArgumentException("Self Loop is Detected!");
        }
        if (adj[a].containsKey(b)) {
            throw new IllegalArgumentException("Parallel Edges are Detected!");
        }
        adj[a].put(b, v);
        outdegrees[a]++;
        indegrees[b]++;
        if (!directed) {
            adj[b].put(a, v);
        }
        this.E ++;
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
     * 获取权值
     *
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
     * 更新权值
     *
     * @param v
     * @param w
     * @param newWeight
     */
    public void setWeight(int v, int w, int newWeight){
        if (!hasEdge(v, w)) {
            throw new IllegalArgumentException(String.format("no edge %d-%d", v, w));
        }
        adj[v].put(w, newWeight);
        if (!directed) {
            adj[w].put(v, newWeight);
        }
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

        if (adj[v].entrySet().contains(w)) {
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

    @Override
    public int degree(int v) {
        if (!directed) {
            throw new RuntimeException("degree only works in undirected graph.");
        }
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public int indegree(int v) {
        if (!directed) {
            throw new RuntimeException("indegree only works in directed graph.");
        }
        validateVertex(v);
        return indegrees[v];
    }

    @Override
    public int outdegree(int v) {
        if (!directed) {
            throw new RuntimeException("outdegree only works in directed graph.");
        }
        validateVertex(v);
        return outdegrees[v];
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
            WeightedGraph cloned = (WeightedGraph) super.clone();
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
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d, directed = %b\n", V, E, directed));
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

    /**
     * Time: O(V^2)
     *
     * @param src
     * @return
     */
    public int[] shortestPathDijkstra0(int src) {
        int[] dis = new int[V];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;
        boolean[] visited = new boolean[V];
        while (true) {

            // 每轮循环都要判断所有的顶点，可用最小堆优化。
            int cur = -1, curDis = Integer.MAX_VALUE;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && dis[v] < curDis) {
                    curDis = dis[v];
                    cur = v;
                }
            }

            // 所有顶点都已确定最短路径，退出循环。
            if (cur == -1) {
                break;
            }
            visited[cur] = true;
            for (int w : adj(cur)) {
                if (!visited[w] && dis[cur] + getWeight(cur, w) < dis[w]) {
                    dis[w] = dis[cur] + getWeight(cur, w);
                }
            }
        }
        return dis;
    }

    /**
     * 求最短路径（Dijkstra 算法）
     * 单源最短路径，不能包含负权边。
     * 
     * Time: O(E*log(E)) => O(V*log(E))
     *
     * @param src
     * @return
     */
    public Iterable<Integer> shortestPathDijkstra(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);
        int[] dis = new int[V], prev = new int[V];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dis[src] = 0;
        prev[src] = src;

        // visited 用于记录“已确定最短距离”的顶点。
        boolean[] visited = new boolean[V];

        // 最小堆用于高效提取 dis 最小的顶点（同一个点可能存在多份）。
        // 而最小值必然是最近更新的结果，即距离已更新点 v 最近的点。
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        // 每轮循环可决定（到达）一个点的最短距离。
        while (!pq.isEmpty()) {

            // 1. 利用最小堆找到 dis 最小值的顶点。
            int v = pq.poll()[0];

            // 2. 更新顶点状态，如果该点已经确定最短路径则跳过。
            if (visited[v]) {
                continue;
            }
            visited[v] = true;

//            // 找到到达终点的最短路径，提前结束（对于 Bellman Ford 算法，由于存在松弛操作，不能提前终止）。
//            if (v == dest) {
//                break;
//            }

            // 3. 根据刚才确定的解，更新邻接顶点的 dis 值。
            for (int w : adj(v)) {

                // 如果 w 未确定最短路径，且其目前的 dis 值大于经由 v 到达 w 的距离，则更新 dis[w] 并添加到最小堆。
                // 比如：
                //           3
                //       +------[w]
                //       |       | 1
                //      [0]-----[v]
                //           1
                // 此时 w 仍未确定，需要重新放入最小堆，用作下次继续更新。
                if (!visited[w] && dis[w] > dis[v] + getWeight(v, w)) {
                    dis[w] = dis[v] + getWeight(v, w);
                    pq.add(new int[]{w, dis[w]});
                    prev[w] = v;
                }
            }
        }

        // 根据 prev 数组求整个路径。
        LinkedList<Integer> ret = new LinkedList<>();
        if (!visited[dest]) {
            return ret;
        }
        int cur = dest;
        while (cur != src) {
            ret.addFirst(cur);
            cur = prev[cur];
        }
        ret.addFirst(src);
        return ret;
    }

    /**
     * 求最短路径（Bellman-Ford 算法）
     * 单源最短路径，可以包含负权边，检测负权环。
     * 
     * Time: O(V*E)
     *
     * @param src
     * @param dest
     * @return
     */
    public Iterable<Integer> shortestPathBellmanFord(int src, int dest) {
        validateVertex(src);
        validateVertex(dest);
        int[] dis = new int[V], prev = new int[V];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dis[src] = 0;

        // 进行 v-1 轮松弛操作。
        for (int pass = 1; pass < V; pass++) {

            // 在每轮操作中对所有的边进行松弛操作：遍历每个顶点的邻接顶点，即取出每条边。
            for (int v = 0; v < V; v++) {
                for (int w : adj(v)) {

                    // 如果 dis[v] 已被更新过，且 dis[w] 目前的 dis 值大于经由 v 到达 w 的距离，则更新 dis[w]。
                    if (dis[v] != Integer.MAX_VALUE && dis[w] > dis[v] + getWeight(v, w)) {
                        dis[w] = dis[v] + getWeight(v, w);
                        prev[w] = v;
                    }
                }
            }
        }

        // 判断是否有负权环：进行第 v 轮松弛操作。
        boolean hasNegCycle = false;
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                if (dis[v] != Integer.MAX_VALUE && dis[w] > dis[v] + getWeight(v, w)) {
                    hasNegCycle = true;
                }
            }
        }

        LinkedList<Integer> ret = new LinkedList<>();

        // 存在负权环，返回空。
        if (hasNegCycle) {
            System.out.println("exist negative cycle");
            return ret;
        }
        // dis[dest] 没有被更新，表示 dest 不在路径上，返回空。
        if (dis[dest] == Integer.MAX_VALUE) {
            return ret;
        }
        int cur = dest;
        while (cur != src) {
            ret.addFirst(cur);
            cur = prev[cur];
        }
        ret.addFirst(src);
        return ret;
    }

    /**
     * 求最短路径（Floyd 算法）
     * 所有点对最短路径，可以包含负权边，检测负权环。
     * 
     * Time: O(V^3)
     *
     * @return
     */
    public Iterable<Integer> shortestPathFloyd(int src, int dest) {

        // dis[v][w] 表示 从 v 到 w 的最短路径长度。
        int[][] dis = new int[V][V];
        int[] prev = new int[V];
        Arrays.fill(prev, -1);
        for (int v = 0; v < V; v++) {
            Arrays.fill(dis[v], Integer.MAX_VALUE);
        }
        for (int v = 0; v < V; v++) {
            dis[v][v] = 0;
            for (int w : adj(v)) {
                dis[v][w] = getWeight(v, w);
            }
        }
        // 相当于松弛操作，每轮循环求解出中间绕过 [0, t] 这些点的最短路径。
        for (int t = 0; t < V; t++) {
            // 寻找最短路径的起始点和终止点。
            for (int v = 0; v < V; v++) {
                for (int w = 0; w < V; w++) {
                    if (dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE
                            && dis[v][t] + dis[t][w] < dis[v][w]) {
                        dis[v][w] = dis[v][t] + dis[t][w];
                        prev[w] = v;
                    }
                }
            }
        }
        for (int v = 0; v < V; v++) {
            if (dis[v][v] < 0) {
                // 存在负权环，返回空。
                return Collections.emptyList();
            }
        }

        // FIXME 具体路径求解有问题。
        LinkedList<Integer> ret = new LinkedList<>();
        int cur = dest;
        while (cur != src) {
            ret.addFirst(cur);
            cur = prev[cur];
        }
        ret.addFirst(src);
        return ret;
    }

    /**
     * 求解最大流（Edmonds-Karp 算法）
     *
     * Time: O(max*E) => O(V*E*E)
     *
     * @param s
     * @param t
     * @return
     */
    public int maxFlowEdmondsKarp(int s, int t) {
        // 参数校验。
        if(!directed) {
            throw new IllegalArgumentException("max flow only works in directed graph.");
        }
        if (V < 2) {
            throw new IllegalArgumentException("the network should hs at least 2 vertices.");
        }
        validateVertex(s);
        validateVertex(t);
        if (s == t) {
            throw new IllegalArgumentException("s and t should be different.");
        }

        int maxFlow = 0;

        // 创建残量图。
        WeightedGraph rG = new WeightedGraph(V, true);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                // 原图的容量 => 残量图的权值，反向为 0（初始时正向边没有流量，反向边也没有流量抵消）。
                rG.addEdge(v, w, getWeight(v, w));
                rG.addEdge(w, v, 0);
            }
        }

        // 在残量图中寻找增广路径。
        while (true) {
            List<Integer> augPath = getAugmentingPath(s, t, rG);

            // 找不到增广路径，已求出最大流，返回。
            if (augPath.size() == 0) {
                return maxFlow;
            }
            // 访问增广路径上所有的边，计算边最小权值，累计到最大流上。
            int f = Integer.MAX_VALUE;
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1), w = augPath.get(i);
                f = Math.min(f, rG.getWeight(v, w));
            }
            maxFlow += f;

            // 根据增广路径更新 rG 的流量。
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1), w = augPath.get(i);
                // 正向减去增广路径的最小权值，反向加上增广路径的最小权值。
                rG.setWeight(v, w, rG.getWeight(v, w) - f);
                rG.setWeight(w, v, rG.getWeight(w, v) + f);
            }
        }
        // 边 v-w 的流量，即残量图的反向边的权值（可抵消的流量）。
        // int flow = rg.getWeight(w, v);
    }

    /**
     * 寻找增广路径。
     *
     * @param s
     * @param t
     * @param rG
     * @return
     */
    private List<Integer> getAugmentingPath(int s, int t, WeightedGraph rG) {

        // 广度优先遍历。
        Queue<Integer> q = new LinkedList<>();

        // 记录顶点是否被遍历过、当前顶点的前一个顶点（初始化为 -1，>= 0 表示已经遍历过，且存储上一个节点）。
        int[] pre = new int[V];
        Arrays.fill(pre, -1);

        q.add(s);
        pre[s] = s;
        while(!q.isEmpty()){
            int cur = q.remove();
            // 遇到汇点，退出循环。
            if (cur == t) {
                break;
            }

            // 遍历邻接节点。
            for (int next: rG.adj(cur)) {
                // 邻接节点未访问过，且在残量图上、与邻接节点的边权值大于 0，则添加到路径。
                if(pre[next] == -1 && rG.getWeight(cur, next) > 0){
                    pre[next] = cur;
                    q.add(next);
                }
            }
        }

        // 还原增广路径并返回（必须到达汇点）。
        LinkedList<Integer> res = new LinkedList<>();
        if (pre[t] == -1) {
            return res;
        }
        int cur = t;
        while(cur != s){
            res.addFirst(cur);
            cur = pre[cur];
        }
        res.addFirst(s);
        return res;
    }


    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("C:\\Project\\cs-basic\\algorithm\\java\\src\\main\\java\\com\\ywh\\ds" +
            "\\graph\\wg2.txt", true);
        System.out.println(g.maxFlowEdmondsKarp(0, 5));
    }
}
