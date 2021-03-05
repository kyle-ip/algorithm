package leetcode148

type ListNode struct {
	Val  int
	Next *ListNode
}

func quickSort(start *ListNode, end *ListNode) {
    if start == end {
        return
    }
    l, r := start, start.Next
    for ; r != end; r = r.Next {
        if r.Val < start.Val {
            l = l.Next
            r.Val, l.Val = l.Val, r.Val
        }
    }
    l.Val, start.Val = start.Val, l.Val
    quickSort(start, l)
    quickSort(l.Next, end)
}

func sortList(head *ListNode) *ListNode {
    quickSort(head, nil)
    return head
}

func sortList2(head *ListNode) *ListNode {
    if head == nil || head.Next == nil {
        return head
    }
    slow, fast := head, head
    for fast.Next != nil && fast.Next.Next != nil {
        fast = fast.Next.Next
        slow = slow.Next
    }
    right := sortList2(slow.Next)
    slow.Next = nil
    left := sortList2(head)
    dummy := ListNode{}
    for p := &dummy; ; p = p.Next {
        if left != nil && right != nil {
            if left.Val < right.Val {
                p.Next, left = left, left.Next
            } else {
                p.Next, right = right, right.Next
            }
        } else {
            if left != nil {
                p.Next = left
                return dummy.Next
            } else {
                p.Next = right
                return dummy.Next
            }
        }
    }
}