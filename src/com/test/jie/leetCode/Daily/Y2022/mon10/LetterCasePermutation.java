package com.test.jie.leetCode.Daily.Y2022.mon10;

import java.util.ArrayList;
import java.util.List;

/**
 * 2022/10/30
 * 784. 字母大小写全排列
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * <p>
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 * <p>
 * 示例 1：
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 示例 2:
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 * <p>
 * 提示:
 * 1 <= s.length <= 12
 * s 由小写英文字母、大写英文字母和数字组成
 */
public class LetterCasePermutation {
    public static void main(String[] args) {

    }

    List<String> ans = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        char[] chars = s.toCharArray();
        dfs(chars, 0);
        return ans;
    }

    private void dfs(char[] cs, int start) {
        ans.add(new String(cs));
        for (int i = start; i < cs.length; i++) {
            if (Character.isLetter(cs[i])) {
                cs[i] = (char) (cs[i] ^ 32);
                dfs(cs, i + 1);
                cs[i] = (char) (cs[i] ^ 32);
            }
        }
    }

//    private void dfs(char[] cs, int index) {
//        if (index >= cs.length) {
//            ans.add(new String(cs));
//            return;
//        }
//        while (!Character.isLetter(cs[index])) {
//            index++;
//            if (index >= cs.length){
//                dfs(cs, index);
//                return;
//            }
//        }
//
//        dfs(cs, index + 1);
//        if (cs[index] <= 90) {
//            cs[index] = Character.toLowerCase(cs[index]);
//        } else {
//            cs[index] = Character.toUpperCase(cs[index]);
//        }
//        dfs(cs, index + 1);
//    }
}