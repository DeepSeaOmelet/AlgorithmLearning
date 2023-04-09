package com.test.jie.leetCode.Daily.Y2022.mon9;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 2022/09/27  https://leetcode.cn/problems/check-permutation-lcci/
 * 面试题 01.02. 判定是否互为字符重排
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * <p>
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 * <p>
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 */
public class CheckPermutationLcci {
    public static void main(String[] args) {
        // 在堆中创建字符串对象”Java“
// 将字符串对象”Java“的引⽤保存在字符串常量池中
        String s1 = "Java";
// 直接返回字符串常量池中字符串对象”Java“对应的引⽤
        String s2 = s1.intern();
// 会在堆中在单独创建⼀个字符串对象
        String s3 = new String("Java");
// 直接返回字符串常量池中字符串对象”Java“对应的引⽤
        String s4 = s3.intern();
// s1 和 s2 指向的是堆中的同⼀个对象
        System.out.println(s1 == s2); // true
// s3 和 s4 指向的是堆中不同的对象
        System.out.println(s3 == s4); // false
// s1 和 s4 指向的是堆中的同⼀个对象
        System.out.println(s1 == s4); //true

    }

    public boolean CheckPermutation(String s1, String s2) {
        return s1.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).equals(
                s2.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    public boolean CheckPermutation3(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n != m) return false;
        int tot = 0;
        int[] cnts = new int[256];
        for (int i = 0; i < n; i++) {
            if (++cnts[s1.charAt(i)] == 1) tot++;
            if (--cnts[s2.charAt(i)] == 0) tot--;
        }
        return tot == 0;
    }

    public boolean CheckPermutation2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] p = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            p[s1.charAt(i) - 'a']++;
            p[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < p.length; i++) {
            if (p[i] != 0) {
                return false;
            }
        }
        return true;
    }
}