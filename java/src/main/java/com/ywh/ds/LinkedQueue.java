package com.ywh.ds;

import com.ywh.model.ListNode;

/**
 * 链队列（头插法）
 *
 * @author ywh
 * @since 2020/10/29/029
 */
public class LinkedQueue {

    private ListNode tail, head;

    private int n;

    public LinkedQueue() {
        tail = null;
        head = null;
        n = 0;
    }

    public void add(int val) {
        ListNode newNode = new ListNode(val);

        //   [val]
        // tail/head
        if (tail == null) {
            tail = newNode;
            head = newNode;
        }
        // [ ] -> [val]
        // head   tail
        else {
            tail.next = newNode;
            tail = tail.next;
        }
        n++;
    }

    public int poll() {
        if (head == null) {
            throw new RuntimeException();
        }
        // [val] -> [ ] -> [ ]
        // head =>       tail
        int val = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        n--;
        return val;
    }

    public int size() {
        return n;
    }
}
