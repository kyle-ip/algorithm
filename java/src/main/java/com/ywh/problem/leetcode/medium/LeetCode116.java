package com.ywh.problem.leetcode.medium;

/**
 * 填充每个节点的下一个右侧节点指针
 * [树] [DFS] [BFS]
 * 
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *      struct Node {
 *        int val;
 *        Node *left;
 *        Node *right;
 *        Node *next;
 *      }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 进阶：
 *      你只能使用常量级额外空间。
 *      使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * @author ywh
 * @since 30/11/2019
 */
public class LeetCode116 {

    static class Node {
        public int val;

        public Node left, right, next;
    }

    /**
     * Time: O(n), Space: O(log(n))
     *
     * @param root
     * @return
     */
    public Node connectRecursive(Node root) {
        if (root == null || root.left == null) {
            return root;
        }
        // 先连接左右子树：[6] -> [8]。
        //     [2]
        //    /   \
        // [6] --> [8]
        root.left.next = root.right;

        // 如当前节点已跨子树连接，则将其右节点与其对面的左节点连接：[8] -> [10]。
        //             2
        //     [2]   ----->    [4]
        //    /   \           /   \
        // [6] --> [8] -> [10]     [12]
        //      1      3
        if (root.next != null) {
            root.right.next = root.next.left;
        }

        // 递归处理左右子树。
        connectRecursive(root.left);
        connectRecursive(root.right);
        return root;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param root
     * @return
     */
    public Node connectIterative(Node root) {
        if (root == null) {
            return null;
        }

        Node left = root, p;

        // 逐层处理
        while (left.left != null) {
            // [2]
            p = left;
            while (p != null) {
                //     [2]
                //    /   \
                // [6] --> [8]
                p.left.next = p.right;

                // [2] -> [4]
                if (p.next != null) {
                    //     [2]   ----->    [4]
                    //    /   \           /   \
                    // [6] --> [8] -> [10]     [12]
                    p.right.next = p.next.left;
                }

                // 处理同层的右边节点：[4]
                p = p.next;
            }
            // 处理左节点（下一层）。
            left = left.left;
        }
        return root;
    }
}
