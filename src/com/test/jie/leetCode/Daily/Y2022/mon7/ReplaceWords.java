package com.test.jie.leetCode.Daily.Y2022.mon7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2022/07/10
 * 648. 单词替换
 * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * <p>
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。
 * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * <p>
 * 你需要输出替换之后的句子。
 * <p>
 * 示例 1：
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 2：
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 * <p>
 * 提示：
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写字母组成。
 * 1 <= sentence.length <= 10^6
 * sentence 仅由小写字母和空格组成。
 * sentence 中单词的总量在范围 [1, 1000] 内。
 * sentence 中每个单词的长度在范围 [1, 1000] 内。
 * sentence 中单词之间由一个空格隔开。
 * sentence 没有前导或尾随空格。
 */
public class ReplaceWords {
    public static void main(String[] args) {
        ReplaceWords replaceWords = new ReplaceWords();
        String[] ss = new String[]{"cat","bat","rat"};
        List<String> list = Arrays.stream(ss).collect(Collectors.toList());
        System.out.println(replaceWords.replaceWords(list, "the cattle was rattled by the battery"));
    }


    class Node {
        boolean isEnd;
        Node[] tns = new Node[26];
    }

    Node root = new Node();

    void insert(String s) {
        Node cur = this.root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (cur.tns[u] == null) {
                cur.tns[u] = new Node();
            }
            cur = cur.tns[u];
        }
        //该词根末尾
        cur.isEnd = true;
    }

    String search(String s) {
        Node r = this.root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (r.tns[u] == null) break;
            //如果继承词有许多可以形成它的词根，则用最短的词根替换它。
            if (r.tns[u].isEnd) return s.substring(0, i + 1);
            r = r.tns[u];
        }
        //找不到词根
        return s;
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        for (String s : dictionary) {
            insert(s);
        }
        StringBuilder sb = new StringBuilder();
        for (String s : sentence.split(" ")) {
            sb.append(search(s)).append(" ");
        }
        return sb.substring(0,sb.length()-1);
    }
}