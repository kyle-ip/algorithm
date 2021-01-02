package com.ywh.problem.leetcode.easy;

/**
 * 种花问题
 * [贪心] [数组]
 *
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
 * 示例 1：
 *      输入：flowerbed = [1,0,0,0,1], n = 1
 *      输出：true
 * 示例 2：
 *      输入：flowerbed = [1,0,0,0,1], n = 2
 *      输出：false
 * 提示：
 *      1 <= flowerbed.length <= 2 * 10^4
 *      flowerbed[i] 为 0 或 1
 *      flowerbed 中不存在相邻的两朵花
 *      0 <= n <= flowerbed.length
 *
 * @author ywh
 * @since 02/01/2021
 */
public class LeetCode605 {

    /**
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n <= 0 || flowerbed.length == 1 && flowerbed[0] == 0) {
            return true;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                n--;
                if (n <= 0) {
                    return true;
                }
                flowerbed[i] = 1;
            }

//            // 1 不能种花，跳过。
//            if (flowerbed[i] == 1) {
//                continue;
//            }
//            // [0, 0, ...] or [..., 0, 0] or [..., 0, 0, 0, ...]
//            if (
//                (i == 0 && flowerbed[i + 1] == 0)
//                    || (i == flowerbed.length - 1 && flowerbed[i - 1] == 0)
//                    || (i > 0 && i < flowerbed.length - 1 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0)
//            ) {
//                n--;
//                flowerbed[i] = 1;
//            }
//            if (n <= 0) {
//                return true;
//            }
        }
        return false;
    }
}
