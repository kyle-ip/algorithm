package leetcode10

func isMatch(s string, p string) bool {
	matches := func(i, j int) bool {
		return i > 0 && (p[j-1] == '.' || s[i-1] == p[j-1])
	}
	m, n := len(s), len(p)
	dp := make([][]bool, m + 1)
	for i := 0; i <= m; i++ {
		dp[i] = make([]bool, n+1)
	}
	dp[0][0] = true
	for i := 0; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if p[j-1] == '*' {
				dp[i][j] = matches(i, j-1) && dp[i-1][j] || dp[i][j-2]
			} else {
				dp[i][j] = matches(i, j) && dp[i-1][j-1]
			}
		}
	}
	return dp[m][n]
}

