package leetcode

func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
    k, n := 0, (len(nums1)+len(nums2))/2+1
    nums := make([]int, n)
    for i, j := 0, 0; k < n; k++ {
        if i < len(nums1) && j < len(nums2) {
            if nums1[i] < nums2[j] {
                nums[k] = nums1[i]
                i++
            } else {
                nums[k] = nums2[j]
                j++
            }
        } else if i < len(nums1) {
            nums[k] = nums1[i]
            i++
        } else if j < len(nums2) {
            nums[k] = nums2[j]
            j++
        }
    }
    if (len(nums1)+len(nums2))%2 == 1 {
        return float64(nums[k-1])
    } else {
        return float64(nums[k-1]+nums[k-2]) / 2
    }
}
