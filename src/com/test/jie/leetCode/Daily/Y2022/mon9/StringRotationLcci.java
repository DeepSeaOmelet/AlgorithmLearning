package com.test.jie.leetCode.Daily.Y2022.mon9;

/**
 * 2022/09/29
 * 面试题 01.09. 字符串轮转
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成
 * * （比如，waterbottle是erbottlewat旋转后的字符串）。
 * <p>
 * 示例1:
 * <p>
 * 输入：s1 = "waterbottle", s2 = "erbottlewat"
 * 输出：True
 * 示例2:
 * <p>
 * 输入：s1 = "aa", s2 = "aba"
 * 输出：False
 * 提示：
 * <p>
 * 字符串长度在[0, 100000]范围内。
 * 说明:
 * <p>
 * 你能只调用一次检查子串的方法吗？
 */
public class StringRotationLcci {
    public static void main(String[] args) {

    }

    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.length() == 0) return true;
        s2 += s2;
        return s2.contains(s1);
    }
}