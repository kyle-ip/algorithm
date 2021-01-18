package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 重新安排行程
 * [图] [DFS] [回溯]
 *
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * 提示：
 *      如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 *      所有的机场都用三个大写字母表示（机场代码）。
 *      假定所有机票至少存在一种合理的行程。
 *      所有的机票必须都用一次 且 只能用一次。
 * 示例 1：
 *      输入：[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 *      输出：["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 示例 2：
 *      输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 *      输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 *      解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 *
 * @author ywh
 * @since 2020/11/1 12:25
 */
public class LeetCode332 {

    /**
     *
     * @param <F>
     * @param <S>
     */
    static class Pair<F, S> {

        public F first;

        public S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     *
     * @param from
     * @param total
     * @param result
     * @param map
     * @return
     */
    private boolean dfs(String from, int total, List<String> result, Map<String, List<Pair<String, Boolean>>> map) {
        if (result.size() == total) {
            return true;
        }
        List<Pair<String, Boolean>> tos = map.get(from);
        if (tos == null) {
            return false;
        }

        for (Pair<String, Boolean> e: tos) {
            if (e.second) {
                continue;
            }
            e.second = true;
            result.add(e.first);
            if (dfs(e.first, total, result, map)) {
                return true;
            }
            result.remove(result.size()-1);
            e.second = false;
        }
        return false;
    }

    /**
     * Time: O(n!), Space: O(n)
     *
     * @param tickets
     * @return
     */
    public List<String> findItineraryBruteForce(List<List<String>> tickets) {
        Map<String, List<Pair<String, Boolean>>> map = new HashMap<>();
        for (List<String> e: tickets) {
            if (!map.containsKey(e.get(0))) {
                map.put(e.get(0), new ArrayList<>());
            }
            map.get(e.get(0)).add(new Pair<>(e.get(1), false));
        }
        for (List<Pair<String, Boolean>> list: map.values()) {
            list.sort(Comparator.comparing(pair -> pair.first));
        }
        int total = tickets.size() + 1;
        List<String> ret = new ArrayList<>();
        ret.add("JFK");
        dfs("JFK", total, ret, map);
        return ret;
    }

    /**
     *
     * @param from
     * @param ret
     * @param map
     */
    private LinkedList<String> hierholzer(String from, LinkedList<String> ret, Map<String, PriorityQueue<String>> map) {
        // 取出当前起点的最小堆，按从小到大的顺序逐个取出终点作为新的起点并递归调用。
        PriorityQueue<String> tos = map.get(from);
        while (tos != null && !tos.isEmpty()) {
            hierholzer(tos.poll(), ret, map);
        }
        // 当队列为空，添加到结果集的头部（因此最早被添加到首部的是全局终点，因为它不能作为任意路线的起点）。
        ret.addFirst(from);
        return ret;
    }

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param tickets
     * @return
     */
    public List<String> findItineraryHierholzer(List<List<String>> tickets) {
        // 每张机票的起点关联到一个最小堆，队列存放终点。
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> e: tickets) {
            if (!map.containsKey(e.get(0))) {
                map.put(e.get(0), new PriorityQueue<>());
            }
            map.get(e.get(0)).add(e.get(1));
        }
        return hierholzer("JFK", new LinkedList<>(), map);
    }

}