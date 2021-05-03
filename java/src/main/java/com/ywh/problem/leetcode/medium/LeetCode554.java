package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 砖墙
 * [哈希表]
 *
 * 你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。
 * 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。
 * 你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
 * 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。
 * 你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 * 示例 1：
 *      输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
 *      输出：2
 * 示例 2：
 *      输入：wall = [[1],[1],[1]]
 *      输出：3
 * 提示：
 *      n == wall.length
 *      1 <= n <= 10^4
 *      1 <= wall[i].length <= 10^4
 *      1 <= sum(wall[i].length) <= 2 * 10^4
 *      对于每一行 i ，sum(wall[i]) 应当是相同的
 *      1 <= wall[i][j] <= 2^31 - 1
 *
 * @author ywh
 * @since 5/3/2021
 */
public class LeetCode554 {

    /**
     * 问题可转换为求“垂线穿过的砖块边缘数量的最大值”，砖墙的高度减去该最大值即为答案。
     *
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {
        // key 为砖块右侧边缘到砖墙左边缘的距离，value 为砖块边缘的个数。
        Map<Integer, Integer> cnt = new HashMap<>();
        // 遍历每一层的墙。
        for (List<Integer> widths : wall) {
            for (int sum = 0, i = 0; i < widths.size() - 1; i++) {
                // 当前砖的右侧边缘到砖墙的左边缘的距离，将除了最右侧的砖块以外的其他砖块的右边缘到砖墙的左边缘的距离加入到哈希表中。
                sum += widths.get(i);
                cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            }
        }
        // 遍历哈希表，找到出现次数最多的砖块边缘，即为垂线经过的砖块边缘。
        // 该垂线经过的砖块数量即为砖墙的高度减去该垂线经过的砖块边缘的数量。
        int maxCnt = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            maxCnt = Math.max(maxCnt, entry.getValue());
        }
        return wall.size() - maxCnt;
    }

}
