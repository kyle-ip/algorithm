package leetcode


// Time: O(n), Space: O(n)
func twoSum(nums []int, target int) []int {
	m := make(map[int]int)
	for i := 0; i < len(nums); i++ {
		num2 := target - nums[i]
		if _, isExist := m[num2]; isExist {
			return []int{m[num2], i}
		}
		m[nums[i]] = i
	}
	return nil
}
