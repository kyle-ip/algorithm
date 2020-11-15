package com.ywh.ds.graph;

/**
 * 记录到达此处距离的节点
 *
 * @author ywh
 * @since 15/11/2020
 */
public class Node implements Comparable<Node> {

    public int v, dis;

    public Node(int v, int dis) {
        this.v = v;
        this.dis = dis;
    }

    @Override
    public int compareTo(Node another) {
        return dis - another.dis;
    }
}
