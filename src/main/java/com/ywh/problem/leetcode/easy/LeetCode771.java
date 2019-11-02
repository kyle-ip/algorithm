package com.ywh.problem.leetcode.easy;

/**
 * 石头中的珠宝数量
 * [哈希表]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode771 {

    /**
     * 暴力解法
     * Time: O(m*n), Space: O(1)
     *
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {

        if (J == null || S == null || J.length() == 0 || S.length() == 0) {
            return 0;
        }

        int count = 0;
        for(int i = 0; i < J.length(); i++) {
            for(int j = 0; j < S.length(); j++) {
                if (S.charAt(j) == J.charAt(i)) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 利用 Hash 标记珠宝的位置
     * Time: O(m+n), Space: O(k)
     *
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStonesHashMap(String J, String S) {
        if (J == null || S == null || J.length() == 0 || S.length() == 0) {
            return 0;
        }

        // Java 中基本类型的默认值是0，引用类型会默认为 null
        boolean[] hash = new boolean[256];
        int count = 0;
        for (int i = 0; i < J.length(); i++) {

            // 把字符作为下标，存入 Hash，其值为 true 表示珠宝
            hash[J.charAt(i)] = true;
        }

        for(int i = 0; i < S.length(); i++) {
            if (hash[S.charAt(i)]) {
                count++;
            }
        }

        return count;
    }
}
