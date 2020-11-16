package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 行程安排
 *
 * [图] [DFS] [回溯]
 *
 * @author ywh
 * @since 2020/11/1 12:25
 */
public class LeetCode332 {


    class Pair<F, S> {
        public F first;
        public S second;
        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    private boolean dfs(String from, int total, List<String> result,
                        Map<String, List<Pair<String, Boolean>>> map) {
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
        List<String> result = new ArrayList<>();
        result.add("JFK");
        dfs("JFK", total, result, map);
        return result;
    }

    private void hierholzer(String from, List<String> result,
                            Map<String, PriorityQueue<String>> map) {
        PriorityQueue<String> tos = map.get(from);
        while (tos != null && !tos.isEmpty()) {
            hierholzer(tos.poll(), result, map);
        }
        result.add(0, from);
    }

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param tickets
     * @return
     */
    public List<String> findItineraryHierholzer(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> e: tickets) {
            if (!map.containsKey(e.get(0))) {
                map.put(e.get(0), new PriorityQueue<>());
            }
            map.get(e.get(0)).add(e.get(1));
        }
        List<String> result = new LinkedList<>();
        hierholzer("JFK", result, map);
        return result;
    }

}