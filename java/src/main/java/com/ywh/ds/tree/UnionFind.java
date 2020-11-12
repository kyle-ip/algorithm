package com.ywh.ds.tree;

/**
 * 并查集
 *
 * @author ywh
 * @since 12/11/2020
 */
public class UnionFind {

    private final int[] parent;

    private final int[] sz;

    /**
     *
     * @param n
     */
    public UnionFind(int n) {
        parent = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    /**
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
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     *
     * @param p
     * @param q
     */
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        parent[pRoot] = qRoot;
        sz[qRoot] += sz[pRoot];
    }

    /**
     *
     * @param p
     * @return
     */
    public int size(int p) {
        return sz[find(p)];
    }
}
