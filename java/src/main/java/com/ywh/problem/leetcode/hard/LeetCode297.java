package com.ywh.problem.leetcode.hard;

import com.ywh.ds.tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的序列化与反序列化
 * [树] [设计]
 *
 * @author ywh
 * @since 24/11/2020
 */
public class LeetCode297 {

    /**
     *
     * @param root
     * @param str
     * @return
     */
    public String serialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += root.val + ",";
            str = serialize(root.left, str);
            str = serialize(root.right, str);
        }
        return str;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
         return serialize(root, "");
    }

    /**
     *
     * @param list
     * @return
     */
    public TreeNode deserialize(List<String> list) {
        if ("None".equals(list.get(0))) {
            list.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = deserialize(list);
        root.right = deserialize(list);
        return root;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        return deserialize(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    /**
     * 括号表示编码 + 递归下降解码
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public String serialize2(TreeNode root) {
        if (root == null) {
            return "X";
        }
        String l = "(" + serialize2(root.left) + ")";
        String r = "(" + serialize2(root.right) + ")";
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
    public TreeNode deserialize2(String data) {
        int[] ptr = {0};
        return parse(data, ptr);
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
