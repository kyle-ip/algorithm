package com.ywh.problem.leetcode.medium;

import com.ywh.model.DoublyListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 LRU 缓存
 * [设计]
 *
 * @author ywh
 * @since 2019/10/28
 */
public class LeetCode146 {

    public class LRUCache {

        // 使用双向链表（移动节点 O(1)）和哈希表（get、put 操作 O(1)）存储
        // 头节点存放下次 put 操作时被淘汰的元素，其next 表示最近刚被使用、prev 表示最近最少使用
        private DoublyListNode head = new DoublyListNode(-1, -1, null, null);
        private final Map<Integer, DoublyListNode> map = new HashMap<>();

        /**
         * 把节点移动到头节点
         *
         * @param cur
         */
        private void move2Head(DoublyListNode cur) {
            // 当前节点为头节点，头节点逆时针移动到上一个节点
            if (cur == head) {
                head = head.prev;
            }
            else {
                // 取下节点
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;

                // 作为头节点的下一个节点
                head.next = cur;
                cur.prev = head;
            }
        }

        /**
         * 构造方法，初始化链表
         *
         * @param capacity
         */
        public LRUCache(int capacity) {
            DoublyListNode cur = head;
            // 尾插法创建容量 - 1 个节点
            for (int i = 0; i< capacity - 1; i++) {
                cur.next = new DoublyListNode(-1, -1, cur, null);
                cur = cur.next;
            }

            // 尾节点与头节点相连
            cur.next = head;
            head.prev = cur;
        }

        /**
         * 获取元素
         *
         * @param key
         * @return
         */
        public int get(int key) {
            // 哈希表中不存在该 key 的节点，返回 -1
            if (!map.containsKey(key)) {
                return -1;
            } else {
                // 从哈希表中取出节点
                DoublyListNode cur = map.get(key);

                // 把被访问的节点移动到 head 前面，表示最近被访问过
                move2Head(cur);
                return cur.val;
            }
        }

        /**
         * 添加元素
         *
         * @param key
         * @param value
         */
        public void put(int key, int value) {

            // 哈希表中已存在该 key，则更新节点的值，并移动到头部
            if (map.containsKey(key)) {
                DoublyListNode cur = map.get(key);
                cur.val = value;
                move2Head(cur);
            } else {
                // 头节点的值不为 -1，表示已经存储数据，则要先移除
                if (head.val != -1) {
                    map.remove(head.key);
                }
                // 头节点设值，并存储到哈希表中
                head.key = key;
                head.val = value;
                map.put(key, head);

                // 头节点移向上一个节点，即指向最久未使用节点
                head = head.prev;
            }
        }
    }

}
