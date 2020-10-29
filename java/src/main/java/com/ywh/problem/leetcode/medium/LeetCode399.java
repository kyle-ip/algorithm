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
    private Double dfs(String cur, String end, double result, Set<String> visited, Map<String, Map<String, Double>> graph) {
        Map<String, Double> nexts = graph.get(cur);
        if (nexts.containsKey(end)) {
            return result * nexts.get(end);
        }
        for (Map.Entry<String, Double> next : nexts.entrySet()) {
            if (visited.contains(next.getKey())) {
                continue;
            }
            visited.add(next.getKey());
            Double ret = dfs(next.getKey(), end, result * next.getValue(), visited, graph);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    /**
     *
     * Time: O(m*V), Space: O(V+E)
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        // a / b = 2.0
        // b / c = 4.0    => equations: [["a", "b"], ["b", "c"], ["c", "d"]]
        // c / d = 5.0       values: [2.0, 4.0, 5.0]
        for (int i = 0; i < equations.size(); ++i) {
            // 从 equations 取被除数、除数。
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            if (!graph.containsKey(a)) {
                graph.put(a, new HashMap<>());
            }
            graph.get(a).put(b, values[i]);
            if (!graph.containsKey(b)) {
                graph.put(b, new HashMap<>());
            }
            graph.get(b).put(a, 1 / values[i]);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); ++i) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (!graph.containsKey(a) || !graph.containsKey(b)) {
                result[i] = -1.0;
            } else if (a.equals(b)) {
                result[i] = 1.0;
            } else {
                Double ret = dfs(a, b, 1.0, new HashSet<>(), graph);
                result[i] = ret != null ? ret : -1.0;
            }
        }
        return result;
    }

}
