package com.ywh.algorithm.util;

import com.ywh.model.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表工具类
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LinkedListUtil {

    /**
     * 判断链表是否有相同元素的节点
     *
     * @param head
     * @return
     */
    public static boolean hasDuplicatedNode(ListNode head) {

        Set<Integer> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.val)) {
                return true;
            }
            set.add(cur.val);
            cur = cur.next;
        }

        return false;
    }

}
