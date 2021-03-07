package leetcode79

func dfs(board [][]byte, visited [][]bool, i int, j int, word string, idx int) bool {
    if idx == len(word) {
        return true
    }
    if i < 0 || i >= len(board) || j < 0 || j >= len(board[0]) || visited[i][j] == true || board[i][j] != word[idx] {
        return false
    }
    visited[i][j] = true
    existed := dfs(board, visited, i-1, j, word, idx+1) ||
        dfs(board, visited, i+1, j, word, idx+1) ||
        dfs(board, visited, i, j-1, word, idx+1) ||
        dfs(board, visited, i, j+1, word, idx+1)
    visited[i][j] = false
    return existed
}

func exist(board [][]byte, word string) bool {
    if board == nil || len(board) == 0 || board[0] == nil || len(board[0]) == 0 {
        return false
    }
    m, n := len(board), len(board[0])
    visited := make([][]bool, m)
    for i := 0; i < m; i++ {
        visited[i] = make([]bool, n)
    }
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if board[i][j] != word[0] || visited[i][j] == true {
                continue
            }
            if dfs(board, visited, i, j, word, 0) {
                return true
            }
        }
    }
    return false
}
