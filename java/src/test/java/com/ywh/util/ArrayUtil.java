package com.ywh.util;

import java.util.Iterator;
import java.util.List;

/**
 * @author ywh
 * @since 10/11/2019
 */
public class ArrayUtil {

    public static boolean diff(List<Integer> l1, List<Integer> l2) {
        Iterator<Integer> it1 = l1.iterator(), it2 = l2.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next())) {
                return false;
            }
        }
        return !(it1.hasNext() || it2.hasNext());
    }

}
