package com.test.jie.leetCode.Daily.Y2022.mon3;

/**
 * Z 字形变换
 * <p>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * <p>
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * numRows = 5
 * P        H
 * A      S I
 * Y    I   R
 * P  L     I  G
 * A        N
 * 2 -> 1
 * 3 -> 3
 * 4 -> 5
 * 5 -> 7
 * 2(n-1)-1 2n-3
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZigzagConversion {
    public static void main(String[] args) {
        ZigzagConversion zig = new ZigzagConversion();
//        System.out.println(zig.convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"));
        System.out.println(zig.convert("A", 1));
        System.out.println(zig.convert("PAYPALISHIRING", 4) + ": " + zig.convert("PAYPALISHIRING", 4).equals("PINALSIGYAHRPI"));
    }

    /**
     * 首行和尾行的下标相差（numRows-1）*2 ,其他行相差为 两个首项分别为 i 和  numRows * 2 - i - 2
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (s.length()==1||numRows==1){
            return s;
        }
        char[] cs = s.toCharArray();
        int n = cs.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int cur = 2 * n;
            if (i == 0 || i == numRows - 1) {
                int offset = (numRows - 1) * 2;
                for (int j = i; j < n; j += offset) {
                    sb.append(cs[j]);
                }
            } else {
                int pos1 = i, pos2 = numRows * 2 - i - 2;
                int offset = (numRows - 1) * 2;
                while (pos1 < n || pos2 < n) {
                    if (pos1 < n) {
                        sb.append(cs[pos1]);
                        pos1 += offset;
                    }
                    if (pos2 < n) {
                        sb.append(cs[pos2]);
                        pos2 += offset;
                    }
                }
            }
        }
        return sb.toString();
    }
}
