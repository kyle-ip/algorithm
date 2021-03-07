package leetcode415

import "strconv"

func addStrings(num1 string, num2 string) string {
    i, j := len(num1)-1, len(num2)-1
    carry := 0
    ret := ""
    for i >= 0 || j >= 0 || carry > 0 {
        if i >= 0 {
            carry += int(num1[i] - '0')
            i--
        }
        if j >= 0 {
            carry += int(num2[j] - '0')
            j--
        }
        ret = strconv.Itoa(carry%10) + ret
        carry /= 10
    }
    return ret
}

func max(nums ...int) int {
    ret := nums[0]
    for _, v := range nums {
        if v > ret {
            ret = v
        }
    }
    return ret
}
