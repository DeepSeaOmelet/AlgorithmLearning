package com.test.jie.leetCode.Daily.Y2022.mon9;

/**
 * 2022/09/07
 * https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/
 */
public class JuZhenZhongDeLuJingLcof {
    public static void main(String[] args) {

    }

    int m, n;

    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] words, int x, int y, int idx) {
        if (x == m || x < 0 || y == n || y < 0  || words[idx] != board[x][y]) {
            return false;
        }

        if (idx == words.length - 1) {
            return true;
        } else {
            board[x][y] = '0';
            boolean ans = dfs(board, words, x + 1, y, idx + 1) || dfs(board, words, x - 1, y, idx + 1)
                    || dfs(board, words, x, y + 1, idx + 1) || dfs(board, words, x, y - 1, idx + 1);
            board[x][y] = words[idx];
            return ans;
        }
    }
}