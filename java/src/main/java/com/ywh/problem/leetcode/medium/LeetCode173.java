package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉搜索树迭代器
 * [树] [栈] [设计]
 * 
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 *      BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
 *          指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 *      boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 *      int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 * 示例：
 *      输入
 *          ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 *          [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 *      输出
 *          [null, 3, 7, true, 9, true, 15, true, 20, false]
 *      解释
 *          BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 *          bSTIterator.next();    // 返回 3
 *          bSTIterator.next();    // 返回 7
 *          bSTIterator.hasNext(); // 返回 True
 *          bSTIterator.next();    // 返回 9
 *          bSTIterator.hasNext(); // 返回 True
 *          bSTIterator.next();    // 返回 15
 *          bSTIterator.hasNext(); // 返回 True
 *          bSTIterator.next();    // 返回 20
 *          bSTIterator.hasNext(); // 返回 False
 * 提示：
 *      树中节点的数目在范围 [1, 105] 内
 *      0 <= Node.val <= 106
 *      最多调用 105 次 hasNext 和 next 操作
 * 进阶：
 *      你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h 是树的高度。
 *
 * @author ywh
 * @since 2020/9/4/004
 */
public class LeetCode173 {
    public static class BSTIterator {

        /**
         * 辅助栈（参考中序遍历 {@link LeetCode94}）
         */
        private final Deque<TreeNode> stack = new LinkedList<>();

        /**
         * 把当前节点及其左节点迭代推入栈中。
         *
         * @param node
         */
        private void pushLeft(TreeNode node) {
            for (; node != null; node = node.left) {
                stack.push(node);
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
