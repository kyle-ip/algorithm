package leetcode132

func min(num1 int, num2 int) int {
    if num1 < num2 {
        return num1
    }
    return num2
}

func expand(s string, i int, j int, cut []int) {
    for ; i >= 0 && j < len(s) && s[i] == s[j]; {
        cut[j + 1] = min(cut[j + 1], cut[i] + 1)
    }
}

// Time: O(n^2), Space: O(n)
func minCut(s string) int {
    if len(s) == 0 {
        return -1
    }
    n, cut := len(s), make([]int, len(s))
    for i := 0; i <= n; i++ {
        cut[i] = i - 1
    }
    for i := 0; i < n; i++ {
        expand(s, i, i, cut)
        expand(s, i, i + 1, cut)
    }
    return cut[n]
}