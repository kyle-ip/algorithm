package leetcode9

func isPalindrome(x int) bool {
	if x < 0 {
		return false
	}
	y, tmp := 0, x
	for x > 0 {
		y = y * 10 + x % 10
		x /= 10
	}
	return tmp == y
}