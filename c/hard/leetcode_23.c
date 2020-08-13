/**
 * Created by ywh on 11/08/2020.
 */

#include <stdlib.h>
#include "../dataStructure.h"


/**
 *
 * @param lists
 * @param start
 * @param end
 * @return
 */
struct ListNode *merge(struct ListNode **lists, int start, int end) {
    if (start == end) {
        return lists[start];
    }
    if (start > end) {
        return NULL;
    }
    int mid = start + (end - start) / 2;
    struct ListNode *left = merge(lists, start, mid), *right = merge(lists, mid + 1, end);
    struct ListNode dummy, *p = &dummy;
    dummy.next = NULL;
    for (; left && right; p = p->next) {
        if (left->val < right->val) {
            p->next = left;
            left = left->next;
        } else {
            p->next = right;
            right = right->next;
        }
    }
    if (left) {
        p->next = left;
    }
    if (right) {
        p->next = right;
    }
    return dummy.next;
}


/**
 * 合并 K 个排序链表
 *
 * Time: O(n*log(k)), Space: O(log(k))
 *
 * @param lists
 * @param listsSize
 * @return
 */
struct ListNode *mergeKLists(struct ListNode **lists, int listsSize) {
    if (lists == NULL || listsSize == 0) {
        return NULL;
    }
    return merge(lists, 0, listsSize - 1);
}

/**
 * 合并 K 个排序链表
 *
 * Time: O(k*n), Space: O(1)
 *
 * @param lists
 * @param listsSize
 * @return
 */
struct ListNode *mergeKLists2(struct ListNode **lists, int listsSize) {
    if (lists == NULL || listsSize == 0) {
        return NULL;
    }
    struct ListNode *left = NULL;
    for (int i = 0; i < listsSize; i++) {
        struct ListNode dummy, *p = &dummy, *right = lists[i];
        dummy.next = NULL;
        for (; left && right; p = p->next) {
            if (left->val < right->val) {
                p->next = left;
                left = left->next;
            } else {
                p->next = right;
                right = right->next;
            }
        }
        if (left) {
            p->next = left;
        }
        if (right) {
            p->next = right;
        }
        left = dummy.next;
    }
    return left;
}