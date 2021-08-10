package com.ywh.ds.list;

/**
 * 双向链表节点
 *
 * @author ywh
 * @since 2019/10/28
 */
public class DoublyListNode {

    public int key;

    public int val;

    public DoublyListNode next;

    public DoublyListNode prev;

    public DoublyListNode() {}

    public DoublyListNode(DoublyListNode next, DoublyListNode prev) {
        this.next = next;
        this.prev = prev;
    }
}
