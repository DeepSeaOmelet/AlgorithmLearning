package com.test.jie.leetCode.Daily.Y2022.mon4;

/**
 * 旋转字符串
 * <p>
 * 给定两个字符串, s和goal。如果在若干次旋转操作之后，s能变成goal，那么返回true。
 * s的 旋转操作 就是将s 最左边的字符移动到最右边。
 * 例如, 若s = 'abcde'，在旋转一次之后结果就是'bcdea'。
 * <p>
 * 示例 1:
 * 输入: s = "abcde", goal = "cdeab"
 * 输出: true
 * 示例 2:
 * 输入: s = "abcde", goal = "abced"
 * 输出: false
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, goal.length <= 100
 * s和goal由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RotateString {
    public static void main(String[] args) {
        RotateString rotate = new RotateString();
        System.out.println(rotate.rotateString("abcde", "cdeab"));
    }

    //由于每次旋转操作都是将最左侧字符移动到最右侧，因此如果 goal 可由 s 经过多步旋转而来
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
