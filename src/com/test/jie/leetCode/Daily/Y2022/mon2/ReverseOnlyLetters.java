package com.test.jie.leetCode.Daily.Y2022.mon2;

/**
 * 仅仅反转字母
 * <p>
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * <p>
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * <p>
 * 示例 1：
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 */
public class ReverseOnlyLetters {
    public static void main(String[] args) {
        ReverseOnlyLetters letters = new ReverseOnlyLetters();
        System.out.println(letters.reverseOnlyLetters("ab-cd"));
        System.out.println(letters.reverseOnlyLetters("a-bC-dEf-ghIj"));
        System.out.println(letters.reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }

    //大佬的，双指针
    public String reverseOnlyLetters(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0, j = n - 1; i < j; ) {
            while (i < j && !Character.isLetter(cs[i])) i++;
            while (i < j && !Character.isLetter(cs[j])) j--;
            if (i < j) {
                char c = cs[i];
                cs[i++] = cs[j];
                cs[j--] = c;
            }
        }
        return String.valueOf(cs);
    }
    //我的
    public String reverseOnlyLetters2(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0, j = n - 1; i < n ; i++) {
            if (!Character.isLetter(cs[i])) {
                sb.append(cs[i]);
            } else {
                while (!Character.isLetter(cs[j])){
                    j--;
                }
                sb.append(cs[j--]);
            }
        }
        return sb.toString();
    }
}
