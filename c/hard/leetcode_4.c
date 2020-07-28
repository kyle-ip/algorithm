//
// Created by ywh on 2020/7/22.
//

#include <stdlib.h>

/**
 * 求两个有序数组的中位数
 *
 * @param nums1
 * @param nums1Size
 * @param nums2
 * @param nums2Size
 * @return
 */
double findMedianSortedArrays(int *nums1, int nums1Size, int *nums2, int nums2Size) {
    int idx1 = 0, idx2 = 0, idx = 0, half = (nums1Size + nums2Size) / 2 + 1;
    int *nums = malloc(sizeof(int) * half);
    while (idx < half) {
        int n = 0;
        if (idx1 < nums1Size && idx2 < nums2Size) {
            n = (nums1[idx1] < nums2[idx2]) ? nums1[idx1++] : nums2[idx2++];
        } else if (idx1 < nums1Size) {
            n = nums1[idx1++];
        } else if (idx2 < nums2Size) {
            n = nums2[idx2++];
        }
        nums[idx++] = n;
    }
    if ((nums1Size + nums2Size) % 2) {
        return nums[idx - 1];
    } else {
        return (nums[idx - 1] + nums[idx - 2]) / 2.0;
    }
}
