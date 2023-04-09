package com.test.jie.leetCode.Daily.Y2022.mon10;

/**
 * 2022/11/01
 * 1662. 检查两个字符串数组是否相等
 * 给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
 * <p>
 * 数组表示的字符串 是由数组中的所有元素 按顺序 连接形成的字符串。
 * <p>
 * 示例 1：
 * 输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
 * 输出：true
 * 解释：
 * word1 表示的字符串为 "ab" + "c" -> "abc"
 * word2 表示的字符串为 "a" + "bc" -> "abc"
 * 两个字符串相同，返回 true
 * 示例 2：
 * 输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
 * 输出：false
 * 示例 3：
 * 输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * 输出：true
 * <p>
 * 提示：
 * 1 <= word1.length, word2.length <= 103
 * 1 <= word1[i].length, word2[i].length <= 103
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
 * word1[i] 和 word2[i] 由小写字母组成
 */
public class CheckIfTwoStringArraysAreEquivalent {
    public static void main(String[] args) {

    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int n1 = word1.length;
        int n2 = word2.length;
        int i = 0, j = 0, p = 0, q = 0;
        while (i < n1 && j < n2) {
            if (word1[i].charAt(p++) != word2[j].charAt(q++)) {
                return false;
            }
            if (p == word1[i].length()) {
                p = 0;
                i++;
            }
            if (q == word2[j].length()) {
                q = 0;
                j++;
            }
        }
        return i == n1 && j == n2;
    }
}