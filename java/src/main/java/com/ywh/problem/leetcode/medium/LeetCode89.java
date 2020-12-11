package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码
 * [回溯]
 *
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * 格雷编码序列必须以 0 开头。
 *
 * 示例 1：
 *      输入: 2
 *      输出: [0,1,3,2]
 *      解释:
 *          00 - 0
 *          01 - 1
 *          11 - 3
 *          10 - 2
 *      对于给定的 n，其格雷编码序列并不唯一。
 *      例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 *          00 - 0
 *          10 - 2
 *          11 - 3
 *          01 - 1
 * 示例 2：
 *      输入: 0
 *      输出: [0]
 *      解释: 我们定义格雷编码序列必须以 0 开头。
 *           给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *           因此，当 n = 0 时，其格雷编码序列为 [0]。
 *
 * @author ywh
 * @since 2020/12/11/011
 */
public class LeetCode89 {

    /**
     * TODO 暂时未理解
     *
     * 设 n 阶格雷码集合为 G(n)，则 G(n+1) 阶格雷码为：
     *      在 G(n) 格雷码每个元素二进制形式前面添加 0，得到 G'(n)（因为最高位前默认为 0，因此 G'(n) = G(n)）；
     *      设 G(n) 集合倒序（镜像）为 R(n)，给 R(n) 每个元素二进制形式前面添加 1，得到 R'(n)；
     *      G(n+1) = G'(n)∪R'(n)，拼接两个集合即可得到下一阶格雷码。
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>() {{ add(0); }};
        int head = 1;

        // n 位格雷编码序列。
        for (int i = 0; i < n; i++) {
            // 倒序遍历 G(n)，依次求得 R'(n) 各元素添加到 ret 尾端，得到 G(n+1)。
            for (int j = ret.size() - 1; j >= 0; j--) {
                ret.add(head + ret.get(j));
            }
            head <<= 1;
        }
        return ret;
    }
}
