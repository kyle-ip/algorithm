package leetcode304

type NumMatrix struct {
    PrefixSum [][]int
}

func Constructor(matrix [][]int) NumMatrix {
    if matrix == nil || len(matrix) == 0 || matrix[0] == nil || len(matrix[0]) == 0 {
        prefixSum := make([][]int, 1)
        for i := range prefixSum {
            prefixSum[i] = make([]int, 1)
        }
        return NumMatrix{PrefixSum: prefixSum}
    }
    m, n:= len(matrix), len(matrix[0])
    prefixSum := make([][]int, m+1)
    for i := range prefixSum {
        prefixSum[i] = make([]int, n+1)
    }
    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            prefixSum[i][j] = prefixSum[i][j - 1] + prefixSum[i - 1][j] - prefixSum[i - 1][j - 1] + matrix[i - 1][j - 1]
        }
    }
    return NumMatrix{PrefixSum: prefixSum}
}


func (this *NumMatrix) SumRegion(row1 int, col1 int, row2 int, col2 int) int {
    return this.PrefixSum[row2 + 1][col2 + 1] -
        this.PrefixSum[row2 + 1][col1] -
        this.PrefixSum[row1][col2 + 1] +
        this.PrefixSum[row1][col1]
}