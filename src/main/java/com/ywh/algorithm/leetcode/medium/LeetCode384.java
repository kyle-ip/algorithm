package com.ywh.algorithm.leetcode.medium;

import java.util.Random;

/**
 * 随机洗牌
 * [数组]
 *
 * @author ywh
 * @since 2019/10/30
 */
public class LeetCode384 {

    /**
     * 使用随机数生成器（nextInt 左闭右开）
     * 每轮循环从 0 ~ i 之间随机取一个正整数作为下标，交换 i 与 j 下标的元素即可
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int[] shuffle(int[] nums) {
        Random rnd = new Random();
        for (int i = nums.length - 1; i > 0; i++) {
            int j = rnd.nextInt(i + 1);
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }
}
