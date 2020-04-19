package com.ywh.problem.leetcode.easy;

import java.util.*;

/**
 * 设计一个类求和为给定值的两个数
 * [哈希表] [设计]
 *
 * @author ywh
 * @since 19/04/2020
 */
public class LeetCode170 {

    public static class TwoSumIIIList {

        private final List<Integer> data = new ArrayList<>();

        /**
         * Time: O(1)
         *
         * @param number
         */
        public void add(int number) {
            data.add(number);
        }

        /**
         * Time: O(n)
         *
         * @param value
         * @return
         */
        public boolean find(int value) {
            Set<Integer> set = new HashSet<>();
            for (int num : data) {
                if (set.contains(value - num)) {
                    return true;
                }
                set.add(num);
            }
            return false;
        }
    }

    public static class TwoSumIIIHashMap {

        // 可能有重复元素，所以 value 为元素出现的次数
        private final Map<Integer, Integer> map = new HashMap<>();

        /**
         * Time: O(1)
         *
         * @param number
         */
        public void add(int number) {
            int cnt = map.getOrDefault(number, 0);
            map.put(number, cnt + 1);
        }

        /**
         * Time: O(n)
         *
         * @param value
         * @return
         */
        public boolean find(int value) {
            for (Map.Entry<Integer, Integer> elem : map.entrySet()) {
                int num = elem.getKey(), cnt = elem.getValue();
                int diff = value - num;
                if ((diff != num && map.containsKey(diff)) || (diff == num && cnt > 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class TwoSumIIIO1Find {

        // 存放元素
        private final List<Integer> data = new ArrayList<>();

        // 存放 data 中组成的两数之和
        private final Set<Integer> sumSet = new HashSet<>();

        /**
         * Time: O(n)
         *
         * @param number
         */
        public void add(int number) {
            for (int num : data) {
                sumSet.add(num + number);
            }
            data.add(number);
        }

        /**
         * Time: O(1)
         *
         * @param value
         * @return
         */
        public boolean find(int value) {
            return sumSet.contains(value);
        }
    }

}
