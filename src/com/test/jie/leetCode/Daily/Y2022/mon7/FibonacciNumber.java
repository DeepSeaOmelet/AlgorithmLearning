package com.test.jie.leetCode.Daily.Y2022.mon7;

/**
 * 2022/07/17
 * 509. 斐波那契数
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 示例 2：
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 示例 3：
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * <p>
 * 提示：
 * 0 <= n <= 30
 */
public class FibonacciNumber {
    public static void main(String[] args) {
        FibonacciNumber f = new FibonacciNumber();
        System.out.println(f.fib(4));
    }

    public int fib(int n) {
        //动规五部曲
        /**
         * 1.确定dp数组及下标含义
         *  dp[i]:第i个数的斐波那契数
         * 2.确定递推公式
         *  dp[i]=dp[i-1]+dp[i-2]
         * 3.初始化数组
         *  dp[0]=0 dp[1]=1
         * 4.确定遍历顺序
         *  遍历数组从前到后
         * 5.举例推导dp数组
         *  0   1   1   2   3   5   8   13  21  34  55
         */
        //通常首先需要检测输入参数是否没有问题
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        int ans=0;
        for (int i = 2; i <= n; i++) {
            ans=dp[0]+dp[1];
            dp[0]=dp[1];
            dp[1]=ans;
        }
        return ans;
    }
}