package leetcode25

type ListNode struct {
    Val int
    Next *ListNode
}

func reverseKGroup(head *ListNode, k int) *ListNode {
    tail := head
    for i := 0; i < k; i++ {
        tail = tail.Next
        if tail == nil {
            return head
        }
    }
    newHead := reverse(head, tail)
    head.Next = reverseKGroup(tail, k)
    return newHead
}

func reverse(head, tail *ListNode) *ListNode {
    prev := tail
    for head != tail {
        next := head.Next
        head.Next = prev
        prev = head
        head = next
    }
    return prev
}