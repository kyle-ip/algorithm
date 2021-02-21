package leetcode120

// Time: O(n^2), Space: O(n)
func minimumTotal(triangle [][]int) int {
    n, dp := len(triangle), make([]int, len(triangle))
    for j := 0; j < n; j++ {
        dp[j] = triangle[n-1][j]
    }
    for i := n - 2; i >= 0; i-- {
        for j := 0; j <= i; j++ {
            dp[j] = min(dp[j], dp[j+1]) + triangle[i][j]
        }
    }
    return dp[0]
}

//
func min(num1, num2 int) int {
    if num1 < num2 {
        return num1
    }
    return num2
}