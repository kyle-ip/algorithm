package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
     * @param result
     */
    private void combine(int n, int k, int start, List<Integer> elem, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(elem));
            return;
        }

        // i 从 start 开始，由于要确保剩余可取数字个数大于等于 k 个，会遍历到 n - k + 1
        // 比如 n = 5，k = 4，即从 1 ~ 5 中取 4 个
        // 所以 i 必须小于等于 n - k + 1 即 2（2 3 4 5），否则当 i 为 3，无法取 4 个
        for (int i = start; i <= n - k + 1; i++) {
            elem.add(i);
            combine(n, k - 1, i + 1, elem, result);
            // 每次递归完成都弹出当前组合中的最后一位，表示向前回溯一位
            elem.remove(elem.size() - 1);
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
        List<Integer> elem = new ArrayList<>();
        combine(n, k, 1, elem, ret);
        return ret;
    }

    /**
     * TODO 暂时未理解
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

        // 用前 n - k + 1 个数字（可作为一个集合的起始元素的数字）创建 n - k + 1 个列表、加入到结果集中
        for (int i = 0; i < n - k + 1; i++) {
            ret.add(Arrays.asList(i));
        }

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
