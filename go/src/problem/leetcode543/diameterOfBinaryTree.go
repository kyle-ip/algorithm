package leetcode543

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func diameterOfBinaryTree(root *TreeNode) int {
	ret := 0
	maxDepth(root, &ret)
	return ret
}

func maxDepth(root *TreeNode, ret *int) int {
	if root == nil {
		return 0
	}
	left, right := maxDepth(root.Left, ret), maxDepth(root.Right, ret)
	*ret = max(*ret, left + right)
	return max(left, right) + 1
}

func max(nums ...int) int {
	ret := nums[0]
	for _, v := range nums {
		if v > ret {
			ret = v
		}
	}
	return ret
}