//
// Created by ywh on 2020/7/20.
//
#ifndef LEETCODE_DATASTRUCTURE_H__
#define LEETCODE_DATASTRUCTURE_H__

struct Object {
    int val;
    int idx;
};

struct ListNode {
    int val;
    struct ListNode *next;
};

/**
 * 比较函数
 *
 * @param a
 * @param b
 * @return
 */
static int cmp(const void *a, const void *b);

#endif