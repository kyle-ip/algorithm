package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 移除最多的同行或同列石头
 * [DFS] [并查集]
 * 
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 * 示例 1：
 *      输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 *      输出：5
 *      解释：一种移除 5 块石头的方法如下所示：
 *          1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 *          2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 *          3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 *          4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 *          5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 *      石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 * 示例 2：
 *      输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 *      输出：3
 *      解释：一种移除 3 块石头的方法如下所示：
 *          1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 *          2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 *          3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 *      石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 示例 3：
 *      输入：stones = [[0,0]]
 *      输出：0
 *      解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 * 提示：
 *      1 <= stones.length <= 1000
 *      0 <= xi, yi <= 10^4
 *      不会有两块石头放在同一个坐标点上
 *
 * @author ywh
 * @since 2021/1/15/015
 */
public class LeetCode947 {


    /**
     * TODO 暂时未理解
     *
     * Time: O(n*log(A)), Space: O(A)
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        // 删到最后留在图中的顶点一定位于不同的行和不同的列。
        // 使用并查集描述横坐标和纵坐标的数值，需要遍历 stones，将每个 stone 的横纵坐标再并查集合并。
        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones) {
            // 合并：所有横坐标为 x 的石头和所有纵坐标为 y 的石头属于同一个连通分量。
            // 区分横纵坐标：比如坐标 (3, 3)，所有横坐标为 3 的石头和所有纵坐标为 3 的石头都在一个连通分量中。
            //             但它们在并查集不能相等，根据提示 0 <= xi, yi <= 10^4，可以把其中一个坐标映射到另一个 [0, 10000] 不重合的区间上。
            //             方法是对横坐标或纵坐标取反，或加/减 10000。
            // 统计连通分量个数：新创建顶点时连通分量加 1，合并不在同一个连通分量中的并查集时连通分量 - 1。
            unionFind.union(~stone[0], stone[1]);
        }
        return stones.length - unionFind.getCount();
    }

    private class UnionFind {

        private Map<Integer, Integer> parent;

        private int count;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int getCount() {
            return count;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                count++;
            }
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent.put(rootX, rootY);
            count--;
        }
    }
}
