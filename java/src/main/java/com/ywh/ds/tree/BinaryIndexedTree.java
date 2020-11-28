package com.ywh.ds.tree;

/**
 * 树状数组（二叉索引树）
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

    public static int lowBit(int x) {
        return x & (-x);
    }

    public void update(int x, int d) {
        while (x <= n) {
            tree[x] += d;
            x += lowBit(x);
        }
    }

    public int query(int x) {
        int ans = 0;
        while (x != 0) {
            ans += tree[x];
            x -= lowBit(x);
        }
        return ans;
    }
}
