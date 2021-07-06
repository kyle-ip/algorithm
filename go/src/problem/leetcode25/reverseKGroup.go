package leetcode25

type ListNode struct {
    Val int
    Next *ListNode
}

func reverseKGroup(head *ListNode, k int) *ListNode {
    tail := head

    // 判断不足 k 个，直接返回 head。
    for i := 0; i < k; i++ {
        tail = tail.Next
        if tail == nil {
            return head
        }
    }

    // 反转 head-tail 一段，返回新 head。
    newHead := reverse(head, tail)

    // 递归反转尾部后续的 k 个，插在 head 的后面。
    head.Next = reverseKGroup(tail, k)
    return newHead
}

func reverse(head, tail *ListNode) *ListNode {
    prev := head
    for head != tail {
        next := head.Next
        head.Next = prev
        prev = head
        head = next
    }
    return prev
}