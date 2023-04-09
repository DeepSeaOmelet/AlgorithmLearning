package com.test.jie.leetCode.Daily.Y2022.mon3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 词典中最长的单词
 * <p>
 * 给出一个字符串数组words 组成的一本英语词典。返回words 中最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。
 * <p>
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 * <p>
 * 示例 1：
 * 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 * 示例 2：
 * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
 * <p>
 * <p>
 * 提示：
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * 所有输入的字符串words[i]都只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestWordInDictionary {
    //模拟法
    public String longestWord2(String[] words) {
        String ans = "";
        Set<String> set = new HashSet<>();
        //重新排序
        for (String s : words) {
            set.add(s);
        }
        for (String s : set) {
            int n = s.length(), m = ans.length();
            if (m > n) {
                continue;
            }
            if (m == n && s.compareTo(ans) > 0) {
                continue;
            }
            boolean ok = true;
            for (int i = 1; i <= n && ok; i++) {
                String sub = s.substring(0, i);
                if (!set.contains(sub)) {
                    ok = false;
                }
            }
            if (ok) {
                ans = s;
            }
        }
        return ans;
    }
//    //字典树
static int N = 30010, M = 26;
    static int[][] tr = new int[N][M];
    static boolean[] isEnd = new boolean[N];
    static int idx = 0;
    void add(String s) {
        int p = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (tr[p][u] == 0) tr[p][u] = ++idx;
            p = tr[p][u];
        }
        isEnd[p] = true;
    }
    boolean query(String s) {
        int p = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            p = tr[p][u];
            if (!isEnd[p]) return false;
        }
        return true;
    }
    public String longestWord(String[] words) {
        Arrays.fill(isEnd, false);
        for (int i = 0; i <= idx; i++) Arrays.fill(tr[i], 0);
        idx = 0;

        String ans = "";
        for (String s : words) add(s);
        for (String s : words) {
            int n = s.length(), m = ans.length();
            if (n < m) continue;
            if (n == m && s.compareTo(ans) > 0) continue;
            if (query(s)) ans = s;
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestWordInDictionary dictionary = new LongestWordInDictionary();
        System.out.println(dictionary.longestWord2(new String[]{"w", "wo", "wor", "worl", "world"}));
        System.out.println(dictionary.longestWord2(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}));
    }

}
