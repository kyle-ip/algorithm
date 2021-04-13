package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树中第 K 小的元素
 * [树] [二分查找]
 *
 * @author ywh
 * @since 2020/12/11/011
 */
public class LeetCode230 {

    /**
     *
     * @param root
     * @param arr
     * @return
     */
    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) {
            return arr;
        }
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    /**
     * 递归解法
     */
    public int kthSmallest1(TreeNode root, int k) {
        return inorder(root, new ArrayList<>()).get(k - 1);
    }

    /**
     * 迭代解法
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
//        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        List<Integer> list = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
//                if (maxHeap.size() < k) {
//                    maxHeap.add(root.val);
//                } else if (maxHeap.peek() > root.val) {
//                    maxHeap.poll();
//                    maxHeap.add(root.val);
//                }
                list.add(root.val);
                root = root.right;
            }
        }
        System.out.println(list);
        return list.get(k);
    }

}
