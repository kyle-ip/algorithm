package com.ywh.problem.leetcode.medium;

import com.ywh.model.RandomListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 含随机指针的链表拷贝
 * [链表] [哈希表]
 *
 * @author ywh
 * @since 21/12/2019
 */
public class LeetCode138 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param head
     * @return
     */
    public RandomListNode copyListWithRandomPointer(RandomListNode head) {
        if (head == null) {
            return null;
        }

        // 试用哈希表存放原链表与复制链表的映射
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode copyHead = new RandomListNode(head.val);
        map.put(head, copyHead);

        // 第一次循环，创建完整的复制链表；
        // 由于第一次遍历时复制链表还不存在，所以无法获取其 random 域，因此先以哈希表保存映射关系，便于下一次循环时取出、设置 random 域
        for (RandomListNode p = head, q = copyHead; p != null; p = p.next, q = q.next) {
            if (p.next != null) {
                q.next = new RandomListNode(p.next.val);
            }
            map.put(p, q);
        }

//        for (RandomListNode p = head.next, q = copyHead; p != null; p = p.next, q = q.next) {
//            q.next = new RandomListNode(p.val);
//            map.put(p, q.next);
//        }

        // 第二次循环，设置 random 域
        for (RandomListNode p = head, q = copyHead; p != null; p = p.next, q = q.next) {
            // p.random 不为空，则与 p.random 映射的、q.random 也存在，从哈希表中取出即可
            if (p.random != null) {
                q.random = map.get(p.random);
            }
        }

        return copyHead;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param head
     * @return
     */
    public RandomListNode copyListWithRandomPointerO1(RandomListNode head) {
        if (head == null) {
            return null;
        }

        // 第一次循环，在原链表的每个节点后面都插入一个节点
        for (RandomListNode p = head; p != null; p = p.next.next) {
            RandomListNode copy = new RandomListNode(p.val);
            copy.next = p.next;
            p.next = copy;
        }

        // 第二次循环，判断原链表的节点：如果存在 random 域，则为其后继节点也添加上
        for (RandomListNode p = head; p != null; p = p.next.next) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
        }

        // 第三次循环，隔着节点拆解出复制链表（还原原链表）
        RandomListNode dummy = new RandomListNode(0);
        for (RandomListNode cur = head, copyCur = dummy; cur != null; copyCur = copyCur.next, cur = cur.next) {
            copyCur.next = cur.next;
            cur.next = cur.next.next;
        }

        return dummy.next;
    }
}
