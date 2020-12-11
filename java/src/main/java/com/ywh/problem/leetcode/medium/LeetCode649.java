package com.ywh.problem.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Dota2 参议院
 * [贪心]
 *
 * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。
 * 在每一轮中，每一位参议员都可以行使两项权利中的一项：
 *      1. 禁止一名参议员的权利：参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
 *      2. 宣布胜利：如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
 * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
 * 示例 1：
 *      输入："RD"
 *      输出："Radiant"
 *      解释：第一个参议员来自 Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
 * 示例 2：
 *      输入："RDD"
 *      输出："Dire"
 *      解释：
 *      第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
 *      第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
 *      第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
 *      因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
 *
 * @author ywh
 * @since 2020/12/11/011
 */
public class LeetCode649 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        int n = senate.length();

        // 为两个阵营分配两个队列，把议员的序号依次入队。
        Queue<Integer> radiant = new LinkedList<>(), dire = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        // 循环取出两个队列中的议员。
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            // 出队的议员序号较小，即可以行使权利：另一方议员永久出队、自己则回到队尾（下一轮可以继续投票）。
            // 注意加入队尾时，议员序号修改为原值 + n，表示维持原来的相对顺序：
            // 第一轮处于 [0, n)，下一轮投票必然在 [n, 2n) ... 直到某队为空，没有议员行使权利。
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        // 最终一方队列为空，即另一方获胜。
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }
}
