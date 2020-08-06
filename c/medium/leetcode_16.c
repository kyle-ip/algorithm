/**
 * Created by ywh on 01/08/2020.
 */
#include <stdlib.h>
#include <limits.h>
#include <stdio.h>

/**
 *
 * @param a
 * @param b
 * @return
 */
static int compare(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

/**
 * 求和最接近目标值的三个数
 *
 * @param nums
 * @param numsSize
 * @param target
 * @return
 */
int threeSumClosest(int *nums, int numsSize, int target) {
    int ret = 0, min = INT_MAX, diff;
    qsort(nums, numsSize, sizeof(*nums), compare);
    for (int k = numsSize - 1; k >= 2; k--) {
        int left = 0, right = k - 1, sum;
        while (left < right) {
            sum = nums[left] + nums[right] + nums[k];
            if (sum == target) {
                return target;
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
            diff = abs(target - sum);
            if (diff < min) {
                ret = sum;
                min = diff;
            }
        }
    }
    return ret;
}