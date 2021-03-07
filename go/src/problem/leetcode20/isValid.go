package leetcode20

func isValid(s string) bool {
    if len(s) == 0 {
        return true
    }
    stack := make([]rune, 0)
    for _, v := range s {
        switch v {
        case '(':
            stack = append(stack, ')')
        case '[':
            stack = append(stack, ']')
        case '{':
            stack = append(stack, '}')
        default:
            if len(stack) == 0 || stack[len(stack) - 1] != v {
                return false
            }
            stack = stack[:len(stack) - 1]
        }
    }
    return len(stack) == 0
}
