package leetcode697

// Time: O(n), Space: O(n)
func findShortestSubArray(nums []int) int {
    freqs, maxCnt, minLen := map[int][]int{}, 0, len(nums)
    for i, num := range nums {
        if _, found := freqs[num]; found {
            freqs[num][0]++
            freqs[num][2] = i
        } else {
            freqs[num] = []int{1, i, i}
        }
        if maxCnt < freqs[num][0] {
            maxCnt = freqs[num][0]
        }
    }
    for _, indices := range freqs {
        if indices[0] == maxCnt && minLen > indices[2]-indices[1]+1 {
            minLen = indices[2] - indices[1] + 1
        }
    }
    return minLen
}
