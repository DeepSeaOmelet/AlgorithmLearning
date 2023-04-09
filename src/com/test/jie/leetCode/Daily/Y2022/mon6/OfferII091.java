package com.test.jie.leetCode.Daily.Y2022.mon6;

/**
 * 剑指 Offer II 091. 粉刷房子
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成 红色、蓝色 或者 绿色这三种颜色中的一种，
 * 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
 * 每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 * 请计算出粉刷完所有房子最少的花费成本。
 * <p>
 * 示例 1：
 * 输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 * 最少花费: 2 + 5 + 3 = 10。
 * 示例 2：
 * 输入: costs = [[7,6,2]]
 * 输出: 2
 * <p>
 * 提示:
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 * <p>
 * 本质上这是一道「状态机 DP」问题：某些状态只能由规则限定的状态所转移，通常我们可以从 f[i][j]能够更新哪些目标状态（后继状态）进行转移，
 * 也能够从 f[i][j] 依赖哪些前置状态（前驱状态）来转移。
 * <p>
 * 作者：AC_OIer
 * 链接：https://leetcode.cn/problems/JEj789/solution/by-ac_oier-6v2v/
 */
public class OfferII091 {
    public static void main(String[] args) {
        OfferII091 offerII091 = new OfferII091();
        System.out.println(offerII091.minCost(new int[][]{{1, 2, 30}, {32, 30, 31}, {1, 101, 102}}));
    }

    public int minCost(int[][] cs) {
        int len = cs.length;
        int a = cs[0][0],   //选择红0
                b = cs[0][1],//选择蓝1
                c = cs[0][2];//选择绿2
        //根据前一个房子的颜色选择不同颜色，差不多是排除每个房子最大的费用的颜色
        for (int i = 1; i < len; i++) {
            int d = Math.min(b,c)+cs[i][0];//根据前一个房子中价格小的选择同时选择这个非0的颜色
            int e = Math.min(a,c)+cs[i][1];//根据前一个房子中价格小的选择同时选择这个非1的颜色
            int f = Math.min(a,b)+cs[i][2];///根据前一个房子中价格小的选择同时选择这个非2的颜色
            a=d;
            b=e;
            c=f;
        }
        return Math.min(Math.min(a,b),c);
    }
}