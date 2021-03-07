package leetcode105

type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func buildTreeRecursive(pre []int, preStartIdx int, preEndIdx int, inStartIdx int, inPos map[int]int) *TreeNode {
    if preStartIdx > preEndIdx {
        return nil
    }
    rootVal := pre[preStartIdx]
    inRootIdx := inPos[rootVal]
    leftLen := inRootIdx - inStartIdx
    return &TreeNode{
        Val: rootVal,
        Left: buildTreeRecursive(pre, preStartIdx+1, preStartIdx+leftLen, inStartIdx, inPos),
        Right: buildTreeRecursive(pre, preStartIdx+leftLen+1, preEndIdx, inRootIdx+1, inPos),
    }
}

func buildTree(preorder []int, inorder []int) *TreeNode {
    inPos := make(map[int]int)
    for i, v := range inorder {
        inPos[v] = i
    }
    return buildTreeRecursive(preorder, 0, len(preorder)-1, 0, inPos)
}
