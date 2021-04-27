package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 最长递增子序列的个数
 * [动态规划]
 *
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * 示例 1:
 *      输入: [1,3,5,4,7]
 *      输出: 2
 *      解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *      输入: [2,2,2,2,2]
 *      输出: 5
 *      解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 *
 * @author ywh
 * @since 2021/1/6/006
 */
public class LeetCode673 {

    /**
     * 动态规划解法
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }

        // len[i] 表示截至 i 的最长序列长度，cnt[i] 表示出现该长度的序列的个数。
        int[] len = new int[n], cnt = new int[n];
        Arrays.fill(cnt, 1);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                // 不递增，跳过。
                if (nums[j] >= nums[i]) {
                    continue;
                }
                // 递增：
                // 更新 len[i]、counts[i]：使得 len[i] 始终比 len[j] 长 1，且数量相等（是同一个递增序列 s[0:i]）。
                if (len[j] >= len[i]) {
                    len[i] = len[j] + 1;
                    cnt[i] = cnt[j];
                }
                // 如果 [0, i] 中递增的长度比 [0, j] 大 1，表示算上 i 后又找到 cnt[j] 种最长递增序列的凑法。
                else if (len[j] + 1 == len[i]) {
                    cnt[i] += cnt[j];
                }
            }
        }

        // 找到最大值，并在 len 中统计该最大值出现的个数，返回。
        int maxLen = 0, ret = 0;
        for (int l : len) {
            maxLen = Math.max(maxLen, l);
        }
        for (int i = 0; i < n; ++i) {
            if (len[i] == maxLen) {
                ret += cnt[i];
            }
        }
        return ret;
    }

    /**
     * 线段树解法
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int min = nums[0], max = nums[0];
        for (int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        Node root = new Node(min, max);
        for (int num: nums) {
            Value v = query(root, num-1);
            insert(root, num, new Value(v.length + 1, v.count));
        }
        return root.val.count;
    }

    /**
     *
     * @param v1
     * @param v2
     * @return
     */
    public Value merge(Value v1, Value v2) {
        if (v1.length == v2.length) {
            if (v1.length == 0) {
                return new Value(0, 1);
            }
            return new Value(v1.length, v1.count + v2.count);
        }
        return v1.length > v2.length ? v1 : v2;
    }

    /**
     *
     * @param node
     * @param key
     * @param val
     */
    public void insert(Node node, int key, Value val) {
        if (node.rangeLeft == node.rangeRight) {
            node.val = merge(val, node.val);
            return;
        } else if (key <= node.getRangeMid()) {
            insert(node.getLeft(), key, val);
        } else {
            insert(node.getRight(), key, val);
        }
        node.val = merge(node.getLeft().val, node.getRight().val);
    }

    /**
     *
     * @param node
     * @param key
     * @return
     */
    public Value query(Node node, int key) {
        if (node.rangeRight <= key) {
            return node.val;
        } else if (node.rangeLeft > key) {
            return new Value(0, 1);
        } else {
            return merge(query(node.getLeft(), key), query(node.getRight(), key));
        }
    }

    static class Node {
        int rangeLeft, rangeRight;
        Node left, right;
        Value val;
        public Node(int start, int end) {
            rangeLeft = start;
            rangeRight = end;
            left = null;
            right = null;
            val = new Value(0, 1);
        }
        public int getRangeMid() {
            return rangeLeft + (rangeRight - rangeLeft) / 2;
        }
        public Node getLeft() {
            if (left == null) {
                left = new Node(rangeLeft, getRangeMid());
            }
            return left;
        }
        public Node getRight() {
            if (right == null) {
                right = new Node(getRangeMid() + 1, rangeRight);
            }
            return right;
        }
    }

    static class Value {
        int length, count;
        public Value(int len, int ct) {
            length = len;
            count = ct;
        }
    }
}
