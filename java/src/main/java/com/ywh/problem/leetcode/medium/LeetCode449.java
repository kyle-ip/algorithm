package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.ArrayDeque;

/**
 * 序列化和反序列化二叉搜索树
 * [树]
 *
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，
 * 以便稍后在同一个或另一个计算机环境中重建。
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。
 * 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 * 示例 1：
 *      输入：root = [2,1,3]
 *      输出：[2,1,3]
 * 示例 2：
 *      输入：root = []
 *      输出：[]
 * 提示：
 *      树中节点数范围是 [0, 10^4]
 *      0 <= Node.val <= 10^4
 *      题目数据 保证 输入的树是一棵二叉搜索树。
 * 注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。
 *
 * @author ywh
 * @since 4/9/2021
 */
public class LeetCode449 {


    class Codec {

        /**
         *
         * @param bytesStr
         * @return
         */
        public int stringToInt(String bytesStr) {
            int ret = 0;
            for(char b : bytesStr.toCharArray()) {
                ret = (ret << 8) + (int)b;
            }
            return ret;
        }

        /**
         *
         * @param x
         * @return
         */
        public String intToString(int x) {
            char[] bytes = new char[4];
            for(int i = 3; i > -1; --i) {
                bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
            }
            return new String(bytes);
        }

        /**
         *
         * @param root
         * @param sb
         */
        public void postorder(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            postorder(root.left, sb);
            postorder(root.right, sb);
//            sb.append(intToString(root.val));
            sb.append(root.val).append("-");
        }


        /**
         *
         * @param root
         * @return
         */
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            postorder(root, sb);
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }

        /**
         *
         * @param lower
         * @param upper
         * @param nums
         * @return
         */
        public TreeNode deserialize(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
            if (nums.isEmpty()) {
                return null;
            }
            int val = nums.getLast();
            if (val < lower || val > upper) {
                return null;
            }
            nums.removeLast();
            TreeNode root = new TreeNode(val);
            root.right = deserialize(val, upper, nums);
            root.left = deserialize(lower, val, nums);
            return root;
        }

        /**
         *
         * @param data
         * @return
         */
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            ArrayDeque<Integer> nums = new ArrayDeque<>();
//            for(int i = 0; i < (data.length() / 4); ++i) {
//                nums.add(stringToInt(data.substring(4 * i, 4 * i + 4)));
//            }
            for (String s : data.split("-")) {
                nums.add(Integer.valueOf(s));
            }
            return deserialize(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
        }
    }
}
