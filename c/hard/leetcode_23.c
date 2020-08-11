/**
 * Created by ywh on 11/08/2020.
 */

#include <string.h>
#include <stdlib.h>
#include "../dataStructure.h"

/**
 *
 * @param l1
 * @param l2
 * @return
 */
struct ListNode *mergeTwoLists(struct ListNode *l1, struct ListNode *l2) {
    struct ListNode dummy, *p = &dummy;
    dummy.next = NULL;
    while (l1 && l2) {
        if (l1->val < l2->val) {
            p->next = l1;
            l1 = l1->next;
        } else {
            p->next = l2;
            l2 = l2->next;
        }
        p = p->next;
    }
    if (l1) {
        p->next = l1;
    }
    if (l2) {
        p->next = l2;
    }
    return dummy.next;
}

/**
 * FIXME
 *
 * @param lists
 * @param listsSize
 * @return
 */
struct ListNode *mergeKLists(struct ListNode **lists, int listsSize) {
    if (lists == NULL) {
        return NULL;
    }
    struct ListNode ret, *p = &ret;
    ret.next = NULL;
    for (int i = 0; i < listsSize; i++) {
        struct ListNode *tmp = mergeTwoLists(p, lists[i]);
        memcpy(p, tmp, sizeof(ret));
    }
    return p;
}