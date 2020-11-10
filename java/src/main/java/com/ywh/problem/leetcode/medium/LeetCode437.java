package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树中和为给定值的路径数量
 * [树]
 *
 * @author ywh
 * @since 05/01/2020
 */
public class LeetCode437 {

    /**
     * 计算从 root 出发，路径和为 sum 的路径数量
     *
     * @param root
     * @param sum
     * @return
     */
    private int pathFrom(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int cnt = 0;

        // 当前节点值即为路径和
        if (root.val == sum) {
            ++cnt;
        }
        // 节点值可能存在负数，因此无论截至目前是否已找到和为 sum 的路径，都继续向左右子树遍历
        cnt += pathFrom(root.left, sum - root.val);
        cnt += pathFrom(root.right, sum - root.val);
        return cnt;
    }

    /**
     * 递归处理所有节点
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSumRecursive(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathFrom(root, sum) + pathSumRecursive(root.left, sum) + pathSumRecursive(root.right, sum);
    }

    /**
     * 前序遍历二叉树，并记录遍历过程中的路径和及其出现次数
     *
     * @param root          当前节点
     * @param cur           前缀路径和
     * @param sum           目标值
     * @param prefixSum
     * @return              路径和为 sum 的路径数量
     */
    private int dfs(TreeNode root, int cur, int sum, Map<Integer, Integer> prefixSum) {
        if (root == null) {
            return 0;
        }
        cur += root.val;

        // 记录当前节点的前缀路径和
        prefixSum.put(cur, prefixSum.getOrDefault(cur, 0) + 1);

        // 取出前缀路径和与目标值差值的数量
        int cnt = prefixSum.getOrDefault(cur - sum, 0);
        // 在左右子树中继续查找，并添加到 cnt 上
        cnt += dfs(root.left, cur, sum, prefixSum);
        cnt += dfs(root.right, cur, sum, prefixSum);

        // 退递归后数量 - 1，回溯到上一个节点处理
        prefixSum.put(cur, prefixSum.get(cur) - 1);
        return cnt;

    }

    /**
     * 使用哈希表存放前缀路径和（从根节点到当前节点的路径和）及其出现的次数
     * TODO 暂时未理解
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSumPrefixSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        return dfs(root, 0, sum, prefixSum);
    }
}
