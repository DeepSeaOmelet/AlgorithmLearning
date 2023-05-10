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
 * <p>
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
class PalindromicSubstringSolution2 {
    //for (int i = s.size() - 1; i >= 0; i--) {  // 注意遍历顺序
    //            for (int j = i; j < s.size(); j++) {
    public static int longestPalindromeSubseq2(String s) {
        char[] cs = s.toCharArray();
        int len = s.length();
        //1 优化为滚动数组
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            //初始化，一个字符的回文子序列长度就是1
            dp[i] = 1;
            int pre = 0;//用来保存i-1，序列dp滚动数组通用优化技巧
            for (int j = i - 1; j >= 0; j--) {
                int temp = dp[j];//用来保存i-1
                if (cs[i] == cs[j]) {
                    dp[j] = pre + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j + 1]);
                }
                pre = temp;
            }
        }
        return dp[0];
    }

    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        //定义dp[i][j]为最长长度
        int[][] dp = new int[len + 1][len + 1];
        //3
        //4
        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    //2
                    if (i - j <= 1) {
                        dp[i][j] = i - j + 1;
                    } else {
                        //在原来回文基础上+2
                        dp[i][j] = dp[i - 1][j + 1] + 2;
                    }
                } else {
                    //要么删除i，或者j
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j + 1]);
                }
            }
            //5
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[len - 1][0];
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
    }
}