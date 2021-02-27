package leetcode2

type ListNode struct {
    Val int
    Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
    dummy := &ListNode{Val: 0}
    carry, p := 0, dummy
    for l1 != nil || l2 != nil || carry > 0 {
        sum := carry
        if l1 != nil {
            sum += l1.Val
            l1 = l1.Next
        }
        if l2 != nil {
            sum += l2.Val
            l2 = l2.Next
        }
        p.Next = &ListNode{Val: sum % 10}
        p = p.Next
        carry = sum / 10
    }
    return dummy.Next
}