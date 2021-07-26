package leetcode

func lengthOfLongestSubstring(s string) int {
    ret, hash := 0, make([]bool, 256)
    for l, r := 0, 0; r < len(s); r++ {
        for ; hash[s[r]]; l++ {
            hash[s[l]] = false
        }
        if r - l + 1 > ret {
            ret = r - l + 1
        }
        hash[s[r]] = true
    }
    return ret
}

func lengthOfLongestSubstring2(s string) int {
    ret := 0
    idx := make(map[byte]int)
    for l, r := 0, 0; r < len(s); r++ {
        if v, ok := idx[s[r]]; ok && l < v + 1 {
            l = v + 1
        }
        if ret < r - l + 1 {
            ret = r - l + 1
        }
        idx[s[r]] = r
    }
    return ret
}