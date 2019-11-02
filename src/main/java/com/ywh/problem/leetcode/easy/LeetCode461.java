package com.ywh.problem.leetcode.easy;

/**
 * 汉明距离
 * [位操作]
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode461 {

    /**
     * 统计 n 的二进制表示中有多少个 1
     *
     * @param n
     * @return
     */
    int numberOfOne(int n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n &= (n - 1);
        }
        return count;
    }

    /**
     * 汉明距离即两个数的二进制表示中、相异位的个数
     * 先求异或，把相异位都置为1、相同位都置为0，在统计1的个数即可
     * TODO 暂时未理解
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {

        // 异或：相同为 0，不同为 1
        return numberOfOne(x ^ y);
    }

}
