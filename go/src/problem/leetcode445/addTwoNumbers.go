package leetcode445

type ListNode struct {
    Val  int
    Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
    stack1, stack2 := make([]int, 0), make([]int, 0)
    for ; l1 != nil; l1 = l1.Next {
        stack1 = append(stack1, l1.Val)
    }
    for ; l2 != nil; l2 = l2.Next {
        stack2 = append(stack2, l2.Val)
    }
    carry := 0
    var p *ListNode = nil
    for len(stack1) > 0 || len(stack2) > 0 || carry > 0 {
        sum := carry
        if len(stack1) > 0 {
            sum += stack1[len(stack1)-1]
            stack1 = stack1[:len(stack1)-1]
        }
        if len(stack2) > 0 {
            sum += stack2[len(stack2)-1]
            stack2 = stack2[:len(stack2)-1]
        }
        node := ListNode{Val: sum % 10}
        node.Next = p
        p = &node
        carry = sum / 10
    }
    return p
}
