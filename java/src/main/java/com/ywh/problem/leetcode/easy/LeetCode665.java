package com.ywh.problem.leetcode.easy;

/**
 * 非递减数列
 * [数组]
 * 
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * 示例 1:
 *      输入: nums = [4,2,3]
 *      输出: true
 *      解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 *      输入: nums = [4,2,1]
 *      输出: false
 *      解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * 提示：
 *      1 <= n <= 10 ^ 4
 *      - 10 ^ 5 <= nums[i] <= 10 ^ 5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ywh
 * @since 10/05/2020
 */
public class LeetCode665 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public boolean checkPossibilityBoolean(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        // 如果一开始就出现递减，则 modified 置为 true。遍历过程判断 modified 则返回 false。
        boolean modified = nums[0] > nums[1];
        for (int i = 1; i < nums.length - 1; i++) {
            // 非递减，下一位。
            if (nums[i] <= nums[i + 1]) {
                continue;
            }
            if (modified) {
                return false;
            }
            // 3, [5], 4 => 3, 3, 4
            if (nums[i + 1] >= nums[i - 1]) {
                nums[i] = nums[i - 1];
            }
            // 4, [5], 3 => 4, 5, 5
            else {
                nums[i + 1] = nums[i];
            }
            modified = true;
        }
        return true;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public boolean checkPossibilityInt(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        // 记录修改次数，一开始就递减则置为 1，否则为 0。
        int modified = nums[0] > nums[1] ? 1 : 0;
        for (int i = 1; i < nums.length - 1 && modified <= 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                ++modified;
                //   ->
                // 3, [5], 4 => 3, 3, 4
                if (nums[i + 1] >= nums[i - 1]) {
                    nums[i] = nums[i - 1];
                }
                //       ->
                // 4, [5], 3 => 4, 5, 5
                else {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return modified <= 1;
    }
}
