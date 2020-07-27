//
// Created by ywh on 2020/7/27.
//
#include <limits.h>

#define IS_DIGIT(c) ((c) >= '0' && (c) <= '9')

/**
 * 字符串转整数
 *
 * @param str
 * @return
 */
int myAtoi(char *str) {
    int negative = 0;
    char *start;
    long num = 0;

    for (; *str == ' '; str++);
    if (*str == '\0') {
        return 0;
    }

    if (*str == '+') {
        str++;
    } else if (*str == '-') {
        str++;
        negative = 1;
    }

    for (; *str == '0'; str++);
    start = str;
    for (; *str != '\0' && IS_DIGIT(*str); str++);
    if (start == str) {
        return 0;
    }
    if (str - start > 10) {
        return negative ? INT_MIN : INT_MAX;
    }
    for (char *i = start; i < str; ++i) {
        num = num * 10 + (*i - '0');
    }
    num = negative ? -num : num;

    if (num < INT_MIN) {
        return INT_MIN;
    } else if (num > INT_MAX) {
        return INT_MAX;
    } else {
        return (int) num;
    }
}