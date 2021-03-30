package com.ywh.problem.leetcode.easy;

/**
 * 删除排序数组中的重复项
 * [双指针] [数组]
 *
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1：
 *      给定数组 nums = [1,1,2],
 *      函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *      你不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *      给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *      函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *      你不需要考虑数组中超出新长度后面的元素。
 * 说明：
 *      为什么返回数值是整数，但输出的答案是数组呢?
 *      请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *      你可以想象内部操作如下:
 *      // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 *      int len = removeDuplicates(nums);
 *
 *      // 在函数里修改输入数组对于调用者是可见的。
 *      // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 *      for (int i = 0; i < len; i++) {
 *           print(nums[i]);
 *      }
 * 提示：
 *      0 <= nums.length <= 3 * 10^4
 *      -10^4 <= nums[i] <= 10^4
 *      nums 已按升序排列
 *      
 * @author ywh
 * @since 06/11/2019
 */
public class LeetCode26 {

    /**
     * 左指针定位在出现重复元素的位置（同时也是不含重复元素的子数组长度），右指针不断向后移动；
     * 当右指针发现不同元素，则把值赋予左指针的位置，左指针后移；
     * 右指针到达末尾，返回左指针即可
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = 1, r = 1;
        for (; r < nums.length; r++) {
            if (nums[r] != nums[r - 1]) {
                // 此时 nums[r] 为最近出现的不同元素，拷贝到 l 的位置上，并移动 l。
                nums[l++] = nums[r];
            }
        }
        return l;
    }

}
