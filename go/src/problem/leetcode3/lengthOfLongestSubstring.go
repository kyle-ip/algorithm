package leetcode3

func lengthOfLongestSubstring(s string) int {
    l, r, ret, hash := 0, 0, 0, make([]int, 256)
    for i := 0; i < 255; i++ {
        hash[i] = -1
    }
    for ; r < len(s); r++ {
        l = max(l, hash[s[r]] + 1)
        ret = max(ret, r - l + 1)
        hash[s[r]] = r
    }
    return ret
}

func max(num1, num2 int) int {
    if num1 > num2 {
        return num1
    }
    return num2
}