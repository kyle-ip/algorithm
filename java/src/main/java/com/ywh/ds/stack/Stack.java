package com.ywh.ds.stack;

/**
 * 栈接口
 *
 * @author ywh
 * @since 2020/11/11/011
 */
public interface Stack {

    /**
     * 入栈
     *
     * @param val
     */
    void push(int val);

    /**
     * 出栈
     *
     * @return
     */
    int pop();

    /**
     * 栈长度
     *
     * @return
     */
    int size();

}
