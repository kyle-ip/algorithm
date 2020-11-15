package com.ywh.ds.graph;

/**
 * 带权边
 *
 * @author ywh
 * @since 15/11/2020
 */
public class WeightedEdge implements Comparable<WeightedEdge> {

    private final int v;
    private final int w;
    private final int weight;

    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * @return
     */
    public int getV() {
        return v;
    }

    /**
     * @return
     */
    public int getW() {
        return w;
    }

    /**
     * @return
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param another
     * @return
     */
    @Override
    public int compareTo(WeightedEdge another) {
        return weight - another.weight;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("(%d-%d: %d)", v, w, weight);
    }
}
