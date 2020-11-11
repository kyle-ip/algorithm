package com.ywh.ds.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 无向图节点
 *
 * @author ywh
 * @since 27/11/2019
 */
public class UGN {

    public int val;

    public List<UGN> neighbors;

    public UGN(int x) {
        val = x;
        neighbors = new LinkedList<>();
    }
}
