package com.ywh.util;


import com.ywh.ds.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author ywh
 * @since 2/13/2019
 */
public class StringUtil {

    /**
     * 字符串转二叉树
     *
     * @param str
     * @return
     */
    public static TreeNode strToTree(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        for (String s : str.split(",")) {
            if ("null".equals(s)) {
                list.add(null);
            } else {
                list.add(Integer.valueOf(s));
            }
        }
        return TreeUtil.buildTree(list);
    }

    /**
     * 字符串转整形数组
     *
     * @param str
     * @return
     */
    public static int[] strToIntArray(String str) {
        if (str == null || str.length() == 0 || "[]".equals(str)) {
            return new int[]{};
        }
        if ("null".equals(str)) {
            return null;
        }

        return Arrays
            .stream(str.split(","))
            .map(String::trim)
            .mapToInt(Integer::parseInt)
            .toArray();
    }

    public static String intArrayToStr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String intArrayToStr(int[] arr, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(arr[i]);
            if (i != len - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 字符串转二维整型列表
     *
     * @param str
     * @return
     */
    public static List<List<Integer>> strTo2DimIntList(String str) {
        List<List<Integer>> ret = new ArrayList<>();

        if (str == null || str.length() == 0 || "null".equals(str)) {
            return ret;
        }
        for (String s : str.split("/")) {
            if ("null".equals(s)) {
                ret.add(null);
            } else if ("".equals(s)) {
                ret.add(new ArrayList<>());
            } else {
                ret.add(strToIntList(s));
            }
        }
        return ret;
    }

    /**
     * 字符串转整型列表
     *
     * @param str
     * @return
     */
    public static List<Integer> strToIntList(String str) {
        List<Integer> ret = new ArrayList<>();

        if (str == null || str.length() == 0 || "null".equals(str)) {
            return ret;
        }
        for (String s : str.split(",")) {
            if ("null".equals(s)) {
                ret.add(null);
            } else {
                ret.add(Integer.valueOf(s));
            }
        }
        return ret;
    }

    public static String[] strToStrArray(String str) {
        List<String> list = strToStrList(str);
        if (list == null) {
            return null;
        }
        String[] ret = new String[list.size()];
        list.toArray(ret);
        return ret;
    }

    /**
     * 把字符串转成字符串列表
     *
     * @param str
     * @return
     */
    public static List<String> strToStrList(String str) {
        return strToStrList(str, "/");
    }

    /**
     * 把字符串转成字符串列表
     *
     * @param str
     * @param splitter
     * @return
     */
    public static List<String> strToStrList(String str, String splitter) {
        List<String> ret = new ArrayList<>();
        if ("null".equals(str.split(splitter)[0])) {
            return null;
        }
        for (String s : str.split(splitter)) {
            if ("null".equals(s)) {
                ret.add(null);
            } else {
                ret.add(s);
            }
        }
        return ret;
    }
}
