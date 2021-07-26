package leetcode

func longestPalindrome(s string) string {
    if len(s) == 0 {
        return s
    }
    expand := func(l, r int) int {
        for l >= 0 && r < len(s) && s[l] == s[r] {
            l--
            r++
        }
        return r - l - 1
    }
    start, maxLen := 0, 0
    for i := 0; i < len(s); i++ {
        l1, l2 := expand(i, i), expand(i, i+1)
        curLen := l1
        if l2 > l1 {
            curLen = l2
        }
        if curLen > maxLen {
            maxLen = curLen
            start = i - (curLen-1)/2
        }
    }
    return s[start : start+maxLen]
}

func longestPalindromeDP(s string) string {
    n, start, maxLen := len(s), 0, 0
    dp := make([][]bool, n)
    for i := 0; i < n; i++ {
        dp[i] = make([]bool, n)
    }
    for i := n - 1; i >= 0; i-- {
        for j := i; j < n; j++ {
            if i == j {
                dp[i][j] = true
            } else if i+1 == j {
                dp[i][j] = s[i] == s[j]
            } else {
                dp[i][j] = s[i] == s[j] && dp[i+1][j-1]
            }
            if dp[i][j] && j-i+1 > maxLen {
                maxLen = j - i + 1
                start = i
            }
        }
    }
    return s[start : start+maxLen]
}

func longestPalindromeDP2(s string) string {
    res, dp := "", make([][]bool, len(s))
    for i := 0; i < len(s); i++ {
        dp[i] = make([]bool, len(s))
    }
    for i := len(s) - 1; i >= 0; i-- {
        for j := i; j < len(s); j++ {
            dp[i][j] = (s[i] == s[j]) && ((j-i < 3) || dp[i+1][j-1])
            if dp[i][j] && (res == "" || j-i+1 > len(res)) {
                res = s[i : j+1]
            }
        }
    }
    return res
}
