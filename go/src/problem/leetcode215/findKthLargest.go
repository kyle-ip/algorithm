package leetcode215

func findKthLargest(nums []int, k int) int {
    low, high := 0, len(nums)-1
    for low <= high {
        pivot, i, j := nums[low], low, high
        for i < j {
            for i < j && nums[j] < pivot {
                j--
            }
            if i < j {
                nums[i], nums[j] = nums[j], nums[i]
            }
            for i < j && nums[i] >= pivot {
                i++
            }
            if i < j {
                nums[i], nums[j] = nums[j], nums[i]
            }
        }
        if i == k-1 {
            return nums[i]
        } else if i < k-1 {
            low = i + 1
        } else {
            high = i - 1
        }
    }
    return -1
}
