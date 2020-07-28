//
// Created by ywh on 2020/7/28.
//

#define min(a, b) ((a) < (b)? (a): (b))
#define max(a, b) ((a) > (b)? (a): (b))

/**
 * 容纳最多水的凹槽容量
 *
 * @param height
 * @param heightSize
 * @return
 */
int maxArea(int *height, int heightSize) {
    int left = 0, right = heightSize - 1, ret = 0, s;
    while (left < right) {
        s = (right - left) * min(height[left], height[right]);
        ret = max(ret, s);
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    return ret;
}