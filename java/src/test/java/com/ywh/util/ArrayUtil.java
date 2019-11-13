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

    /**
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static boolean diff(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return true;
        }
        if (nums1 == null || nums2 == null) {
            return false;
        }
        if (nums1.length != nums2.length) {
            return false;
        }
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] != nums2[i]) {
                return false;
            }
        }
        return true;
    }

}
