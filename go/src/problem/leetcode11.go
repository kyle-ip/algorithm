package leetcode

func maxArea(height []int) int {
    ret := 0
    for l, r := 0, len(height) - 1; l < r; {
        w, h := r - l, 0
        if height[l] < height[r] {
            h = height[l]
            l++
        } else {
            h = height[r]
            r--
        }

        area := w * h
        if area > ret {
            ret = area
        }
    }
    return ret

}