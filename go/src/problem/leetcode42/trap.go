package leetcode42

func trap(height []int) int {
    leftMax, rightMax, l, r, ret := 0, 0, 0, len(height) - 1, 0
    for l <= r {
        leftMax = max(leftMax, height[l])
        rightMax = max(rightMax, height[r])
        if leftMax < rightMax {
            ret += leftMax - height[l]
            l++
        } else {
            ret += rightMax - height[r]
            r--
        }
    }
    return ret
}

func max(num1, num2 int) int {
    if num1 > num2 {
        return num1
    }
    return num2
}