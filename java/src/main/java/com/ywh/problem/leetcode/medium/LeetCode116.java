package com.ywh.problem.leetcode.medium;

/**
 * 树节点的 next 指针
 * [树] [DFS] [BFS]
 *
 * @author ywh
 * @since 30/11/2019
 */
public class LeetCode116 {

    static class Node {
        public int val;

        public Node left, right, next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
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
        // 先连接左右子树
//                 ...
//             2
//           /   \
//        [6] -> [8]
        root.left.next = root.right;

        // 如当前节点已跨子树连接，则将其右节点与其对面的左节点连接
//                 ...
//            2     ->    4 -> null
//          /  \        /   \
//        6 -> [8] -> [10] -> 12 -> null
        if (root.next != null) {
            root.right.next = root.next.left;
        }

        // 递归处理左右子树
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

        Node leftNode = root, p;

        // 逐层处理
        while (leftNode.left != null) {
            p = leftNode;
            while (p != null) {
//                     ...
//                     2
//                   /   \
//                [6] -> [8]
                p.left.next = p.right;

                // 2 -> 4
                if (p.next != null) {
//                         ...
//                    2     ->    4 -> null
//                  /  \        /   \
//                6 -> [8] -> [10] -> 12 -> null
                    p.right.next = p.next.left;
                }

                // 处理同层的右边节点
                p = p.next;
            }
            // 处理左节点（下一层）
            leftNode = leftNode.left;
        }
        return root;
    }
}
