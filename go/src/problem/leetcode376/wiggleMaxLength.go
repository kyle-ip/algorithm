package leetcode376

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}

// Time: O(n), Space: O(1)
func wiggleMaxLength(nums []int) int {
    up, down := 1, 1
    for i := 1; i < len(nums); i++ {
        if nums[i] > nums[i - 1] {
            up = down + 1
        } else {
            down = up + 1
        }
    }
    return max(up, down)
}