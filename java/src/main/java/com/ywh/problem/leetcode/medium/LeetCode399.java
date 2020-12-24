package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 除法求值
 * [图]
 *
 * @author ywh
 * @since 2020/10/29/029
 */
public class LeetCode399 {

    /**
     * 深度优先遍历。
     *
     * @param cur     当前节点
     * @param end     终点
     * @param result  权重累乘
     * @param visited 已访问集合
     * @param graph   有向图图
     * @return 权重累乘结果
     */
    private Double dfs(String cur, String start, String end, double result, Set<String> visited,
                       Map<String, Map<String, Double>> graph) {
        // 邻接节点与边权重的对应关系：a -> (b, 2.0)
        Map<String, Double> nexts = new HashMap<>(graph.get(cur));
        // 已经找到终点，累乘上最后一条边的权重并返回。
        if (nexts.containsKey(end)) {
            result *= nexts.get(end);
            graph.get(start).put(end, result);
            graph.get(end).put(start, 1 / result);
            return result;
        }
        // 遍历所有邻接节点：(b, 2.0)
        for (Map.Entry<String, Double> next: nexts.entrySet()) {
            // 当前邻接节点已访问，跳过。
            if (visited.contains(next.getKey())) {
                continue;
            }
            // 把邻接节点添加到已访问集合。
            visited.add(next.getKey());
            double d = result * next.getValue();
            // 路径压缩：a->b->c => a->c
            if (!cur.equals(start)) {
                graph.get(start).put(next.getKey(), d);
                graph.get(next.getKey()).put(start, 1 / d);
            }
            // 递归调用，返回结果不为空则提前返回结果。
            Double ret = dfs(next.getKey(), start, end, d, visited, graph);
            // 不需要回溯：假设经过节点 p 的路径都无法到达终点 d，则可达到终点 d 的路径必然不包含 p，因此不需要把 p 设置为未访问。
            if (ret != null) {
                return ret;
            }
        }

        // 找不到合法路径，返回空。
        return null;
    }

    /**
     * 如果在给出的等式中，可以找出一部分等式或其倒数，在连乘约简后得到查询表达式，则可以将这些等式结果连乘，得到查询结果。
     * 比如 a/c == a/b * b/c，计算 a/c 的结果转化为在有向图上找到节点 a 到 节点 c 的一条路径，把路径上所有边的权重连乘即可。
     * 1. 将给出的分子分母二元组序列及其对应的结果，构建乘一个有向带权图。
     * 2. 在图中查询路径，并计算这条路径上所有边权重的乘积。
     *
     * Time: O(m*V), Space: O(V+E)
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // graph 为有向带权图，key 存放节点，value 存放邻接节点以及相应的边的权重。
        // 比如 a / b，a 的邻接节点为 b，邻接边权重为 2.0；相反，b 的邻接节点为 a，邻接边权重为 0.5。

        // 输入：
        //      a / b = 2.0
        //      b / c = 4.0    =>    equations: [["a", "b"], ["b", "c"], ["c", "d"]]
        //      c / d = 5.0          values:    [2.0, 4.0, 5.0]

        // 生成有向带权图图（可压缩：在遍历过程中补充，比如 a->b->c == a->c）：
        //      key      value
        //      a        (b, 2.0), [c, 8], [d, 40]
        //      b        (a, 0.5), (c, 4.0)
        //      c        (b, 0.25), (d, 5.0)
        //      d        (c, 0.2)
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); ++i) {
            // 从 equations 取被除数、除数。
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            //   values[i]
            // a     ->    b
            if (!graph.containsKey(a)) {
                graph.put(a, new HashMap<>());
            }
            graph.get(a).put(b, values[i]);
            //   1/values[i]
            // b     ->    a
            if (!graph.containsKey(b)) {
                graph.put(b, new HashMap<>());
            }
            graph.get(b).put(a, 1 / values[i]);
        }

        double[] result = new double[queries.size()];
        // [["a","d"], ["b","a"], ["a","c"], ["a","e"], ["a","a"], ["e","e"]]
        for (int i = 0; i < queries.size(); ++i) {
            String a = queries.get(i).get(0), b = queries.get(i).get(1);
            // 其中一个节点不存在于图中，无法得出表达式的结果。
            if (!graph.containsKey(a) || !graph.containsKey(b)) {
                result[i] = -1.0;
            }
            // 两个节点为同一个节点，结果为 1.0。
            else if (a.equals(b)) {
                result[i] = 1.0;
            }
            // 深度优先遍历。
            else {
                Double ret = dfs(a, a, b, 1.0, new HashSet<>(), graph);
                result[i] = ret != null ? ret : -1.0;
            }
        }
        return result;
    }

}
