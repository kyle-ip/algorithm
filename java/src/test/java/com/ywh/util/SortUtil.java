package com.ywh.util;

import java.util.Collections;
import java.util.List;

/**
 * @author ywh
 * @since 23/11/2019
 */
public class SortUtil {

    // 常见类型排序
    @SuppressWarnings("unchecked")
    public static <T> List<T> sort(List<T> list) {
        if (list == null || list.isEmpty()) return list;
        T e = list.get(0);
        if (e instanceof Integer) {
            Collections.sort((List<Integer>) list);
        } else if (e instanceof String) {
            Collections.sort((List<String>) list);
        }
        return list;
    }
}
