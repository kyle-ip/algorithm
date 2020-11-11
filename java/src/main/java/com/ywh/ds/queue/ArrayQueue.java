package com.ywh.ds.queue;

/**
 * 顺序队列
 *
 * @author ywh
 * @since 2020/10/29/029
 */
public class ArrayQueue implements Queue {

    private int head, tail;

    private final int[] array;

    int count;

    int n;

    public ArrayQueue(int n) {
        this.array = new int[n];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.n = n;
    }

    @Override
    public void enqueue(int val) {
        if (count == n) {
            throw new RuntimeException();
        }
        array[tail++] = val;
    }

    @Override
    public int dequeue() {
        if (head == tail) {
            throw new RuntimeException();
        }
        return array[head++];
    }

    @Override
    public int size() {
        return n;
    }
}
