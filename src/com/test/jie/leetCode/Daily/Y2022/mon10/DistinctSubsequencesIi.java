package com.test.jie.leetCode.Daily.Y2022.mon10;

/**
 * 2022/10/14
 * 940. 不同的子序列 II
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 * <p>
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 * 示例 1：
 * 输入：s = "abc"
 * 输出：7
 * 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
 * 示例 2：
 * 输入：s = "aba"
 * 输出：6
 * 解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
 * 示例 3：
 * 输入：s = "aaa"
 * 输出：3
 * 解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
 * <p>
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class DistinctSubsequencesIi {
    public static void main(String[] args) {

    }



    //优化
    public int distinctSubseqII(String s) {
        int MOD = (int) 1e9 + 7;
        int n = s.length();
        long ans = 0;
        long[] dp = new long[26];
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            long prev = dp[c];
            dp[c] = (ans + 1) % MOD;
            ans = (ans + dp[c] - prev +MOD) % MOD;
        }
        return (int)ans;
    }

    public int distinctSubseqII2(String s) {
        int MOD = (int) 1e9 + 7;
        int n = s.length(), ans = 0;
        int[][] dp = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            int c = s.charAt(i - 1) - 'a';
            for (int j = 0; j < 26; j++) {
                if (c != j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int cur = 1;
                    for (int k = 0; k < 26; k++) {
                        cur = (cur + dp[i - 1][k]) % MOD;
                    }
                    dp[i][j] = cur;
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            ans = (ans + dp[n][i]) % MOD;
        }
        return ans;
    }
}