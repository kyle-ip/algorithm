package leetcode24

type ListNode struct {
    Val  int
    Next *ListNode
}

func swapPairs(head *ListNode) *ListNode {
    if head.Next == nil || head.Next.Next == nil {
        return nil
    }
    next := head.Next
    head.Next = swapPairs(next.Next)
    next.Next = head
    return next
}

func swapPairs2(head *ListNode) *ListNode {
    dummy := ListNode{Next: head}
    pre := &dummy
    for pre.Next != nil && pre.Next.Next != nil {
        first, second := pre.Next, pre.Next.Next
        pre.Next = second
        first.Next = second.Next
        second.Next = first
        pre = first
    }
    return dummy.Next
}
