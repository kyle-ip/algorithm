//
// Created by ywh on 2020/7/20.
//
#include <limits.h>

/**
 * 反转整数
 *
 * @param x
 * @return
 */
static int reverse(int x) {
    int y = 0 ;
    while (x != 0) {
        if (y < INT_MIN / 10 || y > INT_MAX / 10) {
            return 0;
        }
        y = y * 10 + x % 10;
        x /= 10;
    }
    return y;
}