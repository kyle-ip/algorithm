package com.ywh.problem.leetcode.medium;

/**
 * 删除排序数组中的重复项 II
 * [数组] [双指针]
 *
 * @author ywh
 * @since 2020/9/21/021
 */
public class LeetCode80 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        // left     right
        //  1	      1         1, 1, 1, 2, 2, 3
        //                         l
        //                         r

        // left     right
        //  2	      2         1, 1, 1, 2, 2, 3
        //                            l
        //                            r

        // left     right
        //  2	      3         1, 1, 1, 2, 2, 3
        //                            l
        //                               r

        // left     right
        //  3	      4         1, 1, 1, 2, 2, 3
        //                               l
        //                                  r

        // left     right
        //  4	      5         1, 1, 1, 2, 2, 3
        //                                  l
        //                                     r

        int left = 1, right = 1, count = 1;
        for (; right < nums.length; right++) {
            if (nums[right] == nums[right - 1]) {
                count += 1;
            }
            else {
                count = 1;
            }
            if (count <= 2) {
                nums[left++] = nums[right];
            }
            System.out.println(String.format("left: %d, right %d", left, right));
        }
        return left;
    }
}
