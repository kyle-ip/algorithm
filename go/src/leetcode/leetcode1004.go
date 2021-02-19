package leetcode

func longestOnes(A []int, K int) int {
    n, l, lsum, rsum, ret := len(A), 0, 0, 0, 0
    for r := 0; r < n; r++ {
        rsum += 1 - A[r]
        for ; rsum - lsum > K; l++ {
            lsum += 1 - A[l]
        }
        cur := r - l + 1
        if cur > ret {
            ret = cur
        }
    }
    return ret
}
