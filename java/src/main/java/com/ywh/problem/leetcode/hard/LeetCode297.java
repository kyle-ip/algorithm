package com.ywh.problem.leetcode.hard;

import com.ywh.ds.tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 二叉树的序列化与反序列化
 * [树] [设计]
 * 
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 示例 1：
 *      输入：root = [1,2,3,null,null,4,5]
 *      输出：[1,2,3,null,null,4,5]
 * 示例 2：
 *      输入：root = []
 *      输出：[]
 * 示例 3：
 *      输入：root = [1]
 *      输出：[1]
 * 示例 4：
 *      输入：root = [1,2]
 *      输出：[1,2]
 * 提示：
 *      树中结点数在范围 [0, 10^4] 内
 *      -1000 <= Node.val <= 1000
 * 
 * @author ywh
 * @since 24/11/2020
 */
public class LeetCode297 {

    public class Codec1 {

        /**
         *
         * @param root
         * @param sb
         * @return
         */
        public StringBuilder serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("_,");
            } else {
                sb.append(root.val + ",");
                serialize(root.left, sb);
                serialize(root.right, sb);
            }
            return sb;
        }

        /**
         * Time: O(n), Space: O(n)
         *
         * @param root
         * @return
         */
        public String serialize(TreeNode root) {
            return serialize(root, new StringBuilder()).toString();
        }

        /**
         *
         * @param list
         * @return
         */
        public TreeNode deserialize(LinkedList<String> list) {
            String s = list.removeFirst();
            if ("_".equals(s)) {
                return null;
            }
            return new TreeNode(Integer.parseInt(s), deserialize(list), deserialize(list));
        }

        /**
         * Time: O(n), Space: O(n)
         *
         * @param data
         * @return
         */
        public TreeNode deserialize(String data) {
//            List<String> list = new ArrayList<>();
//            Collections.addAll(list, data.split(","));
//            return deserialize(list);
            return deserialize(new LinkedList<>(Arrays.asList(data.split(","))));
        }
    }

    public class Codec2 {

        /**
         * 括号表示编码 + 递归下降解码
         *
         * Time: O(n), Space: O(n)
         *
         * @param root
         * @return
         */
        public String serialize(TreeNode root) {
            if (root == null) {
                return "X";
            }
            String l = "(" + serialize(root.left) + ")";
            String r = "(" + serialize(root.right) + ")";
            return  l + root.val + r;
        }

        /**
         * 括号表示编码 + 递归下降解码
         *
         * Time: O(n), Space: O(n)
         *
         * @param data
         * @return
         */
        public TreeNode deserialize(String data) {
            return parse(data, new int[]{0});
        }

        /**
         *
         * @param data
         * @param ptr
         * @return
         */
        public TreeNode parse(String data, int[] ptr) {
            if (data.charAt(ptr[0]) == 'X') {
                ++ptr[0];
                return null;
            }
            TreeNode cur = new TreeNode(0);
            cur.left = parseSubtree(data, ptr);

            int x = 0, sgn = 1;
            if (!Character.isDigit(data.charAt(ptr[0]))) {
                sgn = -1;
                ++ptr[0];
            }
            while (Character.isDigit(data.charAt(ptr[0]))) {
                x = x * 10 + data.charAt(ptr[0]++) - '0';
            }
            cur.val = x * sgn;

            cur.right = parseSubtree(data, ptr);
            return cur;
        }

        /**
         *
         * @param data
         * @param ptr
         * @return
         */
        public TreeNode parseSubtree(String data, int[] ptr) {
            ++ptr[0];
            TreeNode subtree = parse(data, ptr);
            ++ptr[0];
            return subtree;
        }
    }

}
