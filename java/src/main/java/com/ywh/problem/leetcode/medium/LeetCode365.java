package com.ywh.problem.leetcode.medium;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 水壶问题
 * [数学] [深度优先搜索]
 *
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * 你允许：
 *      装满任意一个水壶
 *      清空任意一个水壶
 *      从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 *      输入: x = 3, y = 5, z = 4
 *      输出: True
 * 示例 2:
 *      输入: x = 2, y = 6, z = 5
 *      输出: False
 *
 * @author ywh
 * @since 4/9/2021
 */
public class LeetCode365 {

    /**
     * Time: O(x*y), Space: O(x*y)
     *
     * @param jug1Capacity
     * @param jug2Capacity
     * @param targetCapacity
     * @return
     */
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[]{0, 0});
        Set<Long> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            if (visited.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }
            visited.add(hash(stack.peek()));

            int[] state = stack.pop();
            int x = state[0], y = state[1];
            if (x == targetCapacity || y == targetCapacity || x + y == targetCapacity) {
                return true;
            }
            // 把 X 壶灌满。
            stack.push(new int[]{jug1Capacity, y});
            // 把 Y 壶灌满。
            stack.push(new int[]{x, jug2Capacity});
            // 把 X 壶倒空。
            stack.push(new int[]{0, y});
            // 把 Y 壶倒空。
            stack.push(new int[]{x, 0});
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            stack.push(new int[]{x - Math.min(x, jug2Capacity - y), y + Math.min(x, jug2Capacity - y)});
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            stack.push(new int[]{x + Math.min(y, jug1Capacity - x), y - Math.min(y, jug1Capacity - x)});
        }
        return false;
    }

    /**
     * @param state
     * @return
     */
    private long hash(int[] state) {
        return (long) state[0] * 1000001 + state[1];
    }

    /**
     * 贝祖定理
     * Time: O(log(min(x,y))), Space: O(1)
     *
     * @param jug1Capacity
     * @param jug2Capacity
     * @param targetCapacity
     * @return
     */
    public boolean canMeasureWaterMath(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (jug1Capacity + jug2Capacity < targetCapacity) {
            return false;
        }
        if (jug1Capacity == 0 || jug2Capacity == 0) {
            return targetCapacity == 0 || jug1Capacity + jug2Capacity == targetCapacity;
        }
        return targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    public int gcd(int x, int y) {
        int remainder = x % y;
        while (remainder != 0) {
            x = y;
            y = remainder;
            remainder = x % y;
        }
        return y;
    }
}
