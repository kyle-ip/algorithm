package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

/**
 * 完全二叉树的节点个数
 * [树] [二分查找]
 * 
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * 示例 1：
 *      输入：root = [1,2,3,4,5,6]
 *      输出：6
 * 示例 2：
 *      输入：root = []
 *      输出：0
 * 示例 3：
 *      输入：root = [1]
 *      输出：1
 * 提示：
 *      树中节点的数目范围是[0, 5 * 10^4]
 *      0 <= Node.val <= 5 * 104
 *      题目数据保证输入的树是 完全二叉树
 * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
 * 
 * @author ywh
 * @since 4/26/2021
 */
public class LeetCode222 {

    /**
     * 暴力求解
     *
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return countNodes2(root.left) + countNodes2(root.right) + 1;
    }

    /**
     * 完全二叉树最底层节点个数可能为 [1, 2^h]，则总节点个数为 [2^h, 2^(h+1)-1]。
     * 二分查找：根据节点个数范围的上下界得到当前需要判断的节点个数 k，如果第 k 个节点存在，则节点个数一定大于或等于 k，
     * 如果第 k 个节点不存在，则节点个数一定小于 k，由此可以将查找的范围缩小一半，直到得到节点个数。
     *
     * Time: O(log2(n)), Space: O(1)
     *
     * @param root
     * @return
     */
    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 统计层数：
        int h = 0;
        TreeNode node = root;
        for (; node.left != null; h++, node = node.left);

        // 在最下层进行二分查找，高度为 h 的满二叉树最下层的右半边的下标范围：[level*2, (level+1)*2-1]
        int low = 1 << h, high = (1 << (h + 1)) - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (exists(root, h, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * 在树的第 h 层，判断第 k 号元素是否存在
     *
     * @param root
     * @param h
     * @param k
     * @return
     */
    public boolean exists(TreeNode root, int h, int k) {
        TreeNode node = root;
        //     1            h = 0
        //    / \
        //   2   3          h = 1
        //  / \  /
        // 4  5 6           h = 2
        // 最下层 4、5、6、(7) 的二进制表示为 100、101、110、111，从第二位开始，0 表示向左，1 表示向右。
        // 比如 4 从第二位开始是 00，表示从根节点向左再向左；5 从第二位开始是 01，表示从根节点向左再向右，以此类推。
        // bits 从 1 << (h - 1) 开始，每轮循环右移一位，每次判断最后一位，与 k 相与，根据所得的值决定向左/右走。
        for (int bits = 1 << (h - 1); node != null && bits > 0; bits >>= 1) {
            // 判断序号为 k 的节点相对于 node 的位置，如果
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node != null;
    }
}
