package com.test.jie.leetCode.dp;

import java.util.Arrays;

public class PalindromicSubstring {
}

/**
 * 647. 回文子串
 * 中等
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 */
class PalindromicSubstringSolution1 {
    public int countSubstrings(String s) {
        int len = s.length();
        //1 定义dp[i][j]是s下标i到j是不是回文
        boolean[][] dp = new boolean[len][len];
        //4 遍历顺序
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                //2 分情况讨论
                if (s.charAt(i) == s.charAt(j)) {
                    if (i - j < 3) {
                        dp[i][j] = true;
                    } else {
                        //判断内部是否为回文
                        dp[i][j] = dp[i - 1][j + 1];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (dp[i][j]) {
                    ans++;
                }
            }
            //5
            System.out.println(Arrays.toString(dp[i]));
        }
        return ans;
    }
}

/**
 * 516. 最长回文子序列
 * 中等
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
class PalindromicSubstringSolution2 {
    public int longestPalindromeSubseq(String s) {

    }
}