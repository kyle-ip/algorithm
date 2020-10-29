package com.ywh.ds;

/**
 * 顺序队列
 *
 * @author ywh
 * @since 2020/10/29/029
 */
public class ArrayQueue {

    private int head, tail;

    private int[] array;

    int count;

    int n;

    public ArrayQueue(int n) {
        this.array = new int[n];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.n = n;
    }

    public void push(int val) {
        if (count == n) {
            throw new RuntimeException();
        }
        array[tail++] = val;
    }

    public int pop() {
        if (head == tail) {
            throw new RuntimeException();
        }
        return array[head++];
    }
}
