package com.ywh.ds.queue;

import com.ywh.ds.list.ListNode;

/**
 * 链队列（头插法）
 *
 * @author ywh
 * @since 2020/10/29/029
 */
public class LinkedQueue implements Queue {

    private ListNode tail, head;

    private int n;

    public LinkedQueue() {
        tail = null;
        head = null;
        n = 0;
    }

    @Override
    public void enqueue(int val) {
        ListNode newNode = new ListNode(val);

        //   [val]            [ ] -> [val]
        // tail/head    =>    head   tail
        if (tail == null) {
            tail = newNode;
            head = newNode;
        }
        else {
            tail.next = newNode;
            tail = tail.next;
        }
        n++;
    }

    @Override
    public int dequeue() {
        if (head == null) {
            throw new RuntimeException();
        }
        // [val] -> [ ] -> [ ]
        // head =>         tail
        int val = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        n--;
        return val;
    }

    @Override
    public int size() {
        return n;
    }
}
