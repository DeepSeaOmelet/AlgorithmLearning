package com.test.jie.leetCode.Daily.Y2022.mon7;

/**
 * 2022/07/17
 * <p>
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。
 * 机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 * <p>
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 * https://leetcode.cn/problems/unique-paths/
 */
public class UniquePaths {
    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 7));
        System.out.println(uniquePaths.uniquePaths(3, 2));
        System.out.println(uniquePaths.uniquePaths(7, 3));
        System.out.println(uniquePaths.uniquePaths(3, 3));
    }

    public int uniquePaths(int m, int n) {
        /**
         * 从左上角到右下角的过程中，我们需要移动 m+n-2m+n−2 次，其中有 m-1m−1 次向下移动，n-1n−1 次向右移动。
         * 因此路径的总数，就等于从 m+n-2m+n−2 次移动中选择 m-1m−1 次向下移动的方案数，即组合数：
         *  C(m-1,m+n-2)
         *  ->((m+n-2)(m+n-3)...n)/(m-1)!
         */
        long ans = 1;
        for (int i = n, j = 1; j < m; i++, j++) {
            ans = ans * i / j;
        }
        return (int) ans;
    }

    public int uniquePaths2(int m, int n) {
        /**
         * 将大问题分解成小问题
         * 1.确定dp数组及下标含义
         *  dp[i][j]:
         * 2.确定递推公式
         *  dp[i][j]=d[i-1][j]+d[i][j-1]
         * 3.初始化数组
         *  dp[0][j]=1;
         *  dp[i][0]=1;
         * 4.确定遍历顺序
         *  for i=1 i<m i++
         *      for j=1 j<n j++
         * 5.举例推导dp数组
         *
         */
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
    //dfs不行
//    int x;
//    int y;
//    int ans;
//    public int uniquePaths(int m, int n) {
//        x = n;
//        y = m;
//        ans=0;
//        dfs(1, 1);
//        return ans;
//    }
//
//    void dfs(int i, int j) {
//        if (i > y || j > x) {
//            return;
//        }else if (i==y &&j==x){
//            ans++;
//        }
//        dfs(i + 1, j);
//        dfs(i, j + 1);
//    }
}