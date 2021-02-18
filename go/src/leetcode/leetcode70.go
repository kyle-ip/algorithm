package leetcode

// Time: O(n), Space: O(n)
func climbStairs(n int) int {
    if n < 2 {
        return 1
    }
    return climbStairs(n-1) + climbStairs(n-2)
}

// Time: O(n), Space: O(1)
func climbStairs1(n int) int {
    x0, x1 := 1, 1
    for i := 1; i < n; i++ {
        x2 := x0 + x1
        x0 = x1
        x1 = x2
    }
    return x1
}
