package com.ywh.ds;

import com.ywh.model.ListNode;

/**
 * 链栈（头插法）
 *
 * @author ywh
 * @since 2020/10/29/029
 */
public class LinkedStack {

    private ListNode top;

    private int count;

    public LinkedStack() {
        top = null;
        count = 0;
    }

    /**
     * 入栈
     *
     * @param val
     * @return
     */
    public void push(int val) {
        //            newNode
        //      栈顶    [ ] -> [ ]    栈底
        //                 <= top
        ListNode newNode = new ListNode(val);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        count++;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (top == null) {
            throw new RuntimeException();
        }
        //              ret
        //      栈顶    [ ] -> [ ]    栈底
        //              top =>
        int ret = top.val;
        top = top.next;
        count--;
        return ret;
    }

    /**
     *
     * @return
     */
    public int size() {
        return count;
    }
}
