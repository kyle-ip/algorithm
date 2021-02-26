package leetcode440

func findKthNumber(n int, k int) int {
    num := 1
    for p := 1; p < k; {
        count := getPrefixNodeCount(num, n)
        if count + p > k {
            num *= 10
            p++
        } else {
            num += 1
            p += count
        }
    }
    return num
}

func getPrefixNodeCount(prefix, n int) int {
    count, cur, next := 0, prefix, prefix + 1
    for cur <= n {
        count += min(next, n + 1) - cur
        cur *= 10
        next *= 10
    }
    return count
}

func min(num1, num2 int) int {
    if num1 < num2 {
        return num1
    }
    return num2
}