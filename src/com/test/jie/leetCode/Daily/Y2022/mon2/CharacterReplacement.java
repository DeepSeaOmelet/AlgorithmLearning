package com.test.jie.leetCode.Daily.Y2022.mon2;

/**
 * 替换后的最长重复字符
 * Tag : 「双指针」、「滑动窗口」
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，
 * 总共可最多替换k次。
 * <p>
 * 在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度k和 10^4不会超过 。
 */
public class CharacterReplacement {
    public static void main(String[] args) {
        CharacterReplacement replacement = new CharacterReplacement();
        System.out.println(replacement.solution("ABAB", 2));//4
        System.out.println(replacement.solution("AABABBA", 1));//4
    }

    public int solution(String s, int k) {
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        int ans = 0;
        //l , r滑动窗口
        for (int l = 0, r = 0; r < s.length(); r++) {
            cnt[cs[r] - 'A']++;
            while (!check(cnt, k)) {
                //说明不合法，sum(所有字符的出现次数)- max(出现次数最多的字符的出现次数) =
                // other(其他字符的出现次数) <= k
                //滑动窗口左端点l前进
                cnt[cs[l++] - 'A']--;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    private boolean check(int[] cnt, int k) {
        int max = 0, sum = 0;
        for (int i = 0; i < 26; i++) {
            max = Math.max(max, cnt[i]);
            sum += cnt[i];
        }
        return sum - max <= k;
    }
}
