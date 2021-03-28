package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 路径总和 III
 * [树]
 *
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * 示例：
 *      root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *            10
 *           /  \
 *          5   -3
 *         / \    \
 *        3   2   11
 *       / \   \
 *      3  -2   1
 *      返回 3。和等于 8 的路径有:
 *           1.  5 -> 3
 *           2.  5 -> 2 -> 1
 *           3.  -3 -> 11
 *
 * @author ywh
 * @since 05/01/2020
 */
public class LeetCode437 {

    /**
     * 计算从 root 出发，路径和为 sum 的路径数量。
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
        // 节点值可能存在负数，因此无论截至目前是否已找到和为 sum 的路径，都继续向左右子树遍历。
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
     * 前序遍历二叉树，并记录遍历过程中的路径和及其出现次数。
     *
     * @param root      当前节点
     * @param cur       前缀路径和
     * @param sum       目标值
     * @param prefixSum
     * @return 路径和为 sum 的路径数量
     */
    private int dfs(TreeNode root, int cur, int sum, Map<Integer, Integer> prefixSum) {
        if (root == null) {
            return 0;
        }
        cur += root.val;

        // 取出前缀路径和与目标值差值的数量（表示共有多少条路径可以到达这里）。
        int cnt = prefixSum.getOrDefault(cur - sum, 0);

        // 由于发现一条新路径，其路径和为 cur，更新到 prefixSum。
        prefixSum.put(cur, prefixSum.getOrDefault(cur, 0) + 1);

        // 在左右子树中继续递归查找，并添加到 cnt 上。
        cnt += dfs(root.left, cur, sum, prefixSum) + dfs(root.right, cur, sum, prefixSum);

        // 退递归后数量 -1，回溯到上一个节点处理。
        prefixSum.put(cur, prefixSum.get(cur) - 1);
        return cnt;
    }

    /**
     * 使用哈希表存放前缀路径和（从根节点到当前节点的路径和）及其出现的次数。
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
