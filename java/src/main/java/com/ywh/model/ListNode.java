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
        return val == listNode.val &&
            next.equals(listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }
}
