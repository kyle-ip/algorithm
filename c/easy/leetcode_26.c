//
// Created by ywh on 2020/8/15.
//

/**
 * 删除排序数组中的重复项
 *
 * @param nums
 * @param numsSize
 * @return
 */
int removeDuplicates(int *nums, int numsSize) {
    if (!nums || numsSize <= 0) {
        return 0;
    }
    int left = 1, right = 1;
    for (; right < numsSize; right++) {
        if (nums[right] != nums[right - 1]) {
            nums[left++] = nums[right];
        }
    }
    return left;
}