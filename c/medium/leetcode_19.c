//
// Created by ywh on 2020/8/5.
//
#include <stdlib.h>
#include "../dataStructure.h"

/**
 * 移除单链表倒数第 n 个节点
 *
 * @param head
 * @param n
 * @return
 */
static struct ListNode *removeNthFromEnd(struct ListNode *head, int n) {
    if (n <= 0) {
        head = NULL;
    }
    struct ListNode *fast, *slow, dummy;
    dummy.next = head;
    for (fast = &dummy; fast != NULL && n > 0; n--, fast = fast->next);
    if (n > 0) {
        return dummy.next;
    }
    for (slow = &dummy; fast != NULL && fast->next != NULL; fast = fast->next, slow = slow->next);
    slow->next = slow->next->next;
    return dummy.next;
}