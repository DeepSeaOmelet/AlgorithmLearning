package com.test.jie.leetCode.Daily.Y2022.mon8;

import java.util.Arrays;

/**
 * 2022/08/03
 * 899. 有序队列
 * 给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 * <p>
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。
 * <p>
 * 示例 1：
 * 输入：s = "cba", k = 1
 * 输出："acb"
 * 解释：
 * 在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
 * 在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。
 * 示例 2：
 * 输入：s = "baaca", k = 3
 * 输出："aaabc"
 * 解释：
 * 在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
 * 在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。
 * <p>
 * 提示：
 * 1 <= k <= S.length <= 1000
 * s 只由小写字母组成。
 */
public class OrderlyQueue {
    public static void main(String[] args) {
        OrderlyQueue oq = new OrderlyQueue();
        System.out.println(oq.orderlyQueue("cba", 1));
    }

    static String test() {
        String s = "asdgbw";//abdgsw
        char[] cs = s.toCharArray();
        char[] cs2 = s.toCharArray();
        Arrays.sort(cs);
        s = String.valueOf(cs);
        System.out.println(String.valueOf(cs));
        StringBuilder sb = new StringBuilder(s);
        while (!sb.toString().equals(s)) {
            char a = 'z';
            int idx = 0;
            for (int i = 0; i < 3; i++) {
                if (sb.charAt(i) <= a) {
                    a = sb.charAt(i);
                    idx = i;
                }
            }
            sb.append(sb.charAt(idx));
            sb.deleteCharAt(idx);
        }
        System.out.println(String.valueOf(cs2));
        return "";
    }

    public String orderlyQueue(String s, int k) {
        char[] cs = s.toCharArray();
        if (k == 1) {
            //「最小表示法」
            int i = 0, j = 1, p = 0, n = cs.length;
            while (i < n && j < n && p < n) {
                char a = cs[(i + p) % n];
                char b = cs[(j + p) % n];
                if (a == b) {
                    p++;
                } else {
                    if (a > b) {
                        i += p + 1;
                    } else {
                        j += p + 1;
                    }
                    if (i == j) {
                        i++;
                    }
                    p = 0;
                }
            }
            i = Math.min(i, j);
            return s.substring(i) + s.substring(0, i);
        } else {
            Arrays.sort(cs);
            return String.valueOf(cs);
        }
    }

    public String orderlyQueue2(String s, int k) {
        if (k > 1) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            return String.valueOf(cs);
        } else {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < s.length(); i++) {
                sb.append(sb.charAt(0)).deleteCharAt(0);
                if (sb.toString().compareTo(s) < 0) {
                    s = sb.toString();
                }
            }
            return s;
        }
    }
}