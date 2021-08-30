package com.ywh.problem.leetcode.hard;

import java.util.*;

/**
 * 计算右侧小于当前元素的个数
 * [排序] [树状数组] [线段树] [二分查找] [分治]
 * 
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * 示例：
 *      输入：nums = [5,2,6,1]
 *      输出：[2,1,1,0]
 * 解释：
 *      5 的右侧有 2 个更小的元素 (2 和 1)
 *      2 的右侧仅有 1 个更小的元素 (1)
 *      6 的右侧有 1 个更小的元素 (1)
 *      1 的右侧有 0 个更小的元素
 * 提示：
 *      0 <= nums.length <= 10^5
 *      -10^4 <= nums[i] <= 10^4
 *
 * TODO 暂时未理解
 *
 * @author ywh
 * @since 4/21/2021
 */
public class LeetCode315 {

    private int[] temp, tempIndex;

    /**
     * 归并排序
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller1(int[] nums) {
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        temp = new int[nums.length];
        tempIndex = new int[nums.length];
        int[] ret = mergeSort(nums, 0, nums.length - 1, index, new int[nums.length]);
        List<Integer> list = new ArrayList<>();
        for (int num : ret) {
            list.add(num);
        }
        return list;
    }

    /**
     *
     * @param nums
     * @param l
     * @param r
     */
    public int[] mergeSort(int[] nums, int l, int r, int[] index, int[] ret) {
        if (l >= r) {
            return ret;
        }
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid, index, ret);
        mergeSort(nums, mid + 1, r, index, ret);

        // 把较小的元素添加到结果集并移动指针，如下图中三个 7 对 8 的右边比 8 小的元素的贡献为 3。
        // L = [8, 12, 16, 22, 100]   R = [7, 7, 7, 26, 55, 64, 91]  M = [7, 7, 7]
        //      |                                   |
        //      l                                   r
        // 由于在并的过程中 8 的位置一直发生改变，因此引入数组 index 记录每个数字对应的原数组中的下标，比如：
        //          a = [8, 9, 1, 5, 2]
        //      index = [0, 1, 2, 3, 4]
        // 排序的时候原数组和这个下标数组同时变化，则排序后得到这样的两个数组：
        //          a = [1, 2, 5, 8, 9]
        //      index = [2, 4, 3, 0, 1]
        // 用一个数组 ret 来记录贡献。对某个元素计算贡献时，如果它对应的下标为 p，只需要在 ans[p] 上加上贡献即可。


        for (int i = l, j = mid + 1, p = l; i <= mid || j <= r; p++) {
            if (i > mid) {
                temp[p] = nums[j];
                tempIndex[p] = index[j++];
            } else if (j > r) {
                temp[p] = nums[i];
                tempIndex[p] = index[i];
                ret[index[i++]] += (j - mid - 1);
            } else if (nums[i] > nums[j]) {
                temp[p] = nums[j];
                tempIndex[p] = index[j++];
            } else {
                temp[p] = nums[i];
                tempIndex[p] = index[i];
                ret[index[i++]] += (j - mid - 1);
            }
        }
        for (int k = l; k <= r; ++k) {
            index[k] = tempIndex[k];
            nums[k] = temp[k];
        }
        return ret;
    }

    /**
     * 树状数组
     *
     * Time: O(n*log(n)), Space: O(n)
     * @param nums
     * @return
     */
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> ret = new ArrayList<>();

        // discretization
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        int[] a = new int[size];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        Arrays.sort(a);

        // init
        int[] c = new int[nums.length + 5];
        Arrays.fill(c, 0);

        for (int i = nums.length - 1; i >= 0; --i) {
            int id = Arrays.binarySearch(a, nums[i]) + 1;

            // query
            int queryRet = 0, pos = id - 1;
            while (pos > 0) {
                queryRet += c[pos];
                pos -= pos & (-pos);
            }
            ret.add(queryRet);

            // update
            while (id < c.length) {
                c[id] += 1;
                id += id & (-id);
            }
        }
        Collections.reverse(ret);
        return ret;
    }
}
