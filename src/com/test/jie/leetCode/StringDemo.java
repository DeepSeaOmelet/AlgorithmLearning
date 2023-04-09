package com.test.jie.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringDemo {
    /**
     * 344. 反转字符串
     * 简单
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 示例 1：
     * 输入：s = ["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     * 输入：s = ["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     * 提示：
     * 1 <= s.length <= 105
     * s[i] 都是 ASCII 码表中的可打印字符
     */
    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }

    /**
     * 541. 反转字符串 II
     * 简单
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     * 示例 1：
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     * 示例 2：
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     * 提示：
     * 1 <= s.length <= 104
     * s 仅由小写英文组成
     * 1 <= k <= 104
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        int len = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < len; i += 2 * k) {
            reverse(cs, i, Math.min(len, i + k));
        }
        return String.valueOf(cs);
    }

    public void reverse(char[] cs, int start, int end) {
        //左闭右开
        end--;
        while (start < end) {
            char temp = cs[start];
            cs[start] = cs[end];
            cs[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 剑指 Offer 05. 替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * 限制：
     * 0 <= s 的长度 <= 10000
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 简单
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * 示例 1：
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * 示例 2：
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     * 限制：
     * 1 <= k < s.length <= 10000
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n).concat(s.substring(0, n));
    }

    public String reverseLeftWords2(String s, int n) {
        int len = s.length();
        char[] cs = new char[len];
        for (int i = 0; i < len; i++) {
            cs[i] = s.charAt((i + n) % len);
        }
        return String.valueOf(cs);
    }

    /**
     * 151. 反转字符串中的单词
     * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     * 示例 1：
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * 示例 2：
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：反转后的字符串中不能存在前导空格和尾随空格。
     * 示例 3：
     * 输入：s = "a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
     * 提示：
     * 1 <= s.length <= 10^4
     * s 包含英文大小写字母、数字和空格 ' '
     * s 中 至少存在一个 单词
     * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s.length() == 1) {
            return s;
        }
        //整体反转
        char[] cs = s.toCharArray();
        reverse(cs, 0, cs.length);
        int left = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ') {
                continue;
            }
            //找到单词，循环找出完整单词
            int temp = i;
            while (i < cs.length && cs[i] != ' ') {
                i++;
            }
            reverse(cs, temp, i);
            //双指针转移单词
            for (int j = temp; j < i; j++) {
                cs[left++] = cs[j];
                if (j == i - 1) {
                    //最后一个单词不用加空格
                    if (left < cs.length) {
                        cs[left++] = ' ';
                    }
                }
            }
        }
        return new String(cs, 0, (left == cs.length) && (cs[left - 1] != ' ') ? left : left - 1);
    }

    public String reverseWords2(String s) {
        List<String> ss = new ArrayList<>();
        int l = 0;
        int r = 0;
        //1.逐个截取
        while (r < s.length()) {
            if (s.charAt(r) == ' ') {
                r++;
                continue;
            }
            l = r;
            while (r < s.length() && s.charAt(r) != ' ') {
                r++;
            }
            ss.add(s.substring(l, r));
        }
        //2.反转存放
        int len = ss.size();
        StringBuilder sb = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            if (i != len - 1) {
                sb.append(" ");
            }
            sb.append(ss.get(i));
        }
        return sb.toString();
    }

    /**
     * KMP算法--getNext--前缀表
     * 初始化
     * 处理前后缀不相同的情况
     * 处理前后缀相同的情况
     */
//    public void getNext(int[] next, String Str) {
//        next[0] = 0;
//        //定义两个指针i和j，j指向前缀末尾位置，i指向后缀末尾位置。
//        int j = 0;
//        char[] cs = Str.toCharArray();
//        for (int i = 1; i < cs.length; i++) {
//            while (j > 0 && cs[i] != cs[j]) {// 前后缀不相同了
//                j = next[j - 1];// 向前回退
//            }
//            if (cs[i] == cs[j]) {// 找到相同的前后缀
//                j++;
//            }
//            next[i] = j;// 将j（前缀的长度）赋给next[i]
//        }
//    }

    /**
     * 28. 找出字符串中第一个匹配项的下标
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
     * 示例 1：
     * 输入：haystack = "sadbutsad", needle = "sad"
     * 输出：0
     * 解释："sad" 在下标 0 和 6 处匹配。
     * 第一个匹配项的下标是 0 ，所以返回 0 。
     * 示例 2：
     * 输入：haystack = "leetcode", needle = "leeto"
     * 输出：-1
     * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
     * 提示：
     * 1 <= haystack.length, needle.length <= 104
     * haystack 和 needle 仅由小写英文字符组成
     */
    public int strStr(String haystack, String needle) {
        if (haystack.length() == 0) {
            return 0;
        }
        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {
                return i - next.length + 1;
            }
        }
        return -1;
    }

    public void getNext(int[] next, String str) {
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
    }

    /**
     * 459. 重复的子字符串
     * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
     * 示例 1:
     * 输入: s = "abab"
     * 输出: true
     * 解释: 可由子串 "ab" 重复两次构成。
     * 示例 2:
     * 输入: s = "aba"
     * 输出: false
     * 示例 3:
     * 输入: s = "abcabcabcabc"
     * 输出: true
     * 解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
     * 提示：
     * 1 <= s.length <= 104
     * s 由小写英文字母组成
     */
    public boolean repeatedSubstringPattern2(String s) {
        int n = s.length();
        int[] next = new int[n];
        getNext(next,s);
        if (next[n-1]!=0 && n%(n-next[n-1])==0){
            return true;
        }
        return false;
    }

    public boolean repeatedSubstringPattern(String s) {

        if (s.length() == 1) {
            return false;
        }
        for (int i = 1; i <= s.length() / 2; i++) {
            if (s.length() % i != 0) {
                continue;
            }
            String sub = s.substring(0, i);
            boolean flag = true;
            for (int j = 0; j < s.length(); j++) {
                if (sub.charAt(j % sub.length()) != s.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        StringDemo s = new StringDemo();
        String str = "abcabcabcabc";
        int[] next = new int[100];
        s.getNext(next, str);
        System.out.println(Arrays.toString(next));
        str = "asdfasfdasdfasdf";
        s.getNext(next, str);
        System.out.println(Arrays.toString(next));
        str = "aabaabaabaabf";
        s.getNext(next, str);
        System.out.println(Arrays.toString(next));
    }
}
