package com.ywh.problem.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import static java.util.Comparator.comparingInt;

/**
 * 最接近原点的 K 个点
 * [堆] [排序] [分治]
 *
 * @author ywh
 * @since 2020/11/9/009
 */
public class LeetCode973 {

    /**
     * 大顶堆解法，求 Top K
     *
     * Time: O(n*log(K)), Space: O(K)
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        // 大顶堆：按元组第一个值从大到小排列。
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((arr1, arr2) -> arr2[0] - arr1[0]);

        // 先放入 K 个由距离和下标组成的元组。
        for (int i = 0; i < K; i++) {
            maxHeap.add(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        // 把剩余的 points.length - K 个放入，如果距离比堆顶的距离小则替换。
        for (int i = K; i < points.length; i++) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (dist < maxHeap.peek()[0]) {
                maxHeap.poll();
                maxHeap.add(new int[]{dist, i});
            }
        }
        // 填充返回数据。
        int[][] ret = new int[K][2];
        for (int i = 0; i < K; i++) {
            ret[i] = points[maxHeap.poll()[1]];
        }
        return ret;
    }

    /**
     * 排序解法
     *
     * Time: O(n*log(n)), Space: O(log(n))
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest2(int[][] points, int K) {
        // 对距离按从小到大排列，返回前 K 个。
        Arrays.sort(points, Comparator.comparingInt(point -> (point[0] * point[0] + point[1] * point[1])));
        return Arrays.copyOfRange(points, 0, K);
    }


    /**
     * 快速选择解法（参考快排 {@link com.ywh.sorting.QuickSort #lomutoPartition}）
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest3(int[][] points, int K) {
        randomSelect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    Random rand = new Random();

    /**
     * 快排中的划分操作每次执行完后都把数组分成两部分，左侧小于 pivot，右侧大于 pivot。
     * 区别在于根据 K 与 pivot 下标位置关系，只处理划分结果的一部分。
     *
     * @param points
     * @param left
     * @param right
     * @param K
     */
    public void randomSelect(int[][] points, int left, int right, int K) {
        int pivotId = left + rand.nextInt(right - left + 1);
        int pivot = points[pivotId][0] * points[pivotId][0] + points[pivotId][1] * points[pivotId][1];
        swap(points, right, pivotId);
        int i = left - 1;
        for (int j = left; j < right; ++j) {
            int dist = points[j][0] * points[j][0] + points[j][1] * points[j][1];
            if (dist <= pivot) {
                ++i;
                swap(points, i, j);
            }
        }
        ++i;
        swap(points, i, right);
        // [left, i-1] 都小于等于 pivot, [i+1, right] 都大于 pivot
        if (K < i - left + 1) {
            randomSelect(points, left, i - 1, K);
        } else if (K > i - left + 1) {
            randomSelect(points, i + 1, right, K - (i - left + 1));
        }
        // 如果 K == i - left + 1，表示 left 部分即为 K 个元素，结束整个过程。
    }

    public void swap(int[][] points, int index1, int index2) {
        int[] temp = points[index1];
        points[index1] = points[index2];
        points[index2] = temp;
    }
}
