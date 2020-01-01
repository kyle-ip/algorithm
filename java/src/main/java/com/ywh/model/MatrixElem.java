package com.ywh.model;

/**
 * 矩阵元素
 *
 * @author ywh
 * @since 01/01/2020
 */
public class MatrixElem implements Comparable<MatrixElem> {
    public int row, col, val;

    public MatrixElem(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }

    @Override
    public int compareTo(MatrixElem o) {
        return this.val - o.val;
    }

}
