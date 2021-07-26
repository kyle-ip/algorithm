package leetcode

func trap(height []int) int {
    max := func(num1, num2 int) int {
        if num1 > num2 {
            return num1
        }
        return num2
    }
    l, r, lMax, rMax, ret := 0, len(height) - 1, 0, 0, 0
    for l <= r {
        lMax, rMax = max(lMax, height[l]), max(rMax, height[r])
        if lMax < rMax {
            ret += lMax - height[l]
            l++
        } else {
            ret += rMax - height[r]
            r--
        }
    }
    return ret
}
