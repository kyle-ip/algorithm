package com.ywh.ds.tree;

/**
 * 线段树
 *
 * @author ywh
 * @since 29/11/2020
 */
public class SegmentTree {

    /**
     * 树节点
     */
    private final SegTreeNode root;

    /**
     * 权值（仅在建树时有效）
     */
    private final int[] weight;

    /**
     *
     * @param weight
     * @param start
     * @param end
     */
    public SegmentTree(int[] weight, int start, int end) {
        this.weight = weight;
        this.root = build(new SegTreeNode(start, end));
    }

    /**
     * 创建线段树
     *
     * @param weight
     */
    public SegmentTree(int[] weight) {
        this.weight = weight;
        this.root = build(new SegTreeNode(0, weight.length - 1));
    }

    /**
     *
     * @param node
     * @return
     */
    private SegTreeNode build(SegTreeNode node) {
        // 当前节点的区间左右端点相等，则为叶子节点，其权值为端点值。
        if (node.start == node.end) {
            node.val = weight[node.start];
        } else {
            // 取区间中点，递归求左右节点。
            int mid = (node.start + node.end) >> 1;
            node.left = build(new SegTreeNode(node.start, mid));
            node.right = build(new SegTreeNode(mid + 1, node.end));
            node.val = extremum(node.left.val, node.right.val);
        }
        return node;
    }

    /**
     * 寻找区间内的最值
     *
     * @param start
     * @param end
     * @return
     */
    public int query(int start, int end) {
        return query(root, start, end);
    }

    /**
     * 更新节点
     * 找到区间目标下标的叶子节点，对其进行更新。
     * 叶子节点更新之后，其父节点也需要更新，父节点为左右子树中较小者。
     *
     * @param idx
     * @param val
     */
    public void update(int idx, int val) {
        update(root, idx, val);
    }

    /**
     * 更新区间（把 [start, end] 内的节点都更新为 val）
     *
     * @param start
     * @param end
     * @param val
     */
    public void updateInterval(int start, int end, int val) {
        updateInterval(root, start, end, val);
    }

    /**
     *
     * @param node
     * @param start
     * @param end
     * @return
     */
    private int query(SegTreeNode node, int start, int end) {

        // 当前区间和待查询区间没有交集，返回最大值。
        if (node.start > end || node.end < start) {
            return Integer.MAX_VALUE;
        }
        // 当前区间包含待查询区间，返回当前区间极值。
        if (node.start >= start && node.end <= end) {
            return node.val;
        }
        // 递归查询左子树和右子树。
        // 在返回左右子树的最小值之前，进行扩展操作。
        pushDown(node);
        return extremum(query(node.left, start, end), query(node.right, start, end));
    }

    /**
     *
     *
     * @param node
     * @param idx
     * @param val
     * @return
     */
    private void update(SegTreeNode node, int idx, int val) {

        // 到达叶子节点，且该区间端点即为待更新的点。
        if (node.start == node.end && node.start == idx) {
            node.val = val;
            return;
        }
        int mid = (node.start + node.end) >> 1;

        // 更新左右区间。
        if (idx <= mid) {
            update(node.left, idx, val);
        } else {
            update(node.right, idx, val);
        }
        node.val = extremum(node.left.val, node.right.val);
    }

    /**
     *
     * @param node
     * @param start
     * @param end
     * @param val
     */
    private void updateInterval(SegTreeNode node, int start, int end, int val) {
        // 当前区间和待查询区间没有交集，返回。
        if (node.start > end || node.end < start) {
            return;
        }
        // 当前区间包含待查询区间，加上标记。
        if (node.start >= start && node.end <= end) {
            // TODO 为什么是 +=？
            node.val = val;
            node.addMark(val);
            return;
        }

        // 在返回左右子树的最小值之前，进行扩展操作。
        pushDown(node);
        updateInterval(node.left, start, end, val);
        updateInterval(node.right, start, end, val);
        node.val = extremum(node.left.val, node.right.val);
    }

    /**
     * 把当前节点的标志值传给子节点
     *
     * @param node
     */
    private void pushDown(SegTreeNode node) {
        if (node == null || node.mark == 0) {
            return;
        }
        // 更新左右字数标志。
        node.left.addMark(node.mark);
        node.right.addMark(node.mark);

        // 左右子树的值加上标志值。
        node.left.val += node.mark;
        node.right.val += node.mark;

        // 清除当前节点标志。
        node.clearMark();
    }

    /**
     *
     * @return
     */
    private int extremum(int a, int b) {
        return Math.min(a, b);
    }

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree(new int[]{5, 9, 7, 4, 6, 1});
        st.updateInterval(0, 2, 3);
        System.out.println(st.query(0, 1));
        st.updateInterval(0, 2, 10);
        System.out.println();
    }
}
