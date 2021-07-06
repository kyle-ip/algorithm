package leetcode2

type ListNode struct {
    Val int
    Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
    dummy := &ListNode{}
    for carry, p := 0, dummy; l1 != nil || l2 != nil || carry > 0; carry /= 10 {
        if l1 != nil {
            carry += l1.Val
            l1 = l1.Next
        }
        if l2 != nil {
            carry += l2.Val
            l2 = l2.Next
        }
        p.Next = &ListNode{Val: carry % 10}
        p = p.Next
    }
    return dummy.Next
}