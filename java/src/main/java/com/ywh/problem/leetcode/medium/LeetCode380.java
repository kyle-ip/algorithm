package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 支持随机获取元素的集合
 *
 * @author ywh
 * @since 11/12/2019
 */
public class LeetCode380 {

    public static class RandomizedSet {

        private final Map<Integer, Integer> map = new HashMap<>();

        private final List<Integer> list = new ArrayList<>();

        private final Random RANDOM = new Random();

        /**
         * 插入元素：插入前先判断元素是否存在于 map 中，不存在再执行插入；
         * 在 list 中存储元素的同时，把该元素及其对应的下标组成的键值对存储到 map 中
         *
         * @param val
         * @return
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            list.add(val);
            map.put(val, list.size()-1);
            return true;
        }

        /**
         * 删除元素：从 map 中取出元素在 list 中的下标，把 list 的最后一个元素覆盖到该下标的位置上；
         * 然后从 map 中删除元素、从 list 中删除最后一位元素即可
         *
         * @param val
         * @return
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int idx = map.get(val);
            int lastVal = list.get(list.size()-1);
            list.set(idx, lastVal);
            list.remove(list.size()-1);
            map.put(lastVal, idx);
            map.remove(val);
            return true;
        }

        /**
         * 随机获取元素：从 list 大小范围内任意取出一个元素
         *
         * @return
         */
        public int getRandom() {
            return list.get(RANDOM.nextInt(list.size()));
        }
    }

}
