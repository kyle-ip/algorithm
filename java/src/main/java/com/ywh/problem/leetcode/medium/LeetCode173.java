package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

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
         * 辅助栈，存放所有左边的元素
         */
        private final Stack<TreeNode> stack = new Stack<>();

        /**
         * 把当前节点及其左节点迭代推入栈中
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
         * 把根节点及其左节点迭代入栈
         *
         * @param root
         */
        public BSTIterator(TreeNode root) {
            pushLeft(root);
        }

        /**
         * Time(avg): O(1), Space(avg): O(h)
         *
         * @return
         */
        public int next() {
            // stack:
            // [10, 5]

            // tree:
            //         10
            //        /  \
            //       5    11
            //        \     \
            //         7     12
            //        /
            //       6

            // 从栈顶取出元素，即最左边的节点（5）。
            TreeNode node = stack.pop();
            // 对于最左边的节点的右节点，将其左节点迭代入栈（7、6，所以下次从 6 开始取）。
            pushLeft(node.right);
            // 返回最左边的节点（5）。
            return node.val;

            // stack:
            // [10, 7, 6]
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
