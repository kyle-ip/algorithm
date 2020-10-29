package com.ywh.ds;

/**
 * 顺序栈
 *
 * @author ywh
 * @since 2020/10/29/029
 */
public class ArrayStack {

    /**
     * 数组
     */
    private int[] array;

    /**
     * 元素个数（栈顶指针）
     */
    private int count;

    /**
     * 栈大小
     */
    private int n;

    public ArrayStack(int n) {
        this.array = new int[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 入栈
     *
     * @param val
     * @return
     */
    public void push(int val) {
        // 数组空间不足，直接返回。
        if (count == n) {
            throw new RuntimeException();
        }
        // 添加元素后栈顶指针 +1
        array[count++] = val;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (count == 0) {
            throw new RuntimeException();
        }
        return array[--count];
    }

    /**
     *
     * @return
     */
    public int size() {
        return count;
    }
}
