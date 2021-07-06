package leetcode1

// Time: O(n), Space: O(n)
func twoSum(nums []int, target int) []int {
    m := make(map[int]int)
    for i := 0; i < len(nums); i++ {
        num2 := target -nums[i]
        if idx2, ok := m[num2]; ok {
            return []int{idx2, i}
        }
        m[nums[i]] = i
    }
    return nil
}