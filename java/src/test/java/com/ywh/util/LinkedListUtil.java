package com.ywh.util;

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
     * 尾插法创建链表
     *
     * @param str
     * @return
     */
    public static ListNode strToList(String str) {
        return strToList(str, ",");
    }

    public static ListNode strToList(String str, String splitter) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ListNode dummy = new ListNode(), cur = dummy;
        for (String s : str.split(splitter)) {
            cur.next = new ListNode(Integer.parseInt(s));
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 链表转字符串
     *
     * @param list
     * @return
     */
    public static String listToStr(ListNode list) {
        return listToStr(list, ",");
    }

    public static String listToStr(ListNode list, String splitter) {
        if (list == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (list != null) {
            sb.append(list.val);
            if (list.next != null) {
                sb.append(splitter);
            }
            list = list.next;
        }
        return sb.toString();
    }

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
