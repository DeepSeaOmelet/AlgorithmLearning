package com.test.jie.leetCode.Daily.Y2022.mon9;

/**
 * 2022/09/21
 * 854. 相似度为 K 的字符串
 * 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
 * <p>
 * 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
 * <p>
 * 示例 1：
 * 输入：s1 = "ab", s2 = "ba"
 * 输出：1
 * 示例 2：
 * 输入：s1 = "abc", s2 = "bca"
 * 输出：2
 * <p>
 * 提示：
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1 和 s2  只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母
 * s2 是 s1 的一个字母异位词
 */
public class KSimilarStrings {
    public static void main(String[] args) {

    }

    int ans = Integer.MAX_VALUE;

    public int kSimilarity(String s1, String s2) {
        return dfs(s1.toCharArray(), s2.toCharArray(), 0, 0);
    }

    public int dfs(char[] sc1, char[] sc2, int start, int cnt) {
        //1.剪枝
        if (cnt >= ans) return ans;
        //2.结束判断
        if (start == sc2.length - 1) {
            ans = Math.min(cnt, ans);
            return ans;
        }
        //计算判断
        for (int i = start; i < sc2.length; i++) {
            if (sc2[i] != sc1[i]) {
                for (int j = i + 1; j < sc2.length; j++) {
                    if (sc2[j] == sc1[i] && sc2[j] != sc1[j]) {
                        swap(sc2, i, j);//交换
                        dfs(sc1, sc2, i + 1, cnt + 1);
                        swap(sc2, i, j);//回溯
                        if (sc2[i] == sc1[j]) break;//最优交换
                    }
                }
                return ans;
            }
        }
        ans = Math.min(cnt, ans);
        return ans;
    }

    public void swap(char[] sc, int i, int j) {
        char t = sc[i];
        sc[i] = sc[j];
        sc[j] = t;
    }
}