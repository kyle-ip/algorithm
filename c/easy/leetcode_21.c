/**
 * Created by ywh on 09/08/2020.
 */

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
    while (l1 != NULL && l2 != NULL) {
        if (l1->val < l2->val) {
            p->next = l1;
            l1 = l1->next;
        } else {
            p->next = l2;
            l2 = l2->next;
        }
        p = p->next;
    }
    if (l1 != NULL) {
        p->next = l1;
    }
    if (l2 != NULL) {
        p->next = l2;
    }
    return dummy.next;
}