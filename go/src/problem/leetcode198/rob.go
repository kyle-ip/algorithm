package leetcode198

func rob(nums []int) int {
	if nums == nil || len(nums) == 0 {
		return 0
	}
	x0, x1 := 0, 0
	for _, v := range nums {
		tmp := x0 + v
		if x1 > tmp {
			tmp = x1
		}
		x0 = x1
		x1 = tmp
	}
	return x1
}