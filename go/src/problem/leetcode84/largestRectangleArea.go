package leetcode84

func largestRectangleArea(heights []int) int {
	if heights == nil || len(heights) == 0 {
		return 0
	}
	ret, stack := 0, make([]int, 0)
	for r := 0; r <= len(heights); r++ {
		h := 0
		if r != len(heights) {
			h = heights[r]
		}
		for len(stack) != 0 && heights[stack[len(stack)-1]] > h {
			idx := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			l := -1
			if len(stack) > 0 {
				l = stack[len(stack)-1]
			}
			ret = max(ret, heights[idx] * (r - l - 1))
		}
		stack = append(stack, r)
	}
	return ret
}

func max(num1, num2 int) int {
	if num1 > num2 {
		return num1
	}
	return num2
}