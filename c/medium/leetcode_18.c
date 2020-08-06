//
// Created by ywh on 2020/8/4.
//
#include <stdlib.h>

/**
 *
 * @param a
 * @param b
 * @return
 */
static int cmp(const void *a, const void *b) {
    return *(int *) a - * (int *) b;
}

/**
 * 四数之和
 *
 * @param nums
 * @param numsSize
 * @param target
 * @param returnSize
 * @param returnColumnSizes
 * @return
 */
int** fourSum(int* nums, int numsSize, int target, int* returnSize, int** returnColumnSizes){
    *returnSize = 0;
    if (numsSize < 4) {
        return NULL;
    }
    qsort(nums, numsSize, sizeof(*nums), cmp);
    int **ret = (int **) malloc(sizeof(int *) * numsSize * numsSize);
    *returnColumnSizes = (int *) malloc(sizeof(int) * numsSize * numsSize);
    int p = numsSize - 1;
    while (p >= 3) {
        if (4 * nums[p] < target) {
            break;
        }
        int k = p - 1;
        while (k >= 2) {
            if (3 * nums[k] + nums[p] < target) {
                break;
            }
            int left = 0, right = k - 1, innerTarget = target - nums[k] - nums[p];
            while (left < right) {
                if (nums[left] + nums[right] == innerTarget) {
                    ret[*returnSize] = (int *) malloc(sizeof(int) * 4);
                    (*returnColumnSizes)[*returnSize] = 4;
                    ret[*returnSize][0] = nums[left];
                    ret[*returnSize][1] = nums[right];
                    ret[*returnSize][2] = nums[k];
                    ret[*returnSize][3] = nums[p];
                    (*returnSize)++;
                    for (; left < right && nums[left] == nums[left + 1]; left++);
                    for (; left < right && nums[right] == nums[right - 1]; right--);
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < innerTarget) {
                    left++;
                } else {
                    right--;
                }
            }
            for (; k >= 2 && nums[k] == nums[k - 1]; k--);
            k--;
        }
        for (; p >= 3 && nums[p] == nums[p - 1]; p--);
        p--;
    }
    return ret;
}