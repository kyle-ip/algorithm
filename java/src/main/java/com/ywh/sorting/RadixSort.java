package com.ywh.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基数排序
 * [排序] [稳定排序]
 *
 * @author ywh
 * @since 15/11/2019
 */
public class RadixSort {

    /**
     * @param bucket
     */
    private void insertionSort(List<Integer> bucket) {
        if (bucket == null || bucket.size() == 0) {
            return;
        }
        for (int i = 0; i < bucket.size(); i++) {
            int j = i - 1, cur = bucket.get(i);
            while (j >= 0 && bucket.get(j) > cur) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, cur);
        }
    }

    /**
     * TODO 基于桶排序的解法未完成
     *
     * 2 ^ bits 为基数，每次处理 bits 个二进制位，共 趟基数排序（bits == 8 较优）
     * Time: O(32/b * n), Space: O(n + 2^b)
     *
     * @param arr  输入数组
     * @param bits 每次处理 bits 个二进制位
     * @param mask 掩码，每次右移 bits 个二进制位时，使用 mask 取出最低的 bits 位
     */
    private void sortInBucket(int[] arr, int bits, int mask) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int cnt = 32 / bits, bucketCount = 1 << bits, n = arr.length;

        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int d = 0; d < cnt; d++) {

            // 计算取值范围
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int num : arr) {
                int lastNum = (num >> (bits * d)) & mask;
                min = Math.min(lastNum, min);
                max = Math.max(lastNum, max);
            }

            // 取当前第 d 趟基数排序中各元素的最后一位，添加到各个桶中（注意，是使用最后一位计算桶，入桶的还是原来的元素）
            for (int num : arr) {
                int idx = (num >> (bits * d)) & mask;
                buckets.get(idx).add(num);
            }

            int idx = 0;
            for (List<Integer> bucket : buckets) {
                insertionSort(bucket);
                for (Integer num : bucket) {
                    arr[idx++] = num;
                }

                // 清零，供下次计数排序使用
                bucket.clear();
            }

            int len = 0;
            int[] tmp = new int[n];
            for (; len < n && arr[len] >= 0; len++) ;

            // [len, n] -> [0, n - len]
            System.arraycopy(arr, len, tmp, 0, n - len);

            // [0, len] -> [n - len, n]
            System.arraycopy(arr, 0, tmp, n - len, len);

            // [0, n] -> [0, n]
            System.arraycopy(tmp, 0, arr, 0, n);
        }
    }

    /**
     * 2 ^ b 为基数，每次处理 b 个二进制位（b == 8 较优）
     * Time: O(32/b * n), Space: O(n + 2^b)
     *
     * @param arr  输入数组
     * @param bits 每次处理 bits 个二进制位
     * @param mask 掩码，每次右移 bits 个二进制位时，使用 mask 取出最低的 bits 位
     */
    private void sortInCounting(int[] arr, int bits, int mask) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // 假设 int 类型十进制数用 32 位二进制数表示，则每次处理 bits 个二进制位，则处理次数为 32 / bits
        int n = arr.length, cnt = 32 / bits;

        // 定义辅助数组和计数排序位置数组（大小为 2 ^ bits）
        int[] tmp = new int[n], indexes = new int[1 << bits];

        // 循环使用计数排序
        for (int d = 0; d < cnt; d++) {

            // 对数组元素计数
            for (int num : arr) {
                // 每次右移 bits 位（第一次移动 0，第二次 1 * bits...）后取最后一位
                // 即将 num 的二进制表示从低位到高位逐位取数（第 d + 1 个 bits 位）
                int idx = (num >> (bits * d)) & mask;
                indexes[idx]++;
            }

            // 计数数组转化为位置数组
            // 先把键值 0 出现的次数 - 1，得到键值 0 在原数组中的最后下标
            --indexes[0];
            for (int i = 1; i < indexes.length; i++) {
                indexes[i] += indexes[i - 1];
            }

            // 从右向左遍历原始数组
            for (int i = n - 1; i >= 0; i--) {
                int idx = (arr[i] >> (bits * d)) & mask;
                tmp[indexes[idx]] = arr[i];
                indexes[idx]--;
            }

            // 清零，供下次计数排序使用
            Arrays.fill(indexes, 0);

            // 交换 arr 和 tmp：由于循环次数是偶数，arr 最后还是指向最初那块内存
            int[] t = arr;
            arr = tmp;
            tmp = t;
        }

        // 二进制位的最高位表示符号（1 负 0 正），如果数组中存在负数，基数排序后会被排到数组的右边，先找到第一个负数的下标（同时是左边非负数子数组的长度）
        int len = 0;
        for (; len < n && arr[len] >= 0; len++) ;

        // 把 arr 中从 len 开始、为 n - len（负数的个数）个元素拷贝到 tmp
        System.arraycopy(arr, len, tmp, 0, n - len);

        // 把 arr 中从 0 开始、为 len（正数的个数）个元素拷贝到 tmp
        System.arraycopy(arr, 0, tmp, n - len, len);

        // 最后归位
        System.arraycopy(tmp, 0, arr, 0, n);

    }

    public void sort4passInBucket(int[] arr) {
        // 掩码用十六进制表示，最低八位为 1
        sortInBucket(arr, 8, 0xff);
    }

    public void sort8passInBucket(int[] arr) {
        // 最低四位为 1
        sortInBucket(arr, 4, 0x0f);
    }

    public void sort4passInCounting(int[] arr) {
        // 掩码用十六进制表示，最低八位为 1
        sortInCounting(arr, 8, 0xff);
    }

    public void sort8passInCounting(int[] arr) {
        // 最低四位为 1
        sortInCounting(arr, 4, 0x0f);
    }
}
