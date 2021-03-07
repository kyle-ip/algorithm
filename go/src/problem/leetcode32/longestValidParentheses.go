package leetcode32

func longestValidParentheses(s string) int {
    if len(s) == 0 {
        return 0
    }
    l, n, ret := 0, len(s), 0
    dp := make([]int, n+1)
    for i := 0; i < n; i++ {
        if s[i] == '(' {
            l++
        } else if l > 0 {
            l--
            dp[i] = dp[i-1] + 2
            if i >= dp[i] {
                dp[i] += dp[i-dp[i]]
            }
            ret = max(ret, dp[i])
        }
    }
    return ret
}

func max(num1, num2 int) int {
    if num1 > num2 {
        return num1
    }
    return num2
}
