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

    public DoublyListNode(int val) {
        this.val = val;
    }

    public DoublyListNode(int key, int val, DoublyListNode next, DoublyListNode prev) {
        this.key = key;
        this.val = val;
        this.next = next;
        this.prev = prev;
    }

    public DoublyListNode(DoublyListNode next, DoublyListNode prev) {
        this.next = next;
        this.prev = prev;
    }
}
