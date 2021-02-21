package leetcode53

const MinInt = ^int(^uint(0) >> 1)

// Time: O(1), Space: O(n)
func maxSubArray(nums []int) int {
    if nums == nil || len(nums) == 0 {
        return 0
    }
    if len(nums) == 1 {
        return nums[0]
    }
    ret, cur := MinInt, 0
    for _, num := range nums {
        if cur >= 0 {
            cur += num
        } else {
            cur = num
        }
        if cur > ret {
            ret = cur
        }
    }
    return ret
}
