package leetcode22

func generate(ret *[]string, str string, left int, right int) {
    if left == 0 && right == 0 {
        *ret = append(*ret, str)
    } else {
        if left > 0 {
            generate(ret, str+"(", left-1, right)
        }
        if right > left {
            generate(ret, str+")", left, right-1)
        }
    }
}

func generateParenthesis(n int) []string {
    if n <= 0 {
        return nil
    }
    // ret := make([]string, 0)
    ret := []string{}
    generate(&ret, "", n, n)
    return ret
}
