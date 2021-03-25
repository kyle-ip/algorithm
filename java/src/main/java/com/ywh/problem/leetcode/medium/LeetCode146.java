package com.ywh.problem.leetcode.medium;

import com.ywh.ds.list.DoublyListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU 缓存机制
 * [设计]
 *
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *      LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 *      int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 *      void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 *      当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 示例：
 *      输入
 *      ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 *      [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 *      输出
 *      [null, null, null, 1, null, -1, null, -1, 3, 4]
 *      解释
 *      LRUCache lRUCache = new LRUCache(2);
 *      lRUCache.put(1, 1);     // 缓存是 {1=1}
 *      lRUCache.put(2, 2);     // 缓存是 {1=1, 2=2}
 *      lRUCache.get(1);        // 返回 1
 *      lRUCache.put(3, 3);     // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 *      lRUCache.get(2);        // 返回 -1 (未找到)
 *      lRUCache.put(4, 4);     // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 *      lRUCache.get(1);        // 返回 -1 (未找到)
 *      lRUCache.get(3);        // 返回 3
 *      lRUCache.get(4);        // 返回 4
 * 提示：
 *      1 <= capacity <= 3000
 *      0 <= key <= 3000
 *      0 <= value <= 104
 *      最多调用 3 * 10^4 次 get 和 put
 *
 * @author ywh
 * @since 2019/10/28
 */
public class LeetCode146 {

    public static class LRUCache {

        // 使用双向链表（移动节点 O(1)）和哈希表（get、put 操作 O(1)）存储。
        // 头节点存放下次 put 操作时被淘汰的元素，其 next 表示最近刚被使用的，prev 表示 LRU。

        /**
         *  prev: <-
         *  next: ->
         *
         *         +----------------------------------+
         *         ↓                                  ↓
         *      [node0] <-> [node1] <-> [node2] <-> [node3]
         * （最近刚被使用的）                   （head，存放 LRU）
         */
        private DoublyListNode head = new DoublyListNode();

        private final Map<Integer, DoublyListNode> map = new HashMap<>();

        /**
         * 辅助方法，把节点移动到头节点的后面，表示最近刚被使用。
         *
         * @param node
         */
        private void moveToHeadNext(DoublyListNode node) {
            // 如果当前节点为头节点，表示头节点刚被使用，把头节点移动到其前一个节点。
            if (node == head) {
                head = head.prev;
                return;
            }
            // 否则解除当前节点的前驱、后继指针，把当前节点插到头节点的后面（六步），即 [head] <-> [cur] <-> [xxx]。

            // detach
            node.prev.next = node.next;
            node.next.prev = node.prev;

            // attach
            node.next = head.next;
            node.next.prev = node;

            head.next = node;
            node.prev = head;
        }

        /**
         * 构造方法，初始化链表。
         *
         * @param capacity
         */
        public LRUCache(int capacity) {
            DoublyListNode node = head;
            // 尾插法创建容量 -1 个节点（因为 head 本身也用于存放数据）。
            for (int i = 0; i < capacity - 1; i++) {
                node.next = new DoublyListNode(null, node);
                node = node.next;
            }
            node.next = head;
            head.prev = node;
        }

        /**
         * 获取元素：
         * 从哈希表中取出节点，并把该节点移动到链表头部，返回该节点的值。
         *
         * @param key
         * @return
         */
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            DoublyListNode node = map.get(key);
            moveToHeadNext(node);
            return node.val;
        }

        /**
         * 添加元素
         *
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            DoublyListNode node;
            // 哈希表中已存在该 key，则取出该节点。
            if (map.containsKey(key)) {
                node = map.get(key);
            }
            // 哈希表中不存在该 key，则取头节点来存放新元素（哈希表中要移除头节点的旧值）。
            else {
                node = head;
                map.remove(head.key);
            }
            // 设值，添加到哈希表，并移动到头部。
            node.key = key;
            node.val = value;
            map.put(key, node);
            moveToHeadNext(node);
        }
    }
}
