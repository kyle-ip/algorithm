package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * [栈] [树]
 * 
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * 示例 1：
 *      输入：root = [1,null,2,3]
 *      输出：[1,3,2]
 * 示例 2：
 *      输入：root = []
 *      输出：[]
 * 示例 3：
 *      输入：root = [1]
 *      输出：[1]
 * 示例 4：
 *      输入：root = [1,2]
 *      输出：[2,1]
 * 示例 5：
 *      输入：root = [1,null,2]
 *      输出：[1,2]
 * 提示：
 *      树中节点数目在范围 [0, 100] 内
 *      -100 <= Node.val <= 100
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode94 {

    /**
     *
     * @param root
     * @param ret
     */
    public void inorderTraversal(TreeNode root, List<Integer> ret) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, ret);
        ret.add(root.val);
        inorderTraversal(root.right, ret);
    }

    /**
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalRecursive2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        inorderTraversal(root, ret);
        return ret;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 取左子树的节点列表。
        List<Integer> left = inorderTraversalRecursive(root.left);
        // 取右子树的节点列表
        List<Integer> right = inorderTraversalRecursive(root.right);
        // 在左子树的节点后面添加根节点、再依次添加所有右子树的节点。
        left.add(root.val);
        left.addAll(right);
        return left;
    }

    /**
     * Time: O(n). Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> ret = new ArrayList<>();

        // 遍历路径：
        //      1. 首先从 [3] 向左出发，一直到 [1] 的左节点为空，沿途把经过的节点入栈：[3, 1]；
        //      2. 直到遇到空节点，从栈中弹出 [1]，取出 [1] 的值后向右走到达 [2]；
        //      3. 由于是递归结构，[2] 也可以视作根，此时处理与 1 相同。
        //
        //     [3]
        //    /   \
        // [1]     [4]
        //    \
        //     [2]

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                ret.add(root.val);
                root = root.right;
            }
        }
        return ret;
    }
}
