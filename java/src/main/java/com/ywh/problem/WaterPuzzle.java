package com.ywh.problem;

import java.util.*;

/**
 * 两个水桶，一个能装五升，一个能装三升，如何利用两个水桶得到四升水？
 */
public class WaterPuzzle {

    public Iterable<Integer> result() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100];
        int[] pre = new int[100];
        int end = -1;

        // 最初五桶和三桶都为空。
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            int a = cur / 10, b = cur % 10;
            List<Integer> nexts = new ArrayList<>();

            // 六种情况：倒进五桶、倒进三桶、倒掉五桶、倒掉三桶、五桶倒向三桶、三桶倒向五桶。
            nexts.add(5 * 10 + b);
            nexts.add(a * 10 + 3);
            nexts.add(a * 10 + 0);
            nexts.add(0 * 10 + b);
            int x = Math.min(a, 3 - b);
            nexts.add((a - x) * 10 + (b + x));
            int y = Math.min(b, 5 - a);
            nexts.add((a + y) * 10 + (b - y));

            for (int next : nexts) {
                if (visited[next]) {
                    continue;
                }
                queue.add(next);
                visited[next] = true;
                pre[next] = cur;
                if (next / 10 == 4 || next % 10 == 4) {
                    end = next;
                    break;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        if (end == -1) {
            return res;
        }

        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }
}
