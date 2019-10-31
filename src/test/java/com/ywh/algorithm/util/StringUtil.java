package com.ywh.algorithm.util;


import java.util.Arrays;

/**
 * 字符串工具类
 *
 * @author ywh
 * @since 2/13/2019
 */
public class StringUtil {

    public static int[] stringToIntArray(String str) {
        str = str.trim().substring(1, str.length() - 1).trim();
        if (str.isEmpty()) {
            return new int[]{};
        }
        if (!str.contains(",")) {
            return new int[]{Integer.parseInt(str)};
        }
        return Arrays.stream(str.split(","))
            .map(String::trim)
            .mapToInt(Integer::parseInt).toArray();
    }

}
