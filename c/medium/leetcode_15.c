/**
/* Created by ywh on 29/07/2020.
 */
#include <stdlib.h>

static int compare(const void *a, const void *b)
{
    return *(int *) a - *(int *) b;
}

int **threeSum(int *nums, int numsSize, int *returnSize, int **returnColumnSizes) {
    if (numsSize < 3) {
        return NULL;
    }
    qsort(nums, numsSize, sizeof(*nums), compare);
    int **ret = (int **) malloc(sizeof(int *) * (numsSize) * (numsSize));
    *returnColumnSizes = (int *) malloc(sizeof(int) * (numsSize) * (numsSize));

    for (int k = numsSize - 1; k >= 2; k--) {
        if (nums[k] < 0) {
            break;
        }
        int target = -nums[k], left = 0, right = k - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                ret[*returnSize] = (int *) malloc(sizeof(int) * 3);
                (*returnColumnSizes)[*returnSize] = 3;
                ret[*returnSize][0] = nums[left];
                ret[*returnSize][1] = nums[right];
                ret[*returnSize][2] = nums[k];
                (*returnSize)++;
                for (; left < right && nums[left] == nums[left + 1]; left++);
                for (; left < right && nums[right] == nums[right - 1]; right--);
                left++;
                right--;
            }
        }
        while (k >= 2 && nums[k] == nums[k - 1]) {
            k--;
        }
    }
    return ret;
}