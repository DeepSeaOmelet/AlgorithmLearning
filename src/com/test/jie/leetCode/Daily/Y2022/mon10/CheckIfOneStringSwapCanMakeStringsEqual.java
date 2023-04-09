package com.test.jie.leetCode.Daily.Y2022.mon10;

/**
 * 2022/10/11
 * 1790. 仅执行一次字符串交换能否使两个字符串相等
 * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 * <p>
 * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：s1 = "bank", s2 = "kanb"
 * 输出：true
 * 解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
 * 示例 2：
 * 输入：s1 = "attack", s2 = "defend"
 * 输出：false
 * 解释：一次字符串交换无法使两个字符串相等
 * 示例 3：
 * 输入：s1 = "kelb", s2 = "kelb"
 * 输出：true
 * 解释：两个字符串已经相等，所以不需要进行字符串交换
 * 示例 4：
 * 输入：s1 = "abcd", s2 = "dcba"
 * 输出：false
 * <p>
 * 提示：
 * 1 <= s1.length, s2.length <= 100
 * s1.length == s2.length
 * s1 和 s2 仅由小写英文字母组成
 */
public class CheckIfOneStringSwapCanMakeStringsEqual {
    public static void main(String[] args) {

    }

    public boolean areAlmostEqual(String s1, String s2) {
        //true的两个可能：s1与s2相等，s1和s2所含字母相同，但仅共有两个位置不相同
        int a = -1;
        int b = -1;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            if (a == -1) {
                a = i;
            } else if (b == -1) {
                b = i;
            } else {
                return false;
            }
        }
        if (a == -1) {
            return true;
        } else if (b == -1) {
            return false;
        }
        return s1.charAt(a) == s2.charAt(b) && s1.charAt(b) == s2.charAt(a);

    }
}