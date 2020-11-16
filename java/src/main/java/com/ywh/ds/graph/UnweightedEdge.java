package com.ywh.ds.graph;

/**
 * 边
 *
 * @author ywh
 * @since 2020/11/14
 */
public class UnweightedEdge {

    private final int v;
    private final int w;

    public UnweightedEdge(int v, int w){
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString(){
        return String.format("%d-%d", v, w);
    }
}
