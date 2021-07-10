import java.util.*;

public class QuestionsDFS {
    // 200
    public void dfs(char[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = '0';
        int n = grid.length, m = grid[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1') {
                dfs(grid, r, c, dir);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, islands = 0;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, dir);
                    islands++;
                }
            }
        }
        return islands;
    }

    // 695

    public int dfs(int[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = 0;
        int n = grid.length, m = grid[0].length, size = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                size += dfs(grid, r, c, dir);
            }
        }
        return size + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length, maxSize = 0;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int ans = dfs(grid, i, j, dir);
                    maxSize = Math.max(maxSize, ans);
                }
            }
        }
        return maxSize;
    }

    // 463
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length, onceCount = 0, neighbourCount = 0;
        int[][] dir = { { 1, 0 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    onceCount++;
                    for (int d = 0; d < 2; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r < n && c < m && grid[r][c] == 1) {
                            neighbourCount++;
                        }
                    }
                }
            }
        }
        return ((4 * onceCount) - (2 * neighbourCount));
    }

    // 130
    public void dfs(char[][] board, int i, int j, int[][] dir) {
        board[i][j] = '$';
        int n = board.length, m = board[0].length;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 'O')
                dfs(board, r, c, dir);
        }
    }

    public void solve(char[][] board) {
        int n = board.length, m = board[0].length;
        int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && board[i][j] == 'O') {
                    dfs(board, i, j, dir);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '$')
                    board[i][j] = 'O';
                else
                    board[i][j] = 'X';
            }
        }
    }

    // 1091
    public int shortestPathBinaryMatrix(int[][] grid) {

    }

    // 542
    public int[][] updateMatrix(int[][] mat) {

    }

    // 886
    public boolean possibleBipartition(int n, int[][] dislikes) {

    }
}
