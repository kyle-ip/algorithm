//
// Created by ywh on 2020/8/14.
//
#include "../dataStructure.h"

/**
 *
 * @param head
 * @return
 */
struct ListNode *swapPairs(struct ListNode *head) {
    if (!head || !head->next) {
        return head;
    }
    struct ListNode *next = head->next;
    head->next = swapPairs(next->next);
    next->next = head;
    return next;
}

/**
 *
 * @param head
 * @return
 */
struct ListNode *swapPairs2(struct ListNode *head) {
    struct ListNode dummy, *pre = &dummy;
    dummy.next = head;
    while (pre->next && pre->next->next) {
        struct ListNode *first = pre->next, *second = pre->next->next;
        pre->next = second;
        first->next = second->next;
        second->next = first;
        pre = first;
    }
}