package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.ArrayList;
import java.util.List;

/**
 * 2022/07/14
 * 745. 前缀和后缀搜索
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 * <p>
 * 实现 WordFilter 类：
 * - WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * - f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。
 * 如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 * <p>
 * 示例：
 * 输入
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * 输出
 * [null, 0]
 * 解释
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
 * <p>
 * 提示：
 * 1 <= words.length <= 104
 * 1 <= words[i].length <= 7
 * 1 <= pref.length, suff.length <= 7
 * words[i]、pref 和 suff 仅由小写英文字母组成
 * 最多对函数 f 执行 104 次调用
 */
public class PrefixAndSuffixSearch {
    public static void main(String[] args) {

    }
}

class WordFilter {
    //经典前缀树
    class TrieNode {
        TrieNode[] tns = new TrieNode[26];
        List<Integer> idxs = new ArrayList<>();//记录下标
    }

    public void add(TrieNode p, String s, int idx, boolean isTurn) {
        int len = s.length();
        p.idxs.add(idx);
        for (int i = isTurn ? len - 1 : 0; i < len && i >= 0; i += isTurn ? -1 : 1) {
            int u = s.charAt(i) - 'a';
            if (p.tns[u] == null) {
                p.tns[u] = new TrieNode();
            }
            //下一个
            p = p.tns[u];
            p.idxs.add(idx);
        }
    }

    public int query(String pre, String suf) {
        //匹配前后缀
        int preLen = pre.length();
        int sufLen = suf.length();
        TrieNode p = tr1;
        //前缀
        for (int i = 0; i < preLen; i++) {
            int u = pre.charAt(i) - 'a';
            if (p.tns[u] == null) {
                return -1;
            }
            p = p.tns[u];
        }
        List<Integer> l1 = p.idxs;
        p = tr2;
        //后缀
        for (int i = sufLen - 1; i >= 0; i--) {
            int u = suf.charAt(i) - 'a';
            if (p.tns[u] == null) {
                return -1;
            }
            p = p.tns[u];
        }
        List<Integer> l2 = p.idxs;
        //找到最大的,同时是l1和l2同一的下标
        int preIdxSize = l1.size();
        int sufIdxSize = l2.size();
        for (int i = preIdxSize - 1, j = sufIdxSize - 1; i >= 0 && j >= 0; ) {
            if (l1.get(i)> l2.get(j)){
                i--;
            }else if (l2.get(j)> l1.get(i)){
                j--;
            }else {
                return l1.get(i);
            }
        }
        return -1;
    }

    TrieNode tr1 = new TrieNode();
    TrieNode tr2 = new TrieNode();

    public WordFilter(String[] words) {
        for (int i = 0; i < words.length; i++) {
            add(tr1, words[i], i, false);
            add(tr2, words[i], i, true);
        }
    }

    public int f(String pref, String suff) {
        return query(pref, suff);
    }
}

class WordFilter1 {

    //暴力搜索--超时
    String[] w;

    public WordFilter1(String[] words) {
        w = words;
    }

    public int f(String pref, String suff) {
        int pLen = pref.length();
        int sLen = suff.length();
        for (int i = w.length - 1; i >= 0; i--) {
            String cur = w[i];
            int curLen = cur.length();
            if (curLen < pLen || curLen < sLen) {
                continue;
            }
            boolean ok = true;
            for (int j = 0; j < pLen && ok; j++) {
                if (cur.charAt(j) != pref.charAt(j)) {
                    ok = false;
                }
            }
            for (int j = 0; j < sLen && ok; j++) {
                if (cur.charAt(curLen - 1 - j) != suff.charAt(sLen - 1 - j)) {
                    ok = false;
                }
            }
            if (ok) {
                return i;
            }
        }
        return -1;
    }
}