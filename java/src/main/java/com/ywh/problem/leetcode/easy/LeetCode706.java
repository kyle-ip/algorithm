package com.ywh.problem.leetcode.easy;

import java.util.LinkedList;

/**
 * 设计哈希映射
 * [设计] [哈希表] [链表] [数组]
 * 
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 * 实现 MyHashMap 类：
 *      MyHashMap() 用空映射初始化对象
 *      void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 *      int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 *      void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 * 示例：
 *      输入：
 *           ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 *           [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 *      输出：
 *           [null, null, null, 1, -1, null, 1, null, -1]
 *      解释：
 *           MyHashMap myHashMap = new MyHashMap();
 *           myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 *           myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 *           myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 *           myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 *           myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 *           myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 *           myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 *           myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 * 提示：
 *      0 <= key, value <= 10^6
 *      最多调用 10^4 次 put、get 和 remove 方法
 * 
 * @author ywh
 * @since 11/14/2021
 */
public class LeetCode706 {

    static class MyHashMap {
        private static class Pair {
            public int key, value;
            public Pair(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private static final int BASE = 769;
        private final LinkedList<Pair>[] data;

        public MyHashMap() {
            data = new LinkedList[BASE];
            for (int i = 0; i < BASE; ++i) {
                data[i] = new LinkedList<>();
            }
        }

        public void put(int key, int value) {
            int h = hash(key);
            for (Pair pair : data[h]) {
                if (pair.key == key) {
                    pair.value = value;
                    return;
                }
            }
            data[h].offerLast(new Pair(key, value));
        }

        public int get(int key) {
            for (Pair pair : data[hash(key)]) {
                if (pair.key == key) {
                    return pair.value;
                }
            }
            return -1;
        }

        public void remove(int key) {
            int h = hash(key);
            for (Pair pair : data[h]) {
                if (pair.key == key) {
                    data[h].remove(pair);
                    return;
                }
            }
        }

        private static int hash(int key) {
            return key % BASE;
        }
    }
}
