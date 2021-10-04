package com.ywh.problem.leetcode.medium;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 打印零与奇偶数
 * [多线程]
 * 
 * 假设有这么一个类：
 *      class ZeroEvenOdd {
 *        public ZeroEvenOdd(int n) { ... }      // 构造函数
 *        public void zero(printNumber) { ... }  // 仅打印出 0
 *        public void even(printNumber) { ... }  // 仅打印出 偶数
 *        public void odd(printNumber) { ... }   // 仅打印出 奇数
 *      }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *      线程 A 将调用 zero()，它只输出 0 。
 *      线程 B 将调用 even()，它只输出偶数。
 *      线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 * 示例 1：
 *      输入：n = 2
 *      输出："0102"
 *      说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 示例 2：
 *      输入：n = 5
 *      输出："0102030405"
 *
 * @author ywh
 * @since 10/4/2021
 */
public class LeetCode1116 {

    static class ZeroEvenOdd {
        private int n;
        private final Semaphore zeroSema = new Semaphore(1);
        private final Semaphore oddSema = new Semaphore(0);
        private final Semaphore evenSema = new Semaphore(0);

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                zeroSema.acquire();
                printNumber.accept(0);
                if ((i & 1) == 1) {
                    oddSema.release();
                } else {
                    evenSema.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if ((i & 1) == 0) {
                    evenSema.acquire();
                    printNumber.accept(i);
                    zeroSema.release();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if ((i & 1) == 1) {
                    oddSema.acquire();
                    printNumber.accept(i);
                    zeroSema.release();
                }
            }
        }
    }
}
