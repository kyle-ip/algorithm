package com.ywh.sorting;

import java.util.ArrayList;
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
     * 5 ~ 15
     */
    private int bucketSize = 10;

    /**
     * Time(avg): O(n+k), Time(worst): O(n^2), Space: O(n)
     * k = bucketCount
     *
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // 计算取值范围
        int max = arr[0], min = arr[0];
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        // 元素分桶（至少 1 个）
        int bucketCount = Math.max(arr.length / bucketSize, 1);
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int num : arr) {
            // 将 num 映射到 0 ~ 1 之间的浮点数，乘上 bucketCount 再取整即可。
            buckets.get((int) ((num - min) / (max - min + 1.0) * bucketCount)).add(num);
        }

        // 排序后的桶元素重新放回原数组
        int idx = 0;
        for (List<Integer> bucket : buckets) {
            if (bucket.isEmpty()) {
                continue;
            }

            // 对每个桶插入排序后放回原数组。
            for (int i = 1; i < bucket.size(); i++) {
                int j = i - 1, cur = bucket.get(i);
                for (; j >= 0 && bucket.get(j) > cur; j--) {
                    bucket.set(j + 1, bucket.get(j));
                }
                bucket.set(j + 1, cur);
            }
            for (Integer num : bucket) {
                arr[idx++] = num;
            }
        }
    }
}
