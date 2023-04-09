package com.test.jie.leetCode.Daily.Y2022.mon9;

import java.util.HashSet;
import java.util.Set;

/**
 *  2022/09/18
 *  827. 最大人工岛
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 *
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 *
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 *
 * 示例 1:
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 * 示例 2:
 * 输入: grid = [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * 示例 3:
 * 输入: grid = [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 *
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 */
public class MakingALargeIsland {
    public static void main(String[] args) {

    }

}

class Solution {
    static int N = 510;
    static int[] p = new int[N * N], sz = new int[N * N];
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
    void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return ;
        if (sz[ra] > sz[rb]) {
            union(b, a);
        } else {
            sz[rb] += sz[ra]; p[ra] = p[rb];
        }
    }
    public int largestIsland(int[][] g) {
        int n = g.length;
        for (int i = 1; i <= n * n; i++) {
            p[i] = i; sz[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 0) continue;
                for (int[] di : dirs) {
                    int x = i + di[0], y = j + di[1];
                    if (x < 0 || x >= n || y < 0 || y >= n || g[x][y] == 0) continue;
                    union(i * n + j + 1, x * n + y + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    ans = Math.max(ans, sz[find(i * n + j + 1)]);
                } else {
                    int tot = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int[] di : dirs) {
                        int x = i + di[0],y = j + di[1];
                        if (x < 0 || x >= n || y < 0 || y >= n || g[x][y] == 0) continue;
                        int root = find(x * n + y + 1);
                        if (set.contains(root)) continue;
                        tot += sz[root];
                        set.add(root);
                    }
                    ans = Math.max(ans, tot);
                }
            }
        }
        return ans;
    }
}