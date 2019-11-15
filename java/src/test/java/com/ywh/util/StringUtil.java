package com.ywh.util;


import com.ywh.model.TreeNode;

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
        if (arr == null || arr.length == 0) {
            return "";
        }
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
        if (arr == null || arr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(arr[i]);
            if (i != len - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static List<Integer> strToIntList(String str) {
        List<Integer> res = new ArrayList<>();

        if (str == null || str.length() == 0 || "null".equals(str)) {
            return res;
        }

        for (String s : str.split(",")) {
            if ("null".equals(s)) {
                res.add(null);
            } else {
                res.add(Integer.valueOf(s));
            }
        }
        return res;
    }

    /**
     * 把字符串转成字符串数组
     *
     * @param str
     * @return
     */
    public static List<String> strToStrList(String str) {
        return strToStrList(str, "/");
    }

    /**
     * 把字符串转成字符串数组
     *
     * @param str
     * @param splitter
     * @return
     */
    public static List<String> strToStrList(String str, String splitter) {
        List<String> res = new ArrayList<>();
        for (String s : str.split(splitter)) {
            if ("null".equals(s)) {
                res.add(null);
            } else {
                res.add(s);
            }
        }
        return res;
    }
}
