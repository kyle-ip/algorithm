package com.ywh.ds.graph;

/**
 * 图接口
 *
 * @author ywh
 * @since 2020/11/11/011
 */
public interface Graph {

    int V();

    int E();

    boolean hasEdge(int v, int w);

    Iterable<Integer> adj(int v);

    int degree(int v);

    int connectedComponentsCount();
}
