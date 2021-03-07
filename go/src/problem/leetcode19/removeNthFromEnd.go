package leetcode19

type ListNode struct {
    Val  int
    Next *ListNode
}

func removeNthFromEnd(head *ListNode, n int) *ListNode {
    dummy := ListNode{Next: head}
    fast, slow := head, &dummy
    for ; fast != nil && n > 0; fast = fast.Next {
        n--
    }
    if n > 0 {
        return dummy.Next
    }
    for fast != nil {
        fast = fast.Next
        slow = slow.Next
    }
    slow.Next = slow.Next.Next
    return dummy.Next
}
