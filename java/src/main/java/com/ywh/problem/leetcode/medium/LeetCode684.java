package com.ywh.problem.leetcode.medium;

/**
 * 冗余连接
 * [树] [图] [并查集]
 *
 * 在本问题中, 树指的是一个连通且无环的无向图。
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 * 示例 1：
 *      输入: [[1,2], [1,3], [2,3]]
 *      输出: [2,3]
 *      解释: 给定的无向图为:
 *            1
 *           / \
 *          2 - 3
 * 示例 2：
 *      输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 *      输出: [1,4]
 *      解释: 给定的无向图为:
 *          5 - 1 - 2
 *              |   |
 *              4 - 3
 * 注意:
 *      输入的二维数组大小在 3 到 1000。
 *      二维数组中的整数在1到N之间，其中N是输入数组的大小。
 *
 * @author ywh
 * @since 2021/1/13/013
 */
public class LeetCode684 {

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        int nodesCount = edges.length;
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {

            // 取边的两个顶点。
            int node1 = edge[0], node2 = edge[1];
            // node1 与 node2 未相连，则把它们（的根）相连。
            if (find(parent, node1) != find(parent, node2)) {
                union(parent, node1, node2);
            }
            // node1 与 node2 已（通过其他路径）相连，表示存在回路，返回当前的边。
            else {
                return edge;
            }
        }
        return new int[0];
    }

    /**
     *
     * @param parent
     * @param index1
     * @param index2
     */
    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    /**
     *
     * @param parent
     * @param index
     * @return
     */
    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
