package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

/**
 * 打家劫舍 III
 * [树] [深度优先搜索]
 * 
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 示例 1:
 *      输入: [3,2,3,null,3,null,1]
 *
 *           3
 *          / \
 *         2   3
 *          \   \
 *           3   1
 *      输出: 7
 *      解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *      输入: [3,4,5,1,3,null,1]
 *           3
 *          / \
 *         4   5
 *        / \   \
 *       1   3   1
 *      输出: 9
 *      解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * 
 * @author ywh
 * @since 2021/2/8/008
 */
public class LeetCode337 {

    /**
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] ret = dfs(root);
        return Math.max(ret[0], ret[1]);
    }

    /**
     *
     * @param root
     * @return
     */
    private int[] dfs(TreeNode root) {
        if (root == null) {
            // 第一位表示当前节点未被取，第二位表示当前节点被取。
            return new int[]{0, 0};
        }
        int[] left = dfs(root.left), right = dfs(root.right);

        // 当前节点没有被打劫（取其两个分支两种情况较大者之和）、当前节点被打劫（取当前节点，以及左右节点此前未取的状态）。
        int x0 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]), x1 = root.val + left[0] + right[0];

        return new int[]{x0, x1};
    }
}
