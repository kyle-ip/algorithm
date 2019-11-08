package com.ywh.model;

import java.util.Objects;

/**
 * 单链表节点
 *
 * @author ywh
 * @since 2/14/2019
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(){}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ListNode)) {
            return false;
        }
        ListNode listNode = (ListNode) o;
        ListNode cur1 = this, cur2 = listNode;
        while (cur1 != null && cur2 != null) {
            if (cur1.val != cur2.val) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1 == null && cur2 == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null) {
                sb.append(",");
            }
            cur = cur.next;
        }
        return sb.toString();
    }
}
