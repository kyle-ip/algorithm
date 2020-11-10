package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.Stack;

/**
 * 二叉搜索树迭代器
 * [树] [栈] [设计]
 *
 * @author ywh
 * @since 2020/9/4/004
 */
public class LeetCode173 {
    public class BSTIterator {

        /**
         * 辅助栈（参考中序遍历 {@link LeetCode94}）
         */
        private final Stack<TreeNode> stack = new Stack<>();

        /**
         * 把当前节点及其左节点迭代推入栈中。
         *
         * @param node
         */
        private void pushLeft(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * 创建迭代器时把根节点及其左节点入栈。
         *
         * @param root
         */
        public BSTIterator(TreeNode root) {
            // tree:
            //         10
            //        /  \
            //       5    11
            //        \     \
            //         7     12
            //        /
            //       6

            // stack:
            // [10, 5]
            pushLeft(root);
        }

        /**
         * 从栈中取元素，每个元素出栈时，右节点及其左子树迭代入栈。
         *
         * Time(avg): O(1), Space(avg): O(h)
         *
         * @return
         */
        public int next() {

            // 从栈顶取出元素，即最左边的节点 [5]，此时栈：[10]。
            TreeNode node = stack.pop();

            // 取该节点 [5] 的右节点 [7]，并将其左子树入栈 [7]、[6]，此时栈：[10, 7, 6]。
            pushLeft(node.right);

            // 返回节点 [5]。
            return node.val;
        }

        /**
         * Time(avg): O(1), Space(avg): O(h)
         *
         * @return
         */
        public boolean hasNext() {
            return stack.size() > 0;
        }
    }
}
