package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 数字组合
 * [回溯]
 *
 * @author ywh
 * @since 07/04/2020
 */
public class LeetCode77 {

    /**
     *
     * @param n
     * @param k         剩余待凑元素个数
     * @param start     开始取数的位置
     * @param elem
     * @param ret
     */
    private void combine(int n, int k, int start, LinkedList<Integer> elem, List<List<Integer>> ret) {
        if (k == 0) {
            ret.add(new ArrayList<>(elem));
            return;
        }

        // 从 start 开始，取 [1, n] 的后 k 个元素，所以边界条件为 [start, n-k+1]。
        // 比如 [1, 2, 3, 4, 5]
        //            |<----->|
        //              k = 3
        for (int i = start; i <= n - k + 1; i++) {
            elem.add(i);
            combine(n, k - 1, i + 1, elem, ret);
            elem.removeLast();
        }
    }

    /**
     *
     * Time: O(k*C(n, k)), Space: O(k)
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combineRecursive(int n, int k) {
        if (k > n) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();
        combine(n, k, 1, new LinkedList<>(), ret);
        return ret;
    }

    /**
     * Time: O(k*C(n, k)), Space: O(k*C(n, k))
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combineIterative(int n, int k) {
        if (k > n) {
            return Collections.emptyList();
        }
        List<List<Integer>> ret = new ArrayList<>();

        // 用前 n-k+1 个数字（可作为一个集合的起始元素的数字）创建 n-k+1 个列表、加入到结果集中。
        // 比如  n == 5，k == 4，则 ret：
        // [
        //      [1],
        //      [2],
        //      [3],
        //      [4]
        // ]
        for (int i = 0; i < n - k + 1; i++) {
            ret.add(Collections.singletonList(i));
        }

        // 结果集中的每个集合剩余 k-2+1 个元素待补全。
        for (int i = 2; i <= k; i++) {
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> elem : ret) {
                int lastNum = elem.get(elem.size() - 1);
                for (int j = lastNum + 1; j <= n - k + 1; j++) {
                    List<Integer> copy = new ArrayList<>(elem);
                    copy.add(j);
                    tmp.add(copy);
                }
            }
            ret = tmp;
        }
        return ret;
    }
}
