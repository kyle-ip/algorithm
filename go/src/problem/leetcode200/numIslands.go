package leetcode200

func dfs(grid [][]byte, visited [][]bool, i int, j int) {
    m, n := len(grid), len(grid[0])
    if i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0' || visited[i][j] {
        return
    }
    visited[i][j] = true
    dfs(grid, visited, i - 1, j)
    dfs(grid, visited, i + 1, j)
    dfs(grid, visited, i, j - 1)
    dfs(grid, visited, i, j + 1)
}

func numIslands(grid [][]byte) int {
    if grid == nil || len(grid) == 0 || grid[0] == nil || len(grid[0]) == 0 {
        return 0
    }
    m, n, visited, num := len(grid), len(grid[0]), make([][]bool, len(grid)), 0
    for i := 0; i < m; i++ {
        visited[i] = make([]bool, n)
    }
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if grid[i][j] == '0' || visited[i][j] {
                continue
            }
            num += 1
            dfs(grid, visited, i, j)
        }
    }
    return num
}
