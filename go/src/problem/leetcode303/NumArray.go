package leetcode303

type NumArray struct {
    PrefixSum []int
}

func Constructor(nums []int) NumArray {
    if nums == nil || len(nums) == 0 {
        prefixSum := make([]int, 1)
        return NumArray{PrefixSum: prefixSum}
    }
    prefixSum := make([]int, len(nums)+1)
    for i := 1; i < len(nums); i++ {
        prefixSum[i] = prefixSum[i-1] + nums[i-1]
    }
    return NumArray{PrefixSum: prefixSum}
}

func (this *NumArray) SumRange(i int, j int) int {
    return this.PrefixSum[j+1] - this.PrefixSum[i]
}