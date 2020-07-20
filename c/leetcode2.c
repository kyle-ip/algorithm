//
// Created by ywh on 2020/7/20.
//
#include <stdlib.h>
#include "dataStructure.h"

struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
    int carry = 0, sum;
    struct ListNode dummy;
    struct ListNode *cur = l1, *prev = &dummy;
    dummy.next = cur;
    while (l1 || l2) {
        sum = 0;
        if (l1) {
            sum += l1->val;
            l1 = l1->next;
        }
        if (l2) {
            if (!cur) {
                prev->next = l2;
                cur = l2;
            }
            sum += l2->val;
            l2 = l2->next;
        }
        sum += carry;
        carry = sum / 10;
        cur->val = sum % 10;
        prev = cur;
        cur = cur->next;
    }
    if (carry) {
        cur = malloc(sizeof(*cur));
        cur->val = carry;
        cur->next = NULL;
        prev->next = cur;
    }
    return dummy.next;
}