//
// Created by ywh on 2020/7/20.
//

#include <stdio.h>
#include <stdlib.h>
#include "../dataStructure.h"

/**
 * 求和为给定值的两个数
 *
 * @param nums
 * @param numsSize
 * @param target
 * @param returnSize
 * @return
 */
int *twoSum(int *nums, int numsSize, int target, int *returnSize) {
    struct Object *objs = malloc(numsSize * sizeof(*objs));
    for (int i = 0; i < numsSize; ++i) {
        objs[i].val = nums[i];
        objs[i].idx = i;
    }
    qsort(objs, numsSize, sizeof(*objs), cmp);
    int *ret = malloc(2 * sizeof(int));
    for (int i = 0, j = numsSize - 1; i < j;) {
        int sum = objs[i].val + objs[j].val;
        if (sum == target) {
            ret[0] = objs[i].idx;
            ret[1] = objs[j].idx;
            *returnSize = 2;
            return ret;
        }
        if (sum < target) {
            i++;
        } else {
            j--;
        }
    }
    return NULL;
}