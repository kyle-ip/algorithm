package com.ywh.problem.leetcode.medium;

/**
 * 省份数量
 * [并查集] [深度优先搜索]
 * 
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 * 而 isConnected[i][j] = 0 表示二者不直接相连。返回矩阵中 省份 的数量。
 * 示例 1：
 *      输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 *      输出：2
 * 示例 2：
 *      输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 *      输出：3
 * 提示：
 *      1 <= n <= 200
 *      n == isConnected.length
 *      n == isConnected[i].length
 *      isConnected[i][j] 为 1 或 0
 *      isConnected[i][i] == 1
 *      isConnected[i][j] == isConnected[j][i]
 * 
 * @author ywh
 * @since 2021/1/7/007
 */
public class LeetCode547 {

    /**
     * Time: O(n^2), Space: O(n)
     *
     * @param isConnected
     * @return
     */
    public int findCircleNumDfs(int[][] isConnected) {
        int n = isConnected.length, cnt = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cnt++;
                dfs(isConnected, visited, i);
            }
        }
        return cnt;
    }

    /**
     * 深度优先遍历，每次完整的调用都遍历一个连通分量。
     *
     * @param isConnected
     * @param visited
     * @param i
     */
    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        // 每访问一个新节点，就标记为已访问。
        visited[i] = true;

        // 遍历所有节点，如果已访问则跳过，未访问、且相连的则递归调用。
        for (int j = 0; j < isConnected.length; j++) {
            if (!visited[j] && isConnected[i][j] == 1) {
                dfs(isConnected, visited, j);
            }
        }
    }

    /**
     * Time: O(n^2*log(n)), Space: O(n)
     * 其中 n 是城市的数量。需要遍历矩阵 isConnected 中的所有元素，时间复杂度是 O(n^2)。
     * 如果遇到相连关系，则需要进行 2 次查找和最多 1 次合并，一共需要进行 2n^2 次查找和最多 n^2 次合并。
     * 因此总时间 O(n^2*log(n))。此处并查集使用路径压缩，但是没有使用按秩合并，最坏情况下的时间复杂度是 O(n^2*log(n))。
     * 平均情况下的时间复杂度依然是 O(n^2*α(n))，其中 α(n) 为阿克曼函数的反函数，α(n) 可以认为是一个很小的常数。
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;

        // 初始化并查集，所有节点的父节点都是它自身。
        int[] uf = new int[m];
        for (int i = 0; i < m; i++) {
            uf[i] = i;
        }

        // 合并相连节点（要去重）。
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (isConnected[i][j] == 1) {
                    union(uf, i, j);
                }
            }
        }

        // 统计集合数量。
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (uf[i] == i) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 并查集：查
     *
     * @param parent
     * @param p
     * @return
     */
    private int find(int[] parent, int p) {
        if (p != parent[p]) {
            parent[p] = find(parent, parent[p]);
        }
        return parent[p];
    }

    /**
     * 并查集：并
     *
     * @param parent
     * @param p
     * @param q
     */
    private void union(int[] parent, int p, int q) {
        // 此处不去重，只要是相连就合并在一起。
        parent[find(parent, p)] = find(parent, q);
    }
}
