/**
 * Created by ywh on 29/07/2020.
 */
#include <stdio.h>
#include <stdlib.h>

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
 * 三数之和
 *
 * @param nums
 * @param numsSize
 * @param returnSize
 * @param returnColumnSizes
 * @return
 */
int **threeSum(int *nums, int numsSize, int *returnSize, int **returnColumnSizes) {
    *returnSize = 0;
    if (numsSize < 3 || returnSize == 0) {
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

int main() {
    int i, count;
//    int nums[] = {-1, 0, 1, 2, -1, -4};
    //int nums[] = { 0, 0, 0 };
    //int nums[] = { -1, 0, 1, 0 };
    int nums[] = {};
    int **triplets, **returnColumnSizes;
    triplets = threeSum(nums, sizeof(nums) / sizeof(*nums), &count, returnColumnSizes);

    for (i = 0; i < count; i++) {
        printf("%d %d %d\n", triplets[i][0], triplets[i][1], triplets[i][2]);
    }
    return 0;
}