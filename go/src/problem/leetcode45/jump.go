package leetcode45

// Time: O(n), Space: O(1)
func jump(nums []int) int {
    steps, r, end := 0, 0, 0
    for i := 0; i < len(nums) - 1; i++ {
        r = max(r, i+nums[i])
        if i == end {
            end, steps = r, steps+1
        }
    }
    return steps
}

func max(num1, num2 int) int {
    if num1 > num2 {
        return num1
    }
    return num2
}
