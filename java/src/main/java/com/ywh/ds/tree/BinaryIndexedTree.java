package com.ywh.ds.tree;

/**
 * 树状数组（二进制索引树）
 *
 * @author ywh
 * @since 2020/11/28/028
 */
public class BinaryIndexedTree {

    int[] tree;
    int n;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    public void update(int x, int d) {
        while (x <= n) {
            tree[x] += d;
            x += x & (-x);
        }
    }

    public int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += tree[x];
            x -= x & (-x);
        }
        return ret;
    }
}
