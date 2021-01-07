package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 除法求值
 * [图]
 * 
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * 示例 1：
 *      输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 *      输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 *      解释：
 *          条件：a / b = 2.0, b / c = 3.0
 *          问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 *          结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 示例 2：
 *      输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 *      输出：[3.75000,0.40000,5.00000,0.20000]
 * 示例 3：
 *      输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 *      输出：[0.50000,2.00000,-1.00000,-1.00000]
 * 提示：
 *      1 <= equations.length <= 20
 *      equations[i].length == 2
 *      1 <= Ai.length, Bi.length <= 5
 *      values.length == equations.length
 *      0.0 < values[i] <= 20.0
 *      1 <= queries.length <= 20
 *      queries[i].length == 2
 *      1 <= Cj.length, Dj.length <= 5
 *      Ai, Bi, Cj, Dj 由小写英文字母与数字组成
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
     * @param ret  权重累乘
     * @param visited 已访问集合
     * @param graph   有向图图
     * @return 权重累乘结果
     */
    private Double dfs(String cur, String start, String end, double ret, Set<String> visited, Map<String, Map<String, Double>> graph) {
        // 邻接节点与边权重的对应关系：a -> (b, 2.0)
        Map<String, Double> nexts = new HashMap<>(graph.get(cur));
        // 已经找到终点，累乘上最后一条边的权重并返回。
        if (nexts.containsKey(end)) {
            ret *= nexts.get(end);
            graph.get(start).put(end, ret);
            graph.get(end).put(start, 1 / ret);
            return ret;
        }
        // 遍历所有邻接节点：(b, 2.0)
        for (Map.Entry<String, Double> next: nexts.entrySet()) {
            // 当前邻接节点已访问，跳过。
            if (visited.contains(next.getKey())) {
                continue;
            }
            // 把邻接节点添加到已访问集合。
            visited.add(next.getKey());
            double d = ret * next.getValue();
            // 路径压缩：a->b->c => a->c
            if (!cur.equals(start)) {
                graph.get(start).put(next.getKey(), d);
                graph.get(next.getKey()).put(start, 1 / d);
            }
            // 递归调用，返回结果不为空则提前返回结果。
            Double curRet = dfs(next.getKey(), start, end, d, visited, graph);
            // 不需要回溯：假设经过节点 p 的路径都无法到达终点 d，则可达到终点 d 的路径必然不包含 p，因此不需要把 p 设置为未访问。
            if (curRet != null) {
                return curRet;
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
            //    values[i]
            // a     ->     b
            if (!graph.containsKey(a)) {
                graph.put(a, new HashMap<>());
            }
            graph.get(a).put(b, values[i]);
            //   1/values[i]
            // b     ->     a
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
                Double curRet = dfs(a, a, b, 1.0, new HashSet<>(), graph);
                result[i] = curRet != null ? curRet : -1.0;
            }
        }
        return result;
    }

}
