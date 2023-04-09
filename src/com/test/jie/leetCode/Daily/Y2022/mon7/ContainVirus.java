package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.*;

/**
 *  2022/07/18
 *  749. 隔离病毒
 * 病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
 *
 * 假设世界由 m x n 的二维矩阵 isInfected 组成， isInfected[i][j] == 0 表示该区域未感染病毒，
 * 而  isInfected[i][j] == 1 表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。
 *
 * 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。现由于资源有限，
 * 每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），且该感染区域对未感染区域的威胁最大且 保证唯一 。
 *
 * 你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数;
 * 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。
 *
 * 示例 1：
 * 输入: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
 * 输出: 10
 * 解释:一共有两块被病毒感染的区域。
 * 在第一天，添加 5 墙隔离病毒区域的左侧。病毒传播后的状态是:
 * 第二天，在右侧添加 5 个墙来隔离病毒区域。此时病毒已经被完全控制住了。
 *
 * 示例 2：
 * 输入: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出: 4
 * 解释: 虽然只保存了一个小区域，但却有四面墙。
 * 注意，防火墙只建立在两个不同区域的共享边界上。
 * 示例 3:
 * 输入: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
 * 输出: 13
 * 解释: 在隔离右边感染区域后，隔离左边病毒区域只需要 2 个防火墙。
 *
 * 提示:
 * m == isInfected.length
 * n == isInfected[i].length
 * 1 <= m, n <= 50
 * isInfected[i][j] is either 0 or 1
 * 在整个描述的过程中，总有一个相邻的病毒区域，它将在下一轮 严格地感染更多未受污染的方块
 * https://leetcode.cn/problems/contain-virus/
 */
public class ContainVirus {
    public static void main(String[] args) {

    }
    class Solution {
        int[][] g;
        int n, m, ans;
        int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][] vis;
        int search(int _x, int _y, Set<Integer> s1, Set<Integer> s2) {
            int ans = 0;
            Deque<int[]> d = new ArrayDeque<>();
            vis[_x][_y] = true;
            d.addLast(new int[]{_x, _y});
            s1.add(_x * m + _y);
            while (!d.isEmpty()) {
                int[] info = d.pollFirst();
                int x = info[0], y = info[1];
                for (int[] di : dirs) {
                    int nx = x + di[0], ny = y + di[1], loc = nx * m + ny;
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || vis[nx][ny]) continue;
                    if (g[nx][ny] == 1) {
                        s1.add(loc);
                        vis[nx][ny] = true;
                        d.addLast(new int[]{nx, ny});
                    } else if (g[nx][ny] == 0) {
                        s2.add(loc);
                        ans++;
                    }
                }
            }
            return ans;
        }
        int getCnt() {
            vis = new boolean[n][m];
            int max = 0, ans = 0;
            // l1: 每个连通块的点集 s2: 每个连通块的候选感染点集
            List<Set<Integer>> l1 = new ArrayList<>(), l2 = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (g[i][j] == 1 && !vis[i][j]) {
                        // s1: 当前连通块的点集 s2: 当前连通块的候选感染点集
                        Set<Integer> s1 = new HashSet<>(), s2 = new HashSet<>();
                        int b = search(i, j, s1, s2), a = s2.size();
                        if (a > max) {
                            max = a; ans = b;
                        }
                        l1.add(s1); l2.add(s2);
                    }
                }
            }
            for (int i = 0; i < l2.size(); i++) {
                for (int loc : l2.get(i).size() == max ? l1.get(i) : l2.get(i)) {
                    int x = loc / m, y = loc % m;
                    g[x][y] = l2.get(i).size() == max ? -1 : 1;
                }
            }
            return ans;
        }
        public int containVirus(int[][] _g) {
            g = _g;
            n = g.length; m = g[0].length;
            while (true) {
                int cnt = getCnt();
                if (cnt == 0) break;
                ans += cnt;
            }
            return ans;
        }
    }

}