package com.ywh.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序
 * [排序] [稳定排序]
 *
 * @author ywh
 * @since 15/11/2019
 */
public class BucketSort {

    /**
     *
     * @param bucket
     */
    private void insertionSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int j = i - 1, cur = bucket.get(i);
            for (; j >= 0 && bucket.get(j) > cur; j--) {
                bucket.set(j + 1, bucket.get(j));
            }
            bucket.set(j + 1, cur);
        }
    }

    /**
     * Time(avg): O(n+k), Time(worst): O(n^2), Space: O(n)
     * k = bucketCount
     *
     * @param arr
     */
    public void sort(int[] arr) {

        // 计算取值范围
        int max = arr[0], min = arr[0];
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        // 元素分桶（至少 1 个，假设分 10 个）

        int bucketCount = Math.max(arr.length / 10, 1);
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int num : arr) {
            // 将 num 映射到 0 ~ 1 之间的浮点数，乘上 bucketCount 再取整即可。
            buckets.get((int) ((num - min) / (max - min + 1.0) * bucketCount)).add(num);
        }

        for (int i = 0, k = 0; i < bucketCount; i++) {
            // 对每个桶的元素排序，再放入原数组。
            Collections.sort(buckets.get(i));
            for (Integer num : buckets.get(i)) {
                arr[k++] = num;
            }
        }
    }

}
