package leetcode867

func transpose(matrix [][]int) [][]int {
    m, n := len(matrix), len(matrix[0])
    ret := make([][]int, n)
    for i := range ret {
        ret[i] = make([]int, m)
    }
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            ret[j][i] = matrix[i][j]
        }
    }
    return ret
}