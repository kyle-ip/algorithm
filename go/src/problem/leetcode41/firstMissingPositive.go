package leetcode41

func firstMissingPositive(nums []int) int {
	if nums == nil || len(nums) == 0 {
		return 1
	}
	n := len(nums)
	for i := 0; i < n; {
		num := nums[i]
		if num >= 1 && num <= n && nums[num-1] != num {
			nums[num-1], nums[i] = nums[i], nums[i-1]
		} else {
			i++
		}
	}
	for i := 0; i < n; i++ {
		if nums[i] != i+1 {
			return i + 1
		}
	}
	return n + 1
}
