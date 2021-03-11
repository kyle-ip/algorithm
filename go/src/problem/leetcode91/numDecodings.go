package leetcode91

func numDecodings(s string) int {
	c1, c2 := 1, 1
	if s[0] == '0' {
		c2 = 0
	}
	for i := 2; i <= len(s); i++ {
		cur := 0
		if s[i-1] != '0' {
			cur += c2
		}
		if isValidTwoDigit(s[i-2], s[i-1]) {
			cur += c1
		}
		c1, c2 = c2, cur
	}
	return c2
}

func isValidTwoDigit(a, b byte) bool {
	return (a == '1' && b >= '0' && b <= '9') || (a == '2' && b >= '0' && b <= '6')
}
