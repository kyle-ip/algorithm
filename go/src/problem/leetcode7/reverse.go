package leetcode7

import "math"

func reverse(x int) int {
	y := 0
	for x != 0 {
		if y < math.MinInt32 / 10 || y > math.MaxInt32 / 10 {
			return 0
		}
		y = y * 10 + x % 10
		x /= 10
	}
	return y
}