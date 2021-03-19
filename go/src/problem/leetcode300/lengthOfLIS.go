package leetcode300

func lengthOfLIS(nums []int) int {
	if nums == nil || len(nums) == 0 {
		return 0
	}
	n, length := len(nums), 0
	d := make([]int, n)
	for _, num := range nums {
		pos, low, high := 0, 0, length - 1
		for {
			if low > high {
				pos = low
				break
			}
			mid := low + (high - low) / 2
			if num == d[mid] {
				pos = mid
				break
			}
			if num < d[mid] {
				high = mid - 1
			} else {
				low = mid + 1
			}
		}
		d[pos] = num
		if pos == length {
			length++
		}
	}
	return length
}