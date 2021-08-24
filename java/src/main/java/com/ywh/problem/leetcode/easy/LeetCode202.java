package com.ywh.problem.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 * [哈希表] [双指针] [数学]
 * 
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 * 示例 1：
 *      输入：19
 *      输出：true
 *      解释：
 *      1^2 + 9^2 = 82
 *      8^2 + 2^2 = 68
 *      6^2 + 8^2 = 100
 *      1^2 + 0^2 + 0^2 = 1
 * 示例 2：
 *      输入：n = 2
 *      输出：false
 * 提示：
 *      1 <= n <= 2^31 - 1
 * 
 * @author ywh
 * @since 20/11/2019
 */
public class LeetCode202 {

    /**
     * 求 n 每个位置上的数字的平方之和
     *
     * @param n
     * @return
     */
    private int transform(int n) {
        int sum = 0, a;
        for (; n != 0; n /= 10) {
            a = n % 10;
            sum += a * a;
        }
        return sum;
    }

    /**
     * 双指针解法
     *
     * Time: O(1), Space: O(1)
     *
     * @param n
     * @return
     */
    public boolean isHappyTwoPointer(int n) {
        // 根据快乐数的定义，它会在 4, 16, 37, 58, 89, 145, 42, 20 之间循环
        // 因此定义快慢指针，如果快指针可以达到 1，则返回 true；否则当快指针追上慢指针返回 false
        for (int fast = n, slow = n; ; ) {
            fast = transform(transform(fast));
            slow = transform(slow);
            if (fast == 1) {
                return true;
            }
            if (fast == slow) {
                return false;
            }
        }
    }


    /**
     * 哈希表解法
     *
     * Time: O(1), Space: O(1)
     *
     * @param n
     * @return
     */
    public boolean isHappyHashSet(int n) {
        if (n <= 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = n; i != 1; i = transform(i)) {
            if (set.contains(i)) {
                return false;
            }
            set.add(i);
        }
        return true;
    }

    /**
     * 数学解法
     *
     * @param n
     * @return
     */
    public boolean isHappyMath(int n) {

        // 打表
        boolean[] happy = new boolean[101];
        happy[1] = true;
        happy[7] = true;
        happy[10] = true;
        happy[13] = true;
        happy[19] = true;
        happy[23] = true;
        happy[28] = true;
        happy[31] = true;
        happy[32] = true;
        happy[44] = true;
        happy[49] = true;
        happy[68] = true;
        happy[70] = true;
        happy[79] = true;
        happy[82] = true;
        happy[86] = true;
        happy[91] = true;
        happy[94] = true;
        happy[97] = true;
        happy[100] = true;

        // 不断转换，直到 n 落在 [1, 100] 的区间内
        while (n > 100) {
            n = transform(n);
        }
        return happy[n];
    }

    /**
     * 计算 n 需要变换多少次才能 <= 100
     *
     * @param n
     * @return
     */
    public int isHappyHashSetCountLessThanHundred(int n) {
        int cnt = 0;
        while (true) {
            if (n <= 100) {
                return cnt;
            }
            n = transform(n);
            ++cnt;
        }
    }

    /**
     * 计算 n 需要变换多少次才终止
     *
     * @param n
     * @return
     */
    public int isHappyHashSetCount(int n) {
        Set<Integer> set = new HashSet<>();
        int cnt = 0;
        while (n != 1) {
            n = transform(n);
            ++cnt;
            if (set.contains(n)) {
                return cnt;
            }
            set.add(n);
        }
        return cnt;
    }

    /**
     * 打印变换序列
     *
     * @param n
     * @return
     */
    public boolean isHappyHashSetPrint(int n) {
        Set<Integer> set = new HashSet<>();
        System.out.print(n + ", ");
        while (n != 1) {
            n = transform(n);
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
        }
        return true;
    }
}
