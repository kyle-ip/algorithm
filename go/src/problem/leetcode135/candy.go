package leetcode135

func candy(ratings []int) int {
	n, cnt := len(ratings), make([]int, len(ratings))
	for i := 0; i < n; i++ {
		if i > 0 && ratings[i] > ratings[i-1] {
			cnt[i] = cnt[i-1] + 1
		} else {
			cnt[i] = 1
		}
	}
	right, ret := 0, 0
	for i := n-1; i >= 0; i-- {
		if i < n-1 && ratings[i] > ratings[i+1] {
			right++
		} else {
			right = 1
		}
		ret += max(right, cnt[i])
	}
	return ret
}

func max(num1, num2 int) int {
	if num1 > num2 {
		return num1
	}
	return num2
}