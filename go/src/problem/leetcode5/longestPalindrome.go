package leetcode5

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
    max := func(num1, num2 int) int {
        if num1 > num2 {
            return num1
        }
        return num2
    }
    start, maxLen := 0, 0
    for i := 0; i < len(s); i++ {
        curLen := max(expand(i, i), expand(i, i + 1))
        if curLen > maxLen {
            maxLen = curLen
            start = i - (curLen - 1) / 2
        }
    }
    return s[start: start + maxLen]
}
