package com.ywh.ds.tree;

/**
 * 树状数组（二进制索引树）
 *
 * @author ywh
 * @since 2020/11/28/028
 */
public class BinaryIndexedTree {

    int[] tree;

    public BinaryIndexedTree(int n) {
        this.tree = new int[n + 1];
    }

    /**
     * @param x
     * @return
     */
    private int lowbit(int x) {
        return x & (-x);
    }

    public void update(int x, int d) {
        for (; x < tree.length; tree[x] += d, x += lowbit(x)) {
        }
    }

    /**
     * 查询前 x 个元素之和
     *
     * @param x
     * @return
     */
    public int query(int x) {
        int ret = 0;
        for (; x != 0; ret += tree[x], x -= lowbit(x)) {
        }
        return ret;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    public int query(int a, int b) {
        return query(b) - query(a - 1);
    }

}
