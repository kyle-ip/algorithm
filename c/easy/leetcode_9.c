//
// Created by ywh on 2020/7/21.
//
#include <stdbool.h>
#include <limits.h>

static int reverse(int x) {
    int y = 0;
    while (x != 0) {
        if (y < INT_MIN / 10 || y > INT_MAX / 10) {
            return 0;
        }
        y = y * 10 + x % 10;
        x /= 10;
    }
    return y;
}

/**
 * 回文数字判断
 *
 * @param x
 * @return
 */
static bool isPalindrome(int x) {
    if (x == 0) {
        return true;
    }
    if (x < 0) {
        return false;
    }
    return x == reverse(x);
}