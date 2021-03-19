package leetcode98

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func isValidBST(root *TreeNode) bool {
	return isValid(root, nil, nil)
}

func isValid(root *TreeNode, lower *TreeNode, upper *TreeNode) bool {
	if root == nil {
		return true
	}
	if lower != nil && lower.Val >= root.Val || upper != nil && upper.Val <= root.Val {
		return false
	}
	return isValid(root.Left, lower, root) && isValid(root.Right, root, upper)
}