package leetcode221

func maximalSquare(matrix [][]byte) int {
    if matrix == nil || len(matrix) == 0 || matrix[0] == nil || len(matrix[0]) == 0 {
        return 0
    }
    m, n := len(matrix), len(matrix[0])
    dp := make([][]int, m)
    for i := 0; i < m; i++ {
        dp[i] = make([]int, n)
    }
    length := 0
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if i == 0 || j == 0 || matrix[i][j] == '0' {
                if matrix[i][j] == '0' {
                    dp[i][j] = 0
                } else {
                    dp[i][j] = 1
                }
            } else {
                dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
            }
            length = max(length, dp[i][j])
        }
    }
    return length * length
}

// FIXME
func maximalSquare2(matrix [][]byte) int {
    if matrix == nil || len(matrix) == 0 || matrix[0] == nil || len(matrix[0]) == 0 {
        return 0
    }
    m, n, ret := len(matrix), len(matrix[0]), 0
    heights := make([]int, n)
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if matrix[i][j] == '1' {
                heights[j] += 1
            } else {
                heights[j] = 0
            }
        }
        ret = max(ret, largestSquareLengthInHistogram(heights))
    }
    return ret
}

func largestSquareLengthInHistogram(heights []int) int {
    if heights == nil || heights[0] == 0{
        return 0
    }
    length := 0
    for i := 0; i < len(heights); i++ {
        l, r := i, i
        for l >= 0 && heights[i] <= heights[l] {
            l--
        }
        for r < len(heights) && heights[i] <= heights[r] {
            r++
        }
        length = max(length, min(l - r + 1, heights[i]))
    }
    return length * length
}

func min(nums ...int) int {
    ret := nums[0]
    for _, v := range nums {
        if v < ret {
            ret = v
        }
    }
    return ret
}

func max(nums ...int) int {
    ret := nums[0]
    for _, v := range nums {
        if v > ret {
            ret = v
        }
    }
    return ret
}