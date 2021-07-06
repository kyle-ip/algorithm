package leetcode3

func lengthOfLongestSubstring(s string) int {
    max := func(num1, num2 int) int {
        if num1 > num2 {
            return num1
        }
        return num2
    }
    ret := 0
    hash := make([]bool, 256)
    for l, r := 0, 0; r < len(s); r++ {
        for ; hash[s[r]]; l++ {
            hash[s[l]] = false
        }
        ret = max(ret, r - l + 1)
        hash[s[r]] = true
    }
    return ret
}
