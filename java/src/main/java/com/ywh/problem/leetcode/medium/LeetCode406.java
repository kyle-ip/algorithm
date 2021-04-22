package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 根据身高重建队列
 * [贪心]
 *
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * 示例 1：
 *      输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 *      输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 *      解释：
 *      编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 *      编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 *      编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 *      编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 *      编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 *      编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 *      因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 * 示例 2：
 *      输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 *      输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 * 提示：
 *      1 <= people.length <= 2000
 *      0 <= hi <= 10^6
 *      0 <= ki < people.length
 *      题目数据确保队列可以被重建
 *
 * @author ywh
 * @since 4/11/2021
 */
public class LeetCode406 {

    /**
     * Time: O(n^2), Space: O(n*log(n))
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueueLowToHigh(int[][] people) {

        // 排序：如果两人身高不相等，则身高较矮的排前面（第一维升序），否则“前面人较多”的排前面（第二维降序）。
        Arrays.sort(people, (p1, p2) -> p1[0] != p2[0]? p1[0] - p2[0]: p2[1] - p1[1]);

        // 排队：
        int[][] ret = new int[people.length][];
        for (int[] person : people) {
            // person 的前面有 spaces = person[1]+1 个位置。
            // 遍历所有位置，如果 ret[i] 未有人进队，则 spaces--。
            // 如果 spaces== 0，表示 person 的前面没有位置了（这个位正是 person），把 person 排进去。
            for (int spaces = person[1] + 1, i = 0; i < people.length; ++i) {
                if (ret[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ret[i] = person;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Time: O(n^2), Space: O(n*log(n))
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueueHighToLow(int[][] people) {
        // 排序：如果两人身高不相等，则身高较高的排前面（第一维降序），否则“前面人较少”的排前面（第二维升序）。
        Arrays.sort(people, (p1, p2) -> p1[0] != p2[0]? p2[0] - p1[0]: p1[1] - p2[1]);
        List<int[]> ret = new ArrayList<>();

        // 排队：遍历每个人，前面有 person[1] 个人的，插到 person[1] 的位置去（如果人数相同，则插到身高更高则插到前面）。
        for (int[] person : people) {
            ret.add(person[1], person);
        }
        return ret.toArray(new int[ret.size()][]);
    }
}
