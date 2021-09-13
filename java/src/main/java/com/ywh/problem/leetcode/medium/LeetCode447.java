package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 回旋镖的数量
 * [数学] [哈希表] [数学]
 * 
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 返回平面上所有回旋镖的数量。
 * 示例 1：
 *      输入：points = [[0,0],[1,0],[2,0]]
 *      输出：2
 *      解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 *      输入：points = [[1,1],[2,2],[3,3]]
 *      输出：2
 * 示例 3：
 *      输入：points = [[1,1]]
 *      输出：0
 * 提示：
 *      n == points.length
 *      1 <= n <= 500
 *      points[i].length == 2
 *      -104 <= xi, yi <= 104
 *      所有点都 互不相同
 *
 * @author ywh
 * @since 9/13/2021
 */
public class LeetCode447 {

    /**
     * Time: O(n^2), Space: O(n)
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        int ret = 0;
        for (int[] p : points) {
            Map<Integer, Integer> cnt = new HashMap<>();
            // 统计 p、q 两点间距离的个数。
            for (int[] q : points) {
                int dis = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
            }

            // 这 m 点中选出 2 个作为回旋镖 2 个端点，方案数为在 m 个元素中选出 2 个不同元素的排列数（考虑元组顺序）。
            // A(m, 2) = m * (m - 1)
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int m = entry.getValue();
                ret += m * (m - 1);
            }
        }
        return ret;
    }
}
