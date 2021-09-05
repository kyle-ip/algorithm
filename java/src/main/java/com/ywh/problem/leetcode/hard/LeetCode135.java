package com.ywh.problem.leetcode.hard;

/**
 * 分发糖果
 * [贪心]
 *
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *      每个孩子至少分配到 1 个糖果。
 *      相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 *      输入: [1,0,2]
 *      输出: 5
 *      解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 *      输入: [1,2,2]
 *      输出: 4
 *      解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *           第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 * @author ywh
 * @since 2020/12/24/024
 */
public class LeetCode135 {


    /**
     * 糖果总是尽量少给，且从 1 开始累计，每次要么比左边相邻的学生多给一个，要么重新置为 1。
     * 则可以画图：
     *
     *          |
     *      |   |       |
     *  |   |   |   |   |   |
     * [1] [3] [5] [2] [3] [3]
     *
     * 如果当前学生比上一个学生评分高，说明就在最近的递增序列中，直接分配给该学生 pre+1 个糖果。
     * 否则在一个递减序列中，直接分配给当前学生一个糖果，并把该学生所在的递减序列中所有的学生都再多分配一个糖果，以保证糖果数量还是满足条件。
     *      无需显式地额外分配糖果，只需要记录当前的递减序列长度，即可知道需要额外分配的糖果数量。
     *      同时注意当当前的递减序列长度和上一个递增序列等长时，需要把最近的递增序列的最后一个学生也并进递减序列中。
     *
     * Time: O(n), Space: O(1)
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int ret = 1, inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            // 当前学生的分数比上一名学生高或相等。
            if (ratings[i] >= ratings[i - 1]) {
                // 递减序列长度重置为 0。
                dec = 0;
                // 如果与前面的学生分数相等，则 pre 置为 1，否则 +1。
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                // 总糖果数加上 pre。
                ret += pre;
                // 递增序列长度设为 pre。
                inc = pre;
            }
            // 当前学生的分数比上一名学生低。
            else {
                // 递减序列长度 +1，如果此时递减序列长度等于递增序列长度，则递减序列长度再 +1。
                dec++;
                if (dec == inc) {
                    dec++;
                }

                // 总糖果数加上递减序列长度。
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param ratings
     * @return
     */
    public int candy2(int[] ratings) {
        int n = ratings.length;

        // 从左到右、从右到左各遍历一次，求出每名学生分别满足左右规则时、最少需要分得糖果数之间的较大者。
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            // 当 ratings[i-1] < ratings[i]，i 号学生糖果数比 i-1 号学生多 1，否则重置为 1。
            cnt[i] = i > 0 && ratings[i] > ratings[i - 1]? cnt[i - 1] + 1: 1;
        }
        // 从右到左数时，只需要一个变量记录从右到左的累计值。
        // 与从左到右的累计值比较，其较大者累计到 ret 中。
        int ret = 0;
        for (int i = n - 1, right = 0; i >= 0; i--) {
            // 当 ratings[i] > ratings[i+1]，i 号学生糖果数比 i+1 号学生多 1，否则重置为 1。
            right = i < n - 1 && ratings[i] > ratings[i + 1]? right + 1: 1;
            ret += Math.max(cnt[i], right);
        }
        return ret;
    }
}
