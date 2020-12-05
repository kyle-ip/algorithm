package com.ywh.problem.leetcode.hard;

import com.ywh.ds.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 恢复二叉搜索树
 * [树] [DFS]
 *
 * @author ywh
 * @since 2020/12/5
 */
public class LeetCode89 {

    /**
     * 中序遍历
     * Time: O(n), Space: O(H)
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, prev = null;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                // 中序遍历 BST，即从小到大遍历，上一节点不为空则必然小于当前节点，否则错位（可能是 root、prev 其一或其二）。
                if (prev != null && root.val < prev.val) {

                    // 相邻错位：
                    //          break
                    //            ↓
                    // 1 3 2 4 5 [ ]
                    //   x y

                    // 隔位错位：
                    //                           break
                    //                             ↓
                    // 1 4 3 [2] 5    =>    1 4 3 [2] 5
                    //   x y                  x    y
                    y = root;
                    if (x == null) {
                        x = prev;
                    }
                    // 找到第二个错位节点。
                    else {
                        break;
                    }
                }
                prev = root;
                root = root.right;
            }
        }
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    /**
     * TODO 暂时未理解
     * Time: O(n), Space: O(1)
     *
     * @param root
     */
    public void recoverTreeMorris(TreeNode root) {
        TreeNode x = null, y = null, pred = null, predecessor;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) {
                        x = pred;
                    }
                }
                pred = root;
                root = root.right;
            }
        }
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    /**
     * Time: O(n), Space: O(n)
     * @param root
     */
    public void recoverTree2(TreeNode root) {
        List<Integer> nums = new ArrayList<Integer>();
        inorder(root, nums);
        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }

    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int[] findTwoSwapped(List<Integer> nums) {
        int n = nums.size();
        int x = -1, y = -1;
        for (int i = 0; i < n - 1; ++i) {
            if (nums.get(i + 1) < nums.get(i)) {
                y = nums.get(i + 1);
                if (x == -1) {
                    x = nums.get(i);
                } else {
                    break;
                }
            }
        }
        return new int[]{x, y};
    }

    public void recover(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0) {
                    return;
                }
            }
            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
        }
    }
}
