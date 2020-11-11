package com.ywh.ds.queue;

/**
 * 队列接口
 *
 * @author ywh
 * @since 2020/11/11/011
 */
public interface Queue {

    /**
     * 入队
     *
     * @param val
     */
    void enqueue(int val);

    /**
     * 出队
     *
     * @return
     */
    int dequeue();

    /**
     * 队列长度
     *
     * @return
     */
    int size();
}
