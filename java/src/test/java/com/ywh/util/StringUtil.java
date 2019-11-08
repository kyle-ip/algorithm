package com.ywh.util;


import java.util.Arrays;

/**
 * 字符串工具类
 *
 * @author ywh
 * @since 2/13/2019
 */
public class StringUtil {

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
        if (!str.contains(",")) {
            return new int[]{Integer.parseInt(str)};
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

}
