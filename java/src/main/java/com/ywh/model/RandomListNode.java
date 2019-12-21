package com.ywh.model;

/**
 * 含随机指针的链表节点
 *
 * @author ywh
 * @since 21/12/2019
 */
public class RandomListNode {
    public int val;

    public RandomListNode next, random;

    public RandomListNode(int x) {
        this.val = x;
    }

}
