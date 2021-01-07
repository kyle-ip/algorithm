package com.ywh.ds.tree;

/**
 * 并查集
 *
 * @author ywh
 * @since 12/11/2020
 */
public class UnionFind {

    /**
     * 父节点
     * parent[p] 即 p 的父节点。
     */
    private final int[] parent;

    /**
     * 集合大小（优化树结构）
     */
    private final int[] size;

    /**
     * 初始化：
     * [0, n) 所有节点的父节点都是它自身，集合大小都为 1。
     *
     * @param n
     */
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 查询：从给定节点迭代向其父节点查询，直到根节点。
     * 并更新沿途访问节点的根节点。
     *
     * @param p
     * @return
     */
    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    /**
     * 判断两个点是否相连
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并：
     * 两个给定的节点迭代查询到根节点，如果它们有共同根节点则直接返回，
     * 否则把其中的一个的根节点接到另一个根节点上（节点数更多的作为根节点）。
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        // 不去重（根的值相同也合并）。
        // parent[find(parent, p)] = find(parent, q);

        // 去重（根的值相同不合并）。
        int pRoot = find(p), qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (size[p] > size [q]) {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        } else {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
    }

    /**
     * 求大小：
     * 某个节点的大小等于其根节点的节点数。
     *
     * @param p
     * @return
     */
    public int size(int p) {
        return size[find(p)];
    }

    /**
     * 联通分量个数：父节点为其自身的点的数量。
     *
     * @return
     */
    public int cc() {
        int cnt = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                cnt++;
            }
        }
        return cnt;
    }
}
