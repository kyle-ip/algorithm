package leetcode256

// Time: O(n), Space: O(1)
func minCost(costs [][]int) int {
    if costs == nil || len(costs) == 0 {
        return 0
    }
    preR, preG, preB := 0, 0, 0
    for i := 0; i < len(costs); i++ {
        preR = min(preG, preB) + costs[i][0]
        preG = min(preR, preB) + costs[i][1]
        preB = min(preR, preG) + costs[i][2]
    }
    return min(preR, preG, preB)
}

func min(nums ...int) int {
    ret := nums[0]
    for num := range nums {
        if num < ret {
            ret = num
        }
    }
    return ret
}
