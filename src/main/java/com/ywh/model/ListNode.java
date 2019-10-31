package com.ywh.model;

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

}
