package leetcode95

type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func generateBSTree(low, high int) []*TreeNode {
    ret := []*TreeNode{}
    if low > high {
        return append(ret, nil)
    }
    if low == high {
        return append(ret, &TreeNode{Val: low})
    }
    for i := low; i <= high; i++ {
        left, right := generateBSTree(low, i-1), generateBSTree(i+1, high)
        for _, l := range left {
            for _, r := range right {
                ret = append(ret, &TreeNode{Val: i, Left: l, Right: r})
            }
        }
    }
    return ret
}

// Time: O(4^n / n^(3/2)), Space: O(4^n / n^(3/2))
func generateTrees(n int) []*TreeNode {
    if n < 1 {
        return make([]*TreeNode, 0)
    }
    return generateBSTree(1, n)
}
