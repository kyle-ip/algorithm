//
// Created by ywh on 2020/7/21.
//
#include <string.h>

int mapping(char c) {
    switch (c) {
        case 'I':
            return 1;
        case 'V':
            return 5;
        case 'X':
            return 10;
        case 'L':
            return 50;
        case 'C':
            return 100;
        case 'D':
            return 500;
        case 'M':
            return 1000;
        default:
            return 0;
    }
}

/**
 * 罗马数字转阿拉伯数字
 *
 * @param s
 * @return
 */
int romanToInt(char *s) {
    size_t n = strlen(s), cur, right;
    int ret = mapping(s[n - 1]);
    for (size_t i = n - 2; i >= 0; i--) {
        cur = mapping(s[i]);
        right = mapping(s[i + 1]);
        if (cur >= right) {
            ret += cur;
        } else {
            ret -= cur;
        }
    }
    return ret;
}