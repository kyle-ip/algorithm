package com.ywh.problem.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * 将数组拆分成斐波那契序列
 * [贪心] [回溯] [字符串]
 *
 * @author ywh
 * @since 2020/12/8/008
 */
public class LeetCode842 {

    /**
     *
     * @param ret       由 S 拆分出来的数
     * @param S
     * @param start     开始下标
     * @param sum       前面已添加到 list 中的数值之和
     * @param prev
     * @return
     */
    private boolean backtrack(LinkedList<Integer> ret, String S, int start, long sum, long prev) {
        // 遍历到最后，序列中至少有三个数。
        if (start == S.length()) {
            return ret.size() > 2;
        }
        long cur = 0;
        for (int i = start; i < S.length(); i++) {
            // 剪枝 1：拆分出来的数不为 0，则不能以 0 开头。
            if (i > start && S.charAt(start) == '0') {
                return false;
            }
            // 剪枝 2：拆分的数不能越界（Integer.MAX_VALUE）。
            cur = cur * 10 + S.charAt(i) - '0';
            if (cur > Integer.MAX_VALUE) {
                return false;
            }

            // 从第 3 个数开始判断拆分出的数是否等于前 2 个数的和，不满足条件不进行拆分。
            if (ret.size() >= 2) {
                // 新拆分的数小于前面两数之和，继续取下一位累计组成新数。
                if (cur < sum) {
                    continue;
                }
                // 剪枝 3：新拆分的数大于前面两数之和，不符合条件，无需继续拆分。
                else if (cur > sum) {
                    return false;
                }
            }

            // 递归回溯求解。
            ret.add((int) cur);
            if (backtrack(ret, S, i + 1, prev + cur, cur)) {
                return true;
            }
            ret.removeLast();
        }
        return false;
    }

    /**
     *
     *
     * @param S
     * @return
     */
    public List<Integer> splitIntoFibonacci(String S) {
        LinkedList<Integer> ret = new LinkedList<>();
        backtrack(ret, S, 0, 0, 0);
        return ret;
    }
}
