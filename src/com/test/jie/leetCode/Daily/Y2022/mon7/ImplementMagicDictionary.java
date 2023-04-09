package com.test.jie.leetCode.Daily.Y2022.mon7;

/**
 * 2022/07/11
 * 676. 实现一个魔法字典
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，
 * 请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 * <p>
 * 实现 MagicDictionary 类：
 * <p>
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，
 * 使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * 输出
 * [null, null, false, true, false, false]
 * <p>
 * 解释
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 * <p>
 * 提示：
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写英文字母组成
 * dictionary 中的所有字符串 互不相同
 * 1 <= searchWord.length <= 100
 * searchWord 仅由小写英文字母组成
 * buildDict 仅在 search 之前调用一次
 * 最多调用 100 次 search
 */
public class ImplementMagicDictionary {
    public static void main(String[] args) {
        ImplementMagicDictionary d = new ImplementMagicDictionary();
        int a = 1;
        System.out.println(d.add(a++));
        System.out.println(a);
    }
    public int add(int a){
        return a;
    }

}

class MagicDictionary {
    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary();
        md.buildDict(new String[]{"apple", "appeal", "orange", "approach"});
    }

    //因为字典长度可达 100，每个词长度可达100，所以是100
    int N = 100 * 100, M = 26;
    int[][] tr;
    boolean[] isEnd;
    int idx;

    public MagicDictionary() {
        tr = new int[N][M];
        isEnd = new boolean[N * M];
        idx = 0;
    }

    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            int p = 0;//获取当前已使用的格子
            for (int i = 0; i < s.length(); i++) {
                int u = s.charAt(i) - 'a';
                if (tr[p][u] == 0) {
                    tr[p][u] = ++idx;
                }
                p = tr[p][u];
            }
            isEnd[p] = true;
        }
    }

    public boolean search(String searchWord) {
        return query(searchWord, 0, 0, 1);
    }

    /**
     * 存在有替换字符，需要用到DFS
     *
     * @param searchWord 待检索的字符串
     * @param idx        为当前处理到字符串 searchWord 的哪一位
     * @param p          为当前搜索到字典树的索引编号（起始有 p = 0）
     * @param limit      当前剩余的替换字符次数
     * @return
     */
    public boolean query(String searchWord, int idx, int p, int limit) {
        if (limit < 0) {
            //当前需要替换字符次数过多
            return false;
        }
        //如果是最后一个字母需要替换
        if (searchWord.length() == idx) {
            return isEnd[p] && limit == 0;
        }
        //递归搜索
        int u = searchWord.charAt(idx) - 'a';
        for (int i = 0; i < 26; i++) {
            if (tr[p][i] == 0) {
                continue;
            }
            //找到字典树中可能匹配的字符串
            //若i==u则说明不是在该下标的字母需要替换，否则可能需要替换
            if (query(searchWord, idx+1, tr[p][i], i == u ? limit : limit - 1)) {
                return true;
            }
        }
        return false;
    }

}