package leetcode42

func trap(height []int) int {
    max := func(num1, num2 int) int {
        if num1 > num2 {
            return num1
        }
        return num2
    }
    l, r, leftMax, rightMax, ret := 0, len(height) - 1, 0, 0, 0
    for l <= r {
        leftMax, rightMax = max(leftMax, height[l]), max(rightMax, height[r])
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
