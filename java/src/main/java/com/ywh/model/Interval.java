package com.ywh.model;

/**
 * 区间间隔
 *
 * @author ywh
 * @since 2019/10/30
 */
public class Interval {

    public int start;
    public int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
