package com.ywh.problem.leetcode.easy;

/**
 * 向后移动 0
 * [数组] [双指针]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode283 {

    /**
     * （覆盖）使用快慢两个指针，快指针每次移动一位、慢指针当且仅当快指针所指元素不为 0、执行覆盖操作后才移动；
     * 当快指针所指元素不为0，则用快指针的元素覆盖到慢指针，并把慢指针向前移动一位；
     * 最终只需要把慢指针及其后面的元素全部补为0即可
     * Time: O(n), Space: O(1)
     *
     * @param nums
     */
    public void moveZeroesAssign(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


    /**
     * （交换）使用快慢两个指针，快指针每次移动一位、慢指针当且仅当快指针所指元素不为 0、执行交换操作后才移动；
     * 当快指针所指元素不为 0，则交换快慢指针的元素，交换后慢指针的元素非0，因此向前移动一位；
     * 这样可确保慢指针停留在 0 的位置、等待快指针把非 0 元素交换过来
     * Time: O(n), Space: O(1)
     *
     * @param nums
     */
    public void moveZeroesSwap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int slow = 0, fast = 0, tmp;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                tmp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow++] = tmp;
            }
            fast++;
        }

    }

}
