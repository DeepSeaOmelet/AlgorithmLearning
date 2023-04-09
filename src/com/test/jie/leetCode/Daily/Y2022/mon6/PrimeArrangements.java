package com.test.jie.leetCode.Daily.Y2022.mon6;

import java.util.ArrayList;
import java.util.List;

/**
 * 1175. 质数排列
 * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
 * <p>
 * 示例 1：
 * 输入：n = 5
 * 输出：12
 * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
 * 示例 2：
 * 输入：n = 100
 * 输出：682289015
 * <p>
 * 提示：
 * 1 <= n <= 100
 */
public class PrimeArrangements {
    public static void main(String[] args) {
        PrimeArrangements p = new PrimeArrangements();
        System.out.println(p.numPrimeArrangements(5));
    }

    static int MOD = (int) 1e9 + 7;
    static List<Integer> list = new ArrayList<>();

    static {
        for (int i = 2; i < 100; i++) {
            boolean ok = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                list.add(i);
            }
        }
    }

    public int numPrimeArrangements(int n) {
        //求 n 以内的质数个数,质数放在质数索引上
        //质数有a个，非质数则有n-a = b 个，即质数排列a！，那么非质数b!
        //二分找质数,n的左边界最接近的质数
        int l = 0, r = list.size() - 1;
        while (r > l) {
            int mid = l + r + 1 >> 1;
            if (list.get(mid) > n) r = mid - 1;
            else l = mid;
        }
        //包括下标为0
        int a = r + 1, b = n - a;
        long ans = 1;
        for (int i = 1; i <=a ; i++) {
            ans=ans*i ;
        }
        for (int i = 1; i <=b ; i++) {
            ans=ans*i ;
        }
        return (int)ans % MOD;
    }
}