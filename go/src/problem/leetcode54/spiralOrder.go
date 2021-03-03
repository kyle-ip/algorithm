package leetcode54

func spiralOrder(matrix [][]int) []int {
	m, n := len(matrix), len(matrix[0])
	top, bottom, left, right := 0, m-1, 0, n-1
	ret := make([]int, 0)
	for true {
		for i := left; i <= right; i++ {
			ret = append(ret, matrix[top][i])
		}
		top++
		if top > bottom {
			break
		}
		for i := top; i <= bottom; i++ {
			ret = append(ret, matrix[i][right])
		}
		right--
		if right < left {
			break
		}
		for i := right; i >= left; i-- {
			ret = append(ret, matrix[bottom][i])
		}
		bottom--
		if bottom < top {
			break
		}
		for i := bottom; i >= top; i-- {
			ret = append(ret, matrix[i][left])
		}
		left++
		if left > right {
			break
		}
	}
	return ret
}
