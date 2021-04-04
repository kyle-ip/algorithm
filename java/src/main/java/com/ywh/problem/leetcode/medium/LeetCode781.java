package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 森林中的兔子
 * [数学] [哈希表]
 *
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。
 * 我们将这些回答放在 answers 数组里，返回森林中兔子的最少数量。
 * 示例:
 *      输入: answers = [1, 1, 2]
 *      输出: 5
 *      解释:
 *      两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 *      之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 *      设回答了 "2" 的兔子为蓝色。
 *      此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 *      因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 *      输入: answers = [10, 10, 10]
 *      输出: 11
 *      输入: answers = []
 *      输出: 0
 * 说明:
 *      answers 的长度最大为1000。
 *      answers[i] 是在 [0, 999] 范围内的整数。
 *
 * @author ywh
 * @since 4/4/2021
 */
public class LeetCode781 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param answers
     * @return
     */
    public int numRabbits(int[] answers) {
        // 两只相同颜色的兔子看到的其他同色兔子数相同，
        // 将 answers 中值相同的元素分为一组，对于每一组计算出兔子的最少数量，所有组数值之和即为最终答案。

        // 对 answers 所有组进行计数。
        Map<Integer, Integer> counter = new HashMap<>();
        for (int count : answers) {
            counter.put(count, counter.getOrDefault(count, 0) + 1);
        }
        // 至此，counter 存放“看到其他同色兔子数”与这样的兔子的只数的键值对（注意有一些兔子的回答是没有包含在数组中的）。
        // 比如某兔子回答：看到与自己同为红色的兔子共 3 只。而这样回答的兔子共 4 只，则存放 3->4。
        int ret = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            // 如果有 x 只兔子回答 y，则至少有 x/(y+1) 种不同的颜色，且每种颜色有 y+1 只。
            // 因此兔子数至少为 x/(y+1)^2。
            int y = entry.getKey(), x = entry.getValue();
            ret += (x + y) / (y + 1) * (y + 1);
        }
        return ret;
    }

}
