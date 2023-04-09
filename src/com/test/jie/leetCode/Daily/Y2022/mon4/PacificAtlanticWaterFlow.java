package com.test.jie.leetCode.Daily.Y2022.mon4;

import java.util.ArrayList;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
 * <p>
 * 示例 1：
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 示例 2：
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 * <p>
 * 提示：
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        PacificAtlanticWaterFlow flow = new PacificAtlanticWaterFlow();
        System.out.println(flow.pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}}));
    }

    //可以将思维逆转
    //整理题意，需要我们统计能够同时流向两片海域的格子。
    //
    //从源点（格子）流向汇点（海域）是按照高度从高到低（非严格）的规则，那么反过来从海域到格子则是按照从低到高（非严格）规则进行，同时本身处于边缘的格子与海域联通。
    //
    //因此我们可以使用两遍 DFS/BFS 进行求解：分别从与当前海域直接相连的边缘格子出发，统计能够流向当前海域的格子集合，两片海域求得的集合交集即是答案。
    //水往高处流
    int n, m;
    int[][] g;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        g = heights;
        n = g[0].length;
        m = g.length;
        boolean[][] res1 = new boolean[m][n], res2 = new boolean[m][n];
        for (int i = 0; i < m; i++) {//处理能偶水往高处流的各种
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dfs(i, j, res1);//太平洋
                }
                if (i==m-1||j==n-1){
                    dfs(i,j,res2);//大西洋
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {//判断是否都能符合题意
            for (int j = 0; j < n; j++) {
                if (res1[i][j] && res2[i][j]){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    void dfs(int x, int y, boolean[][] res) {
        res[x][y] = true;
        for (int[] dir : dirs) {
            int nx = dir[0] + x;
            int ny = dir[1] + y;
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                continue;//跳过越界的
            }
            if (res[nx][ny] || g[nx][ny] < g[x][y]) {
                continue;//跳过重复判断和不能流动的地方
            }
            dfs(nx, ny, res);
        }
    }
}
