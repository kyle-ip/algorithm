package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 常数时间插入、删除和获取随机元素
 * [设计] [数组] [哈希表]
 * 
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 *      insert(val)：当元素 val 不存在时，向集合中插入该项。
 *      remove(val)：元素 val 存在时，从集合中移除该项。
 *      getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 * 示例 :
 *      // 初始化一个空的集合。
 *      RandomizedSet randomSet = new RandomizedSet();
 *      // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 *      randomSet.insert(1);
 *      // 返回 false ，表示集合中不存在 2 。
 *      randomSet.remove(2);
 *      // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 *      randomSet.insert(2);
 *      // getRandom 应随机返回 1 或 2 。
 *      randomSet.getRandom();
 *      // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 *      randomSet.remove(1);
 *      // 2 已在集合中，所以返回 false 。
 *      randomSet.insert(2);
 *      // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 *      randomSet.getRandom();
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
            map.put(val, list.size() - 1);
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
            int lastVal = list.get(list.size() - 1);
            list.set(idx, lastVal);
            map.put(lastVal, idx);

            list.remove(list.size() - 1);
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
