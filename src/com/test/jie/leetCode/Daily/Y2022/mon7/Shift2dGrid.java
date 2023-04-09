package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.ArrayList;
import java.util.List;

/**
 * 2022/07/20
 * 1260. 二维网格迁移
 * 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
 * <p>
 * 每次「迁移」操作将会引发下述活动：
 * <p>
 * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
 * 请你返回 k 次迁移操作后最终得到的 二维网格。
 * <p>
 * 示例 1：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[9,1,2],[3,4,5],[6,7,8]]
 * 示例 2：
 * 输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * 示例 3：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * 输出：[[1,2,3],[4,5,6],[7,8,9]]
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 50
 * 1 <= n <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 */
public class Shift2dGrid {
    public static void main(String[] args) {

    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length;//行
        int m = grid[0].length;//列
        int[][] mat = new int[n][m];
        for (int i = 0; i < m; i++) {
            //定位当前列
            int tcol = (i + k) % m;
            //定位行
            int trow = ((i + k) / m) % n;
            int idx = 0;
            while (idx < n) {
                mat[(idx + trow) % n][tcol] = grid[idx++][i];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                list.add(mat[i][j]);
            }
            ans.add(list);
        }
        return ans;
    }
}